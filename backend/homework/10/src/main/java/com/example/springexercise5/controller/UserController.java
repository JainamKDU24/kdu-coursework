package com.example.springexercise5.controller;

import com.example.springexercise5.dao.PersonDAO;
import com.example.springexercise5.dto.PersonResponseDTO;
import com.example.springexercise5.entity.Person;
import com.example.springexercise5.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonDAO personDAO;

    @GetMapping("/user/all")
    public List<Person> getAll(){
        return personService.getAll();
    }

    @GetMapping("/user/search")
    public PersonResponseDTO getUser(@RequestParam String name){
        return personService.getPerson(name);
    }

    @PostMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody Person person){
        personService.addPerson(person);
        return ResponseEntity.ok("User added");
    }
}
