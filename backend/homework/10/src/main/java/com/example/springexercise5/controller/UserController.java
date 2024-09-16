package com.example.springexercise5.controller;

import com.example.springexercise5.dao.PersonDAO;
import com.example.springexercise5.dto.PersonResponseDTO;
import com.example.springexercise5.entity.Person;
import com.example.springexercise5.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling user-related endpoints.
 */
@RestController
public class UserController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonDAO personDAO;

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    @GetMapping("/user/all")
    public List<Person> getAll() {
        return personService.getAll();
    }

    /**
     * Retrieves a user by name.
     *
     * @param name The name of the user to retrieve.
     * @return A PersonResponseDTO containing information about the user.
     */
    @GetMapping("/user/search")
    public PersonResponseDTO getUser(@RequestParam String name) {
        return personService.getPerson(name);
    }

    /**
     * Adds a new user.
     *
     * @param person The Person object representing the user to add.
     * @return A ResponseEntity with a message indicating successful user addition.
     */
    @PostMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok("User added");
    }
}
