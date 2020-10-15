package web.model;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet controller for application for handling requests from user
 * @author Petr Smejkal
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    /**
     * Function 
     * @param request
     * @param response
     * @throws ServletException In case of failure throw ServletException
     * @throws IOException In case of failure throw IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException In case of failure throw ServletException
     * @throws IOException In case of failure throw IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
                case "/insert":
                    insertUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Function for listing all users into table
     * @param request HttpServletRequest request from function doGet()
     * @param response HttpServletResponse response from function doGet()
     * @throws SQLException In case of failure throw SQLException
     * @throws IOException In case of failure throw IOException
     * @throws ServletException In case of failure throw ServletException
     */
    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Function for taking informations from form and inserting new user into database
     * @param request HttpServletRequest request from function doGet()
     * @param response HttpServletResponse response from function doGet()
     * @throws SQLException In case of failure throw SQLException
     * @throws IOException In case of failure throw IOException
     */
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name_surname = request.getParameter("name_surname");
        String email = request.getParameter("email");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        String gender = request.getParameter("gender");
        String education = request.getParameter("education");
        String hobby1 = request.getParameter("hobby1");
        String hobby2 = request.getParameter("hobby2");
        String hobby3 = request.getParameter("hobby3");
        String hobby4 = request.getParameter("hobby4");
        String other = request.getParameter("other");
        StringBuilder hobby = new StringBuilder();
        String[] hobbies = {hobby1, hobby2, hobby3, hobby4, other};
        for (String tmp : hobbies) {
            if (tmp != null) {
                hobby.append(tmp).append(", ");
            }
        }
        if (!hobby.isEmpty()) {
            hobby.delete(hobby.length() - 2, hobby.length());
        }
        User newUser = new User(name_surname, email, birthday, gender, education, hobby.toString());
        userDAO.insertUser(newUser);
        response.sendRedirect("main");
    }
}
