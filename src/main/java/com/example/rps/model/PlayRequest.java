package com.example.rps.model;

public class PlayRequest {

    //ROCK PAPER SICSSORS
    private Move move;

    private String username;

    public Move getMove(){
        return move;
    }
    public void setMove(Move move){
        this.move = move;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
}
