package org.example.config;

import org.example.Main;
import org.example.entities.InventoryStore;
import org.example.service.FactoryService1;
import org.example.service.FactoryService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("org.example.service")
public class Config {
    @Bean
    @Scope("prototype")
    InventoryStore inventoryStore(){
        return new InventoryStore();
    }

    @Bean("factory1")
    FactoryService1 factory1(){
        return new FactoryService1(inventoryStore());
    }
    @Bean("factory2")
    FactoryService2 factory2(){
        return new FactoryService2(inventoryStore());
    }
    @Bean("main")
    Main runner(){
        return new Main();
    }

}
