package com.example.springexercise5.dao;

import com.example.springexercise5.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing Person entities.
 */
@Repository
@Data
@AllArgsConstructor
public class PersonDAO {
    List<Person> personList;

    /**
     * Default constructor that initializes an empty list of persons.
     */
    public PersonDAO() {
        this.personList = new ArrayList<>();
    }

    /**
     * Adds a person to the list of persons.
     *
     * @param person The person object to add.
     */
    public void addPerson(Person person) {
        personList.add(person);
    }

    /**
     * Retrieves all persons from the list.
     *
     * @return A list of all persons.
     */
    public List<Person> getAllPersons() {
        return personList;
    }
}
