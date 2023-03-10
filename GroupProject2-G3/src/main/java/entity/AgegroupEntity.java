package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "agegroup", schema = "prog3060", catalog = "")
public class AgegroupEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ageGroupID", nullable = false)
    private int ageGroupId;
    @Basic
    @Column(name = "description", nullable = false, length = 40)
    private String description;

    public int getAgeGroupId() {
        return ageGroupId;
    }

    public void setAgeGroupId(int ageGroupId) {
        this.ageGroupId = ageGroupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
