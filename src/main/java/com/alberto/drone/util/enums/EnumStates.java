package com.alberto.drone.util.enums;

public enum EnumStates {

    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

    public final String value;

    EnumStates(String value){
        this.value = value;
    }

}
