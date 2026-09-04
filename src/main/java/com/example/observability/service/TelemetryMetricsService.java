package com.example.observability.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class TelemetryMetricsService {

    private final Counter processedEventsCounter;
    private final Counter errorEventsCounter;
    private final Timer eventProcessingTimer;
    private final Random random = new Random();

    public TelemetryMetricsService(MeterRegistry registry) {
        this.processedEventsCounter = Counter.builder("kafka_events_processed_total")
                .description("Total Kafka events processed by the microservice")
                .tag("topic", "machine-telemetry")
                .register(registry);

        this.errorEventsCounter = Counter.builder("kafka_events_failed_total")
                .description("Total failed Kafka processing events")
                .tag("topic", "machine-telemetry")
                .register(registry);

        this.eventProcessingTimer = Timer.builder("kafka_event_processing_latency_seconds")
                .description("Latency distribution of event processing")
                .register(registry);
    }

    public void processEvent() {
        long startTime = System.currentTimeMillis();
        
        try {
            // Simulate processing work
            int latencyMs = random.nextInt(150) + 20; 
            Thread.sleep(latencyMs);

            if (random.nextInt(10) > 8) { // Simulate 10% error rate
                errorEventsCounter.increment();
                throw new RuntimeException("Processing bottleneck or event payload error");
            }

            processedEventsCounter.increment();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            eventProcessingTimer.record(duration, TimeUnit.MILLISECONDS);
        }
    }
}