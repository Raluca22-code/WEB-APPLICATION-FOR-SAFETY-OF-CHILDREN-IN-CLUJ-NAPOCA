package com.secureroute.SecureRoute.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class DangerZone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dangerzone_sequence")
    @SequenceGenerator(name = "dangerzone_sequence", sequenceName = "dangerzone_sequence", allocationSize = 1)
    @Column(name="danger_zone_id")
    private Long id;
    private String name;
    private String dangerGrade;
    @OneToMany(mappedBy = "dangerZone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DangerZonePoint> dangerZonePoints;

    public DangerZone() {
    }

    public DangerZone(Long id, String name, String dangerGrade) {
        this.id = id;
        this.name = name;
        this.dangerGrade = dangerGrade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDangerGrade() {
        return dangerGrade;
    }

    public void setDangerGrade(String dangerGrade) {
        this.dangerGrade = dangerGrade;
    }

    public List<DangerZonePoint> getDangerZonePoints() {
        return dangerZonePoints;
    }

    public void setDangerZonePoints(List<DangerZonePoint> dangerZonePoints) {
        this.dangerZonePoints = dangerZonePoints;
    }
}
