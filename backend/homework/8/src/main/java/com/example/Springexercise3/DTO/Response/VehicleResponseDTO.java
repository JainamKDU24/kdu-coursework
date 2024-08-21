package com.example.Springexercise3.DTO.Response;

import lombok.Builder;

/**
 * ResponseDTO for returining the JSON data requested in the API call
 */
@Builder
public class VehicleResponseDTO {
    private int id;
    private String name;
    private String  tyre;
    private String speaker;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTyre() {
        return tyre;
    }

    public void setTyre(String tyre) {
        this.tyre = tyre;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
