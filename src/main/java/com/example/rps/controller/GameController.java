package com.example.rps.controller;

import com.example.rps.model.*;
import com.example.rps.service.GameService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {

    private final GameService service;

    public GameController(GameService service) { this.service = service; }

    @PostMapping("/play")
    public GameRecord play(@RequestBody PlayRequest request) {
        return service.play(request.getMove(), request.getUsername());
    }
}
