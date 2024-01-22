package org.example.service;

import jakarta.annotation.PostConstruct;

import org.example.Logging;
import org.example.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class VehicleService {
    Logging logger=new Logging();
    private List<Vehicle> vehicles;
    @Autowired
    private TyreService tyreService;
    @Autowired
    private SpeakerService speakerService;

    @PostConstruct
    public void initialize(){
        vehicles=new ArrayList<>();
        generatevehicles();
    }
    public void generatevehicles(){
        boolean flag = true;
        for(int i = 0 ; i < 20 ; i++){
            if(i%2 == 0){
                if(flag){
                    vehicles.add(new Vehicle(tyreService.bridgestonetyre(),speakerService.sonyspeaker(),Math.random()*100000+tyreService.bridgestonetyre().getPrice()+speakerService.sonyspeaker().getPrice()));
                }
                else {
                    vehicles.add(new Vehicle(tyreService.bridgestonetyre(),speakerService.bosespeaker(),Math.random()*100000+tyreService.bridgestonetyre().getPrice()+speakerService.bosespeaker().getPrice()));
                }
                flag = !flag;
            }
            else {
                if(flag){
                    vehicles.add(new Vehicle(tyreService.mrftyre(),speakerService.sonyspeaker(),Math.random()*100000+tyreService.mrftyre().getPrice()+speakerService.sonyspeaker().getPrice()));
                }
                else {
                    vehicles.add(new Vehicle(tyreService.mrftyre(),speakerService.bosespeaker(),Math.random()*100000+tyreService.mrftyre().getPrice()+speakerService.bosespeaker().getPrice()));
                }
            }
        }
        for(Vehicle v: vehicles){
            logger.logInfo(v.getPrice()+" "+v.getSpeaker().getBrand()+" "+v.getTyre().getBrand());
        }
    }

    public Vehicle mostExpensive(){
        return vehicles.stream()
                .max(Comparator.comparingDouble(Vehicle::getPrice))
                .orElse(null);
    }
}
