package org.example;

import org.example.config.Config;
import org.example.entities.Vehicle;
import org.example.service.FactoryService1;
import org.example.service.FactoryService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    @Autowired
    @Qualifier("factory1")
    private FactoryService1 factory1;

    @Autowired
    @Qualifier("factory2")
    private FactoryService2 factory2;

    static Logging logger=new Logging();

    public static void main(String[] args) {
        var context=new AnnotationConfigApplicationContext(Config.class);
        Main main=context.getBean(Main.class);
        main.commandrunner();
    }
    void commandrunner(){
        factory1.generatevehicles();
        factory2.generatevehicles();
        Vehicle v=factory1.mostExpensive();
        Vehicle v1=factory2.mostExpensive();
        logger.logInfo("Most Expensive Vehicle for Factory 1:");
        logger.logInfo(v.getPrice()+" "+v.getTyre().getBrand()+" "+v.getSpeaker().getBrand());
        logger.logInfo("Most Expensive Vehicle for Factory 2:");
        logger.logInfo(v1.getPrice()+" "+v1.getTyre().getBrand()+" "+v1.getSpeaker().getBrand());
        v=factory1.leastExpensive();
        v1=factory2.leastExpensive();
        logger.logInfo("Least Expensive Vehicle for Factory 1:");
        logger.logInfo(v.getPrice()+" "+v.getTyre().getBrand()+" "+v.getSpeaker().getBrand());
        logger.logInfo("Least Expensive Vehicle for Factory 2:");
        logger.logInfo(v1.getPrice()+" "+v1.getTyre().getBrand()+" "+v1.getSpeaker().getBrand());

    }
}