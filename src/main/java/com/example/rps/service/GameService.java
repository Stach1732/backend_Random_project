package com.example.rps.service;

import com.example.rps.model.*;
import com.example.rps.repository.GameRepository;
import com.example.rps.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository repository;

    private final AuditService auditService;

    private final UserRepository userRepository;

    public GameService(GameRepository repo, AuditService auditService, UserRepository userRepository) {
        this.repository = repo;
        this.auditService = auditService;
        this.userRepository = userRepository;
    }

    public GameRecord play(Move playerMove, String username) {
        Random random = new Random();
        Move cpuMove = Move.values()[random.nextInt(Move.values().length)];
        Result result = calculateResult(playerMove, cpuMove);

        GameRecord record = new GameRecord(playerMove, cpuMove, result);
        repository.save(record);

        System.out.println("ha llegado");
        //if wins, level up (todo change it to a more complex way to level up)
        if(result.equals(Result.WIN)&& userRepository.findByUsername(username).isPresent()){
            System.out.println("pero aqui no ha llegado");
            User userPlaying = userRepository.findByUsername(username).get();
            userPlaying.setLevel((userPlaying.getLevel())+1);
            userRepository.save(userPlaying);
        }

        return record;
    }

    private Result calculateResult(Move player, Move cpu) {
        if (player == cpu) return Result.DRAW;
        return switch (player) {
            case ROCK -> (cpu == Move.SCISSORS) ? Result.WIN : Result.LOSE;
            case PAPER -> (cpu == Move.ROCK) ? Result.WIN : Result.LOSE;
            case SCISSORS -> (cpu == Move.PAPER) ? Result.WIN : Result.LOSE;
        };
    }
}
