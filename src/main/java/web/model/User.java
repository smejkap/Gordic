package web.model;

import java.sql.Date;

/**
 * Class for storing users from database
 * @author Petr Smejkal
 */
public class User {

    protected String name_surname;
    protected String email;
    protected Date birthday;
    protected String gender;
    protected String education;
    protected String hobby;

    /**
     * User constructor
     * @param name_surname Name and surname of user with space between
     * @param email E-mail of user
     * @param birthday Birthday of user
     * @param gender Gender of user, male/female
     * @param education Maximal finished study of user
     * @param hobby Hobbies of user separated with ", "
     */
    public User(String name_surname, String email, Date birthday, String gender, String education, String hobby) {
        this.name_surname = name_surname;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.education = education;
        this.hobby = hobby;
    }

    public String getName_surname() {
        return name_surname;
    }

    public void setName_surname(String name_surname) {
        this.name_surname = name_surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

}
