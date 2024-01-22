package org.example.entities;

public class Vehicle {

    private Tyre tyre;
    private Speaker speaker;
    private double price;

    public Vehicle(Tyre tyre, Speaker speaker, double price) {
        this.tyre = tyre;
        this.speaker = speaker;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public Speaker getSpeaker() {
        return speaker;
    }
}
