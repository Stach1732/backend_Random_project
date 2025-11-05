package com.example.rps.repository;

import com.example.rps.model.GameRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GameRepository extends MongoRepository<GameRecord, String> {}
