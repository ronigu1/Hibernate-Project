package Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "History", schema = "dbo", catalog = "ronigu")
@IdClass(HistoryEntityPK.class)
public class HistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USERID", nullable = false, precision = 0)
    private int userid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MID", nullable = false)
    private long mid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "VIEWTIME", nullable = false)
    private Date viewtime;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public Date getViewtime() {
        return viewtime;
    }

    public void setViewtime(Date viewtime) {
        this.viewtime = viewtime;
    }
}
