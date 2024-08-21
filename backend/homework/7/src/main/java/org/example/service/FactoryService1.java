package org.example.service;

import org.example.entities.InventoryStore;
import org.example.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;

public class FactoryService1 extends VehicleService{

    @Autowired
    private TyreService tyreService;
    @Autowired
    private SpeakerService speakerService;

    private final InventoryStore inventoryStore;

    @Autowired
    public FactoryService1(InventoryStore inventoryStore) {
        this.inventoryStore = inventoryStore;
    }


    @Override
    public void generatevehicles(){
        boolean flag = true;
        for(int i = 0 ; i < 20 ; i++){
            if(i%2 == 0){
                if(flag){
                    inventoryStore.getVehicles().add(new Vehicle(tyreService.bridgestonetyre(),speakerService.sonyspeaker(),1.1*Math.random()*100000+tyreService.bridgestonetyre().getPrice()+speakerService.sonyspeaker().getPrice()));
                }
                else {
                    inventoryStore.getVehicles().add(new Vehicle(tyreService.bridgestonetyre(),speakerService.bosespeaker(),1.1*Math.random()*100000+tyreService.bridgestonetyre().getPrice()+speakerService.bosespeaker().getPrice()));
                }
                flag = !flag;
            }
            else {
                if(flag){
                    inventoryStore.getVehicles().add(new Vehicle(tyreService.mrftyre(),speakerService.sonyspeaker(),1.1*Math.random()*100000+tyreService.mrftyre().getPrice()+speakerService.sonyspeaker().getPrice()));
                }
                else {
                    inventoryStore.getVehicles().add(new Vehicle(tyreService.mrftyre(),speakerService.bosespeaker(),1.1*Math.random()*100000+tyreService.mrftyre().getPrice()+speakerService.bosespeaker().getPrice()));
                }
            }
        }
        for(Vehicle v:  inventoryStore.getVehicles()){
            logger.logInfo(v.getPrice()+" "+v.getSpeaker().getBrand()+" "+v.getTyre().getBrand());
        }
    }

    @Override
    public Vehicle mostExpensive(){
        return  inventoryStore.getVehicles().stream()
                .max(Comparator.comparingDouble(Vehicle::getPrice))
                .orElse(null);
    }
    @Override
    public Vehicle leastExpensive(){
        return  inventoryStore.getVehicles().stream()
                .min(Comparator.comparingDouble(Vehicle::getPrice))
                .orElse(null);
    }
}
