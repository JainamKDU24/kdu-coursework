package com.example.Springexercise3.DTO.Request;
import lombok.NonNull;

/**
 * RequestDTO for storing the JSON data pushed in the API call
 */
public class VehicleRequestDTO {

    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String tyre;
    @NonNull
    private String  speaker;
    @NonNull
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
