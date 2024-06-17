package com.secureroute.SecureRoute.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table

public class DangerZonePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dangerzonepoint_sequence")
    @SequenceGenerator(name = "dangerzonepoint_sequence", sequenceName = "dangerzonepoint_sequence", allocationSize = 1)

    @Column(name = "danger_zone_point_id")

    private Long id;
    private Double latitude;
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "danger_zone_id")
    @JsonIgnore // Add this annotation to break the circular reference
    private DangerZone dangerZone;

    public DangerZonePoint() {
    }

    public DangerZonePoint(Long id, Double latitude, Double longitude, DangerZone dangerZone) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dangerZone = dangerZone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public DangerZone getDangerZone() {
        return dangerZone;
    }

    public void setDangerZone(DangerZone dangerZone) {
        this.dangerZone = dangerZone;
    }
}
