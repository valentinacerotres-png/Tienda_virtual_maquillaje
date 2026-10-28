package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonTypeId;

public class Usuario {

    private Long id;

    private String nombre;
    private String email;
    private String rol;
    private String token;

    public Usuario() {}

    public Usuario(String nombre, String email, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getRol() {return rol;}
    public void setRol(String rol) {this.rol = rol;}

    public String getToken() {return token;}
    public void setToken(String token) {this.token = token;}
}
