package Assignment2.Entities;

import javax.persistence.*;

@Entity
@Table(name = "Similarity", schema = "dbo", catalog = "ronigu")
@IdClass(SimilarityEntityPK.class)
public class SimilarityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MID1", nullable = false)
    private long mid1;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MID2", nullable = false)
    private long mid2;
    @Basic
    @Column(name = "SIMILARITY", nullable = true, precision = 0)
    private Float similarity;

    public long getMid1() {
        return mid1;
    }

    public void setMid1(long mid1) {
        this.mid1 = mid1;
    }

    public long getMid2() {
        return mid2;
    }

    public void setMid2(long mid2) {
        this.mid2 = mid2;
    }

    public Float getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Float similarity) {
        this.similarity = similarity;
    }
}
