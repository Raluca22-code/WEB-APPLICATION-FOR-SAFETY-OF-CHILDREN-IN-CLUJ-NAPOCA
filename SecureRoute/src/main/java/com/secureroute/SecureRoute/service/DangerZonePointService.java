package com.secureroute.SecureRoute.service;

import com.secureroute.SecureRoute.models.DangerZone;
import com.secureroute.SecureRoute.models.DangerZonePoint;
import com.secureroute.SecureRoute.repository.DangerZonePointRepository;
import com.secureroute.SecureRoute.repository.DangerZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DangerZonePointService {

    private final DangerZonePointRepository dangerZonePointRepository;

    @Autowired
    public DangerZonePointService(DangerZonePointRepository dangerZonePointRepository) {
        this.dangerZonePointRepository = dangerZonePointRepository;
    }

    public List<DangerZonePoint> readDangerZonePointsForDangerZone(Long dangerZoneId) {
        return dangerZonePointRepository.findByDangerZone_Id(dangerZoneId);
    }

    public DangerZonePoint createDangerZonePoint(DangerZonePoint dangerZonePoint){
        return dangerZonePointRepository.save(dangerZonePoint);
    }

}
