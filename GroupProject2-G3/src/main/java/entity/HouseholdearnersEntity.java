package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "householdearners", schema = "prog3060", catalog = "")
public class HouseholdearnersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private short id;
    @Basic
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
