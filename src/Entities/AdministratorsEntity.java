package Entities;

import javax.persistence.*;

@Entity
@Table(name = "Administrators", schema = "dbo", catalog = "ronigu")
public class AdministratorsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ADMINID", nullable = false, precision = 0)
    private int adminid;
    @Basic
    @Column(name = "USERNAME", nullable = true, length = 150)
    private String username;
    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 150)
    private String password;

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
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
}
