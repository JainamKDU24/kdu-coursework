package com.example.springexercise5.service;

import com.example.springexercise5.dao.PersonDAO;
import com.example.springexercise5.dto.PersonResponseDTO;
import com.example.springexercise5.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Person entities.
 */
@Service
public class PersonService {

    @Autowired
    PersonDAO personDAO;

    /**
     * Adds a person to the database.
     *
     * @param person The Person object to add.
     */
    public void addPerson(Person person){
        personDAO.getPersonList().add(person);
    }

    /**
     * Retrieves all persons from the database.
     *
     * @return A list of all persons.
     */
    public List<Person> getAll(){
        return personDAO.getPersonList();
    }

    /**
     * Retrieves a person by username from the database.
     *
     * @param name The username of the person to retrieve.
     * @return The Person object corresponding to the username, or null if not found.
     */
    public Person getPersonUsername(String name){
        for(Person p : personDAO.getAllPersons()){
            if(p.getUsername().equals(name)){
                return p;
            }
        }
        return null;
    }

    /**
     * Retrieves detailed information about a person by username.
     *
     * @param name The username of the person.
     * @return A PersonResponseDTO object containing information about the person.
     */
    public PersonResponseDTO getPerson(String name) {
        for (Person v : personDAO.getPersonList()) {
            if (v.getUsername().equalsIgnoreCase(name)) {
                return PersonResponseDTO.builder()
                        .name(v.getName())
                        .email(v.getEmail())
                        .role(v.getRole())
                        .build();
            }
        }
        return null;
    }
}
