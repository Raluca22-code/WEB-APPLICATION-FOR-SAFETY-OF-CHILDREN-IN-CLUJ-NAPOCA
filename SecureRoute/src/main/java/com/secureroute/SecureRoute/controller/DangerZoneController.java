package com.secureroute.SecureRoute.controller;

import com.secureroute.SecureRoute.models.DangerZone;
import com.secureroute.SecureRoute.models.DangerZonePoint;
import com.secureroute.SecureRoute.service.DangerZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dangerZone")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class DangerZoneController {

    @Autowired
    private DangerZoneService dangerZoneService;

    @GetMapping("/read")
    public List<DangerZone> readDangerZone() {
        return dangerZoneService.readDangerZone();
    }

    @GetMapping("/read/{dangerZoneId}")
    public DangerZone readDangerZoneById(@PathVariable Long dangerZoneId) {
        return dangerZoneService.readDangerZoneById(dangerZoneId);
    }

    @PostMapping("/create")
    public DangerZone createDangerZone(@RequestBody DangerZone dangerZone){
        return dangerZoneService.createDangerZone(dangerZone,dangerZone.getDangerZonePoints());
    }

    @DeleteMapping("/delete/{dangerZoneId}")
    public void deleteDangerZone(@PathVariable Long dangerZoneId){
        dangerZoneService.deleteDangerZoneById(dangerZoneId);
    }

    @PutMapping("/update")
    public DangerZone updateDangerZone(@RequestBody DangerZone updateDangerZone){
        return dangerZoneService.updateDangerZone(updateDangerZone);
    }
}
