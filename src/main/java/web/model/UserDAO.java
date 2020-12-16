package web.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for communication with database for users
 * @author Petr Smejkal
 */
public class UserDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/people?serverTimezone=UTC# [root on Default schema]";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_USER_SQL = "INSERT INTO people"
            + " (name_surname, email, birthday, gender, education, hobby) VALUES" + "(?, ?, ?, ?, ?, ?);";
    private static final String SELECT_USERS_SQL = "SELECT * FROM people;";

    /**
     * Default constructor for UserDAO
     */
    public UserDAO() {
    }

    /**
     * Function for getting connection to database
     * @return Returns successfull connection to database or throw exception
     */
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            //TODO
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //TODO
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Function for inserting user into database
     * @param user User which will be inserted to databse
     */
    public void insertUser(User user) {
        System.out.println(INSERT_USER_SQL);
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getName_surname());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setDate(3, user.getBirthday());
            preparedStatement.setString(4, user.getGender());
            preparedStatement.setString(5, user.getEducation());
            preparedStatement.setString(6, user.getHobby());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {}
    }

    /**
     * Function for selecting all rows from database and store them as class User
     * @return Return array list of all users from database
     */
    public List< User> selectAllUsers() {
        List< User> users = new ArrayList<>();
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name_surname = rs.getString("name_surname");
                String email = rs.getString("email");
                Date birthday = rs.getDate("birthday");
                String gender = rs.getString("gender");
                String education = rs.getString("education");
                String hobby = rs.getString("hobby");
                users.add(new User(name_surname, email, birthday, gender, education, hobby));
            }
        } catch (SQLException e) {}
        return users;
    }
}