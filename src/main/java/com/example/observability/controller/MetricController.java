package com.example.observability.controller;

import com.example.observability.service.TelemetryMetricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/telemetry")
public class MetricController {

    private final TelemetryMetricsService metricsService;

    public MetricController(TelemetryMetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @PostMapping("/simulate")
    public ResponseEntity<String> simulateTraffic() {
        try {
            metricsService.processEvent();
            return ResponseEntity.ok("Event processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Event processing failed: " + e.getMessage());
        }
    }
}