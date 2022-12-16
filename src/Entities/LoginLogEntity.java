package Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "LoginLog", schema = "dbo", catalog = "ronigu")
@IdClass(LoginLogEntityPK.class)
public class LoginLogEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USERID", nullable = false, precision = 0)
    private int userid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "LOGINTIME", nullable = false)
    private Date logintime;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }
}
