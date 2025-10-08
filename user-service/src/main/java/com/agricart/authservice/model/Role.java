package com.ashokagricart.authservice.model;

public enum Role {
    //Basic Roles
    USER,
    ADMIN,

    DISTRIBUTER;

    public boolean isAdmin(){
        return this == ADMIN;
    }
}
