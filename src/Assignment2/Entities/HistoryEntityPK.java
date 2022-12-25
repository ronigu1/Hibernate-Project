package Assignment2.Entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class HistoryEntityPK implements Serializable {
    private int userid;
    private long mid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryEntityPK that = (HistoryEntityPK) o;
        return userid == that.userid && mid == that.mid && Objects.equals(viewtime, that.viewtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, mid, viewtime);
    }
}
