package com.secureroute.SecureRoute.service;

import com.secureroute.SecureRoute.models.DangerZone;
import com.secureroute.SecureRoute.models.DangerZonePoint;
import com.secureroute.SecureRoute.repository.DangerZonePointRepository;
import com.secureroute.SecureRoute.repository.DangerZoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DangerZoneService {

    private final DangerZoneRepository dangerZoneRepository;
    private final DangerZonePointService dangerZonePointService;

    @Autowired
    public DangerZoneService(DangerZoneRepository dangerZoneRepository, DangerZonePointService dangerZonePointService) {
        this.dangerZoneRepository = dangerZoneRepository;
        this.dangerZonePointService = dangerZonePointService;
    }

    public List<DangerZone> readDangerZone() {
        return dangerZoneRepository.findAll();
    }

    public DangerZone readDangerZoneById(Long id) {
        return dangerZoneRepository.findById(id).orElse(null);
    }

    @Transactional
    public DangerZone createDangerZone(DangerZone dangerZone, List<DangerZonePoint> dangerZonePoints) {
        DangerZone savedDangerZone = dangerZoneRepository.save(dangerZone);
        for (DangerZonePoint dangerZonePoint : dangerZonePoints) {
            dangerZonePoint.setDangerZone(savedDangerZone);
            dangerZonePointService.createDangerZonePoint(dangerZonePoint);
        }
        return savedDangerZone;
    }

    public void deleteDangerZoneById(Long id) {
        dangerZoneRepository.deleteById(id);
    }

    public DangerZone updateDangerZone(DangerZone updateDangerZone) {
        DangerZone currentDangerZone = dangerZoneRepository.findById(updateDangerZone.getId()).orElse(null);
        if (currentDangerZone != null) {
            currentDangerZone.setName(updateDangerZone.getName());
            currentDangerZone.setDangerGrade(updateDangerZone.getDangerGrade());
            currentDangerZone.setDangerZonePoints(updateDangerZone.getDangerZonePoints());
            return dangerZoneRepository.save(currentDangerZone);
        }
        return null;
    }

    @Transactional
    // New method to check if a location is in a dangerous zone
    public boolean isLocationInDangerZone(double latitude, double longitude) {
        List<DangerZone> dangerZones = readDangerZone();
        for (DangerZone zone : dangerZones) {
            if (isPointInPolygon(latitude, longitude, zone.getDangerZonePoints())) {
                return true;
            }
        }
        return false;
    }

    // Check if a point is inside a polygon
    private boolean isPointInPolygon(double lat, double lon, List<DangerZonePoint> points) {
        int intersectCount = 0;
        for (int i = 0; i < points.size(); i++) {
            DangerZonePoint p1 = points.get(i);
            DangerZonePoint p2 = points.get((i + 1) % points.size());
            if (rayIntersectsSegment(lat, lon, p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude())) {
                intersectCount++;
            }
        }
        return (intersectCount % 2) == 1;
    }

    // Check if the ray intersects with a polygon segment
    private boolean rayIntersectsSegment(double lat, double lon, double lat1, double lon1, double lat2, double lon2) {
        if (lat1 > lat2) {
            double tempLat = lat1;
            lat1 = lat2;
            lat2 = tempLat;
            double tempLon = lon1;
            lon1 = lon2;
            lon2 = tempLon;
        }
        if (lat == lat1 || lat == lat2) {
            lat += 0.00000001;
        }
        if (lat < lat1 || lat > lat2) {
            return false;
        }
        if (lon >= Math.max(lon1, lon2)) {
            return false;
        }
        if (lon < Math.min(lon1, lon2)) {
            return true;
        }
        double red = (lat - lat1) / (lat2 - lat1);
        double blue = (lon - lon1) / (lon2 - lon1);
        return blue >= red;
    }
}
