package Entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class LoginLogEntityPK implements Serializable {
    @Column(name = "USERID", nullable = false, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    @Column(name = "LOGINTIME", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginLogEntityPK that = (LoginLogEntityPK) o;
        return userid == that.userid && Objects.equals(logintime, that.logintime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, logintime);
    }
}
