package Entities;

import javax.persistence.*;

@Entity
@Table(name = "MediaItems", schema = "dbo", catalog = "ronigu")
public class MediaItemsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MID", nullable = false)
    private long mid;
    @Basic
    @Column(name = "PROD_YEAR", nullable = true)
    private Long prodYear;
    @Basic
    @Column(name = "TITLE", nullable = true, length = 2147483647)
    private String title;
    @Basic
    @Column(name = "TITLE_LENGTH", nullable = true)
    private Long titleLength;

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public Long getProdYear() {
        return prodYear;
    }

    public void setProdYear(Long prodYear) {
        this.prodYear = prodYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTitleLength() {
        return titleLength;
    }

    public void setTitleLength(Long titleLength) {
        this.titleLength = titleLength;
    }
}
