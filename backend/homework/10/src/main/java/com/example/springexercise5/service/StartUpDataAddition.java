package com.example.springexercise5.service;

import com.example.springexercise5.dao.PersonDAO;
import com.example.springexercise5.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StartUpDataAddition implements CommandLineRunner {

    @Autowired
    PersonDAO personDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        personDAO.addPerson(new Person("Rahul", "rahul", passwordEncoder.encode("Testing123"),"rahul@gmail.com", "ROLE_ADMIN"));
        personDAO.addPerson(new Person("Ajay", "ajay", passwordEncoder.encode("Testing123"),"ajay@gmail.com", "ROLE_BASIC"));

    }
}
