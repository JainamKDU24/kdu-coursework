package com.example.springexercise5.dao;

import com.example.springexercise5.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@Data
@AllArgsConstructor
public class PersonDAO {
    List<Person> personList;

    public PersonDAO() {
        this.personList = new ArrayList<>();
    }

    public void addPerson(Person person){
        personList.add(person);
    }

    public List<Person> getAllPersons(){
        return personList;
    }


}
