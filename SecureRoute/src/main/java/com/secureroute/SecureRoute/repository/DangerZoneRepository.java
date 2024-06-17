package com.secureroute.SecureRoute.repository;

import com.secureroute.SecureRoute.models.DangerZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DangerZoneRepository extends JpaRepository<DangerZone, Long>{

}
