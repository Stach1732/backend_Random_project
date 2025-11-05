package com.example.rps.service;

import com.example.rps.model.AuditEvent;
import com.example.rps.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private static final Logger log = LoggerFactory.getLogger(AuditService.class);

    private final AuditRepository repository;

    public AuditService(AuditRepository repository) {
        this.repository = repository;
    }

    public void record(String eventType, String username, String details) {
        System.out.println("ha llegado a audit");
        AuditEvent event = new AuditEvent(eventType, username, details);
        repository.save(event);
    }
}
