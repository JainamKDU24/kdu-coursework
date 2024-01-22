package org.example.service;

import org.example.entities.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpeakerService {
    @Bean
    Speaker sonyspeaker(){
        return new Speaker("SONY",3500);
    }

    @Bean
    Speaker bosespeaker(){
        return new Speaker("BOSE",6000);
    }
}
