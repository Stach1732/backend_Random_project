package com.example.rps.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "game_records")
public class GameRecord {

    @Id
    private String id;

    private Move playerMove;

    private Move cpuMove;

    private Result result;

    private LocalDateTime timestamp;

    public GameRecord() {
        this.timestamp = LocalDateTime.now();
    }

    public GameRecord(Move playerMove, Move cpuMove, Result result) {
        this.id = UUID.randomUUID().toString();
        this.playerMove = playerMove;
        this.cpuMove = cpuMove;
        this.result = result;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Move getPlayerMove() {
        return playerMove;
    }
    public void setPlayerMove(Move playerMove) {
        this.playerMove = playerMove;
    }

    public Move getCpuMove() {
        return cpuMove;
    }
    public void setCpuMove(Move cpuMove) {
        this.cpuMove = cpuMove;
    }

    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "GameRecord{" +
                "id='" + id + '\'' +
                ", playerMove=" + playerMove +
                ", cpuMove=" + cpuMove +
                ", result=" + result +
                ", timestamp=" + timestamp +
                '}';
    }
}
