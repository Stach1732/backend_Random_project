package com.example.rps.model;

public class RegisterRequest {

    //Usuario
    private String username;

    //Contraseña //TODO: Hashearla
    private String password;

    //texto plano
    private String email;

    //TODO: implementar niveles tras granar
    private int level;

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
}
