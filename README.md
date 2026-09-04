# Observability Platform

A full-stack observability platform built with **Spring Boot 3**, **Micrometer**, **Prometheus**, and **Grafana**. This project captures application metrics and visualizes system health, performance, and custom telemetry data in real-time.

---

## 🏗️ Architecture & Tech Stack

* **Application:** Spring Boot 3 (Java 17)[cite: 1]
* **Metrics Exporter:** Spring Boot Actuator & Micrometer Prometheus Registry[cite: 1]
* **Monitoring & Time-Series DB:** Prometheus
* **Visualization & Dashboards:** Grafana
* **Containerization:** Docker & Docker Compose[cite: 1]

---

## 🚀 Getting Started

### Prerequisites

* [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running.
* *(Optional)* Java 17+ and Apache Maven installed locally (if running outside Docker).


## 🛠️ Quick Start Guide

### 1. Start Infrastructure Services (Prometheus & Grafana)

Run Docker Compose to launch Prometheus and Grafana:
docker-compose up -d

2. Run the Spring Boot Application
   docker run -it --rm -v "${PWD}:/app" -w /app -p 8081:8081 maven:3.9-eclipse-temurin-17 mvn spring-boot:run

🌐 Service Endpoints
Service	              URL	                                           Description
Spring Boot App	    http://localhost:8081                     Application base path
Prometheus Metrics	http://localhost:8081/actuator/prometheus	Micrometer scraped endpoint
Prometheus UI	      http://localhost:9090	                    Query & target management UI
Grafana UI	        http://localhost:3000	                    Dashboard & telemetry visualization


📊 Grafana Setup & Dashboards
1. Access Grafana at http://localhost:3000 (Default login: admin / admin).
2. Navigate to Connections $\rightarrow$ Data sources $\rightarrow$ Add data source.
3. Select Prometheus and configure the URL:
      http://prometheus:9090
4. Click Save & test.
5. Import a pre-built Spring Boot dashboard:
Go to Dashboards $\rightarrow$ New $\rightarrow$ Import.
Enter Dashboard ID 11378 or 4701 and select your Prometheus data source.


⚙️ Project Configuration DetailsPrometheus Target: 
1. Configured to scrape metrics from host.docker.internal:8081/actuator/prometheus inside prometheus.yml.
2. Port Mapping: The Spring Boot application runs on port 8081.


