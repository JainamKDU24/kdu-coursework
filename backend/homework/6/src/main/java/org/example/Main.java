package org.example;


import org.example.config.Config;
import org.example.entities.Vehicle;
import org.example.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static Logging logger=new Logging();
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(Config.class);
        VehicleService vehicleService=context.getBean(VehicleService.class);
        Vehicle vehicle=vehicleService.mostExpensive();
        logger.logInfo("Most Expensive Vehicle:");
        logger.logInfo(vehicle.getPrice()+" "+vehicle.getSpeaker().getBrand()+" "+vehicle.getTyre().getBrand());
    }
}