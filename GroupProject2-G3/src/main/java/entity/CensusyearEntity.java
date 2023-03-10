package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "censusyear", schema = "prog3060", catalog = "")
public class CensusyearEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "censusYearID", nullable = false)
    private int censusYearId;
    @Basic
    @Column(name = "censusYear", nullable = false)
    private int censusYear;

    public int getCensusYearId() {
        return censusYearId;
    }

    public void setCensusYearId(int censusYearId) {
        this.censusYearId = censusYearId;
    }

    public int getCensusYear() {
        return censusYear;
    }

    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }
}
