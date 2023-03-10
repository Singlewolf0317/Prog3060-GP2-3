package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "geographicarea", schema = "prog3060", catalog = "")
public class GeographicareaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "geographicAreaID", nullable = false)
    private int geographicAreaId;
    @Basic
    @Column(name = "code", nullable = false)
    private int code;
    @Basic
    @Column(name = "level", nullable = false)
    private int level;
    @Basic
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Basic
    @Column(name = "alternativeCode", nullable = false)
    private int alternativeCode;

    public int getGeographicAreaId() {
        return geographicAreaId;
    }

    public void setGeographicAreaId(int geographicAreaId) {
        this.geographicAreaId = geographicAreaId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlternativeCode() {
        return alternativeCode;
    }

    public void setAlternativeCode(int alternativeCode) {
        this.alternativeCode = alternativeCode;
    }

    public String displayInfo(){
        return "ID: "+this.getGeographicAreaId()+" Name: "+this.getName()+" Leve: "+this.getLevel()+ " Code: "+this.getCode()+"Alternative Code: "+this.getAlternativeCode();
    }
}
