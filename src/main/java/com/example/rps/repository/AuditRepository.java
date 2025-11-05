package com.example.rps.repository;

import com.example.rps.model.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AuditRepository extends MongoRepository<AuditEvent, String> {}
