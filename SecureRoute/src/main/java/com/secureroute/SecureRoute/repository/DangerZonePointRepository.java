package com.secureroute.SecureRoute.repository;

import com.secureroute.SecureRoute.models.DangerZone;
import com.secureroute.SecureRoute.models.DangerZonePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DangerZonePointRepository extends JpaRepository<DangerZonePoint, Long>{
    List<DangerZonePoint> findByDangerZone_Id(Long dangerZoneId);

}
