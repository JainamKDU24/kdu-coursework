package org.example.service;

import org.example.entities.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TyreService {
    @Bean
    Tyre mrftyre(){
        return new Tyre("MRF",15000);
    }
    @Bean
    Tyre bridgestonetyre(){
        return new Tyre("BRIDGESTONE",17000);
    }
}
