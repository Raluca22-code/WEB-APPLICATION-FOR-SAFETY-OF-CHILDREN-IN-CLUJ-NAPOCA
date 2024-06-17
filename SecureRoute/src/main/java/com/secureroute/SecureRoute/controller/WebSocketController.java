package com.secureroute.SecureRoute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secureroute.SecureRoute.models.PointDTO;
import com.secureroute.SecureRoute.service.DangerZoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    private final DangerZoneService dangerZoneService;
    private final ObjectMapper objectMapper;

    @Autowired
    public WebSocketController(DangerZoneService dangerZoneService, ObjectMapper objectMapper) {
        this.dangerZoneService = dangerZoneService;
        this.objectMapper = objectMapper;
    }

    @MessageMapping("/sendMessage/{parentID}")
    @SendTo("/topic/messages/{parentID}")
    public String handleMessage(@DestinationVariable String parentID, String message) {
        try {
            logger.info("Received message: " + message);

            // Parse the incoming message to extract latitude and longitude
            PointDTO point = objectMapper.readValue(message, PointDTO.class);
            double latitude = point.getLatitude();
            double longitude = point.getLongitude();

            // Check if the location is in a danger zone
            boolean isInDangerZone = dangerZoneService.isLocationInDangerZone(latitude, longitude);

            // Prepare the response
            String alert = isInDangerZone ? "Your child is in a dangerous spot!" : "Your child is not in a dangerous spot!";
            return "{\"alert\":\"" + HtmlUtils.htmlEscape(alert) + "\"}";
        } catch (Exception e) {
            logger.error("Error processing message: " + message, e);
            return "{\"alert\":\"" + HtmlUtils.htmlEscape("ERROR") + "\"}";
        }
    }
}
