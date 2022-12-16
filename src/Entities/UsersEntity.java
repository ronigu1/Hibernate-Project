package Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Users", schema = "dbo", catalog = "ronigu")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USERID", nullable = false, precision = 0)
    private int userid;
    @Basic
    @Column(name = "USERNAME", nullable = true, length = 100)
    private String username;
    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 100)
    private String password;
    @Basic
    @Column(name = "FIRST_NAME", nullable = true, length = 100)
    private String firstName;
    @Basic
    @Column(name = "LAST_NAME", nullable = true, length = 100)
    private String lastName;
    @Basic
    @Column(name = "DATE_OF_BIRTH", nullable = true)
    private Date dateOfBirth;
    @Basic
    @Column(name = "REGISTRATION_DATE", nullable = true)
    private Date registrationDate;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
