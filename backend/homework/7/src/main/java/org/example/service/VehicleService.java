package org.example.service;


import org.example.Logging;
import org.example.entities.Vehicle;
import org.springframework.stereotype.Component;


@Component
abstract class VehicleService {
    Logging logger=new Logging();


    public abstract void generatevehicles();

    public abstract Vehicle mostExpensive();
    public abstract Vehicle leastExpensive();
}
