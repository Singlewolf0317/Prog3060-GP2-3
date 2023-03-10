package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "household", schema = "prog3060", catalog = "")
public class HouseholdEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "geographicArea", nullable = false)
    private int geographicArea;
    @Basic
    @Column(name = "householdType", nullable = false)
    private short householdType;
    @Basic
    @Column(name = "householdSize", nullable = false)
    private short householdSize;
    @Basic
    @Column(name = "householdsByAgeRange", nullable = false)
    private short householdsByAgeRange;
    @Basic
    @Column(name = "householdEarners", nullable = false)
    private short householdEarners;
    @Basic
    @Column(name = "totalIncome", nullable = false)
    private short totalIncome;
    @Basic
    @Column(name = "censusYear", nullable = false)
    private int censusYear;
    @Basic
    @Column(name = "numberReported", nullable = false)
    private int numberReported;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeographicArea() {
        return geographicArea;
    }

    public void setGeographicArea(int geographicArea) {
        this.geographicArea = geographicArea;
    }

    public short getHouseholdType() {
        return householdType;
    }

    public void setHouseholdType(short householdType) {
        this.householdType = householdType;
    }

    public short getHouseholdSize() {
        return householdSize;
    }

    public void setHouseholdSize(short householdSize) {
        this.householdSize = householdSize;
    }

    public short getHouseholdsByAgeRange() {
        return householdsByAgeRange;
    }

    public void setHouseholdsByAgeRange(short householdsByAgeRange) {
        this.householdsByAgeRange = householdsByAgeRange;
    }

    public short getHouseholdEarners() {
        return householdEarners;
    }

    public void setHouseholdEarners(short householdEarners) {
        this.householdEarners = householdEarners;
    }

    public short getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(short totalIncome) {
        this.totalIncome = totalIncome;
    }

    public int getCensusYear() {
        return censusYear;
    }

    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }

    public int getNumberReported() {
        return numberReported;
    }

    public void setNumberReported(int numberReported) {
        this.numberReported = numberReported;
    }
}
