package com.secureroute.SecureRoute.models;

import jakarta.persistence.*;

@Entity
@Table

public class DangerZoneAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dangerzonealert_sequence")
    @SequenceGenerator(name = "dangerzonealert_sequence", sequenceName = "dangerzonealert_sequence", allocationSize = 1)

    @Column(name="danger_zone_alert_id")
    private Long id;
    private String alert;
    private Long userId;
    private Long dangerZoneId;

    public DangerZoneAlert() {
    }

    public DangerZoneAlert(Long id, String alert, Long userId, Long dangerZoneId) {
        this.id = id;
        this.alert = alert;
        this.userId = userId;
        this.dangerZoneId = dangerZoneId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDangerZoneId() {
        return dangerZoneId;
    }

    public void setDangerZoneId(Long dangerZoneId) {
        this.dangerZoneId = dangerZoneId;
    }
}
