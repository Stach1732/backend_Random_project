package com.example.rps.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "audit_events")
public class AuditEvent {

    @Id
    private String id;

    private String eventType;

    private String username;

    private String details;

    private LocalDateTime timestamp;

    public AuditEvent() {
        this.timestamp = LocalDateTime.now();
    }

    public AuditEvent(String eventType, String username, String details) {
        this.eventType = eventType;
        this.username = username;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
