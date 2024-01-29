package com.example.springexercise5.service;

import com.example.springexercise5.dao.PersonDAO;
import com.example.springexercise5.dto.PersonResponseDTO;
import com.example.springexercise5.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonDAO personDAO;

    public void addPerson(Person person){
        personDAO.getPersonList().add(person);
    }

    public List<Person> getAll(){
        return personDAO.getPersonList();
    }

    public Person getPersonUsername(String name){
        for(Person p : personDAO.getAllPersons()){
            if(p.getUsername().equals(name)){
                return p;
            }
        }
        return null;
    }

    public PersonResponseDTO getPerson(String name) {
        for (Person v : personDAO.getPersonList()) {
            if (v.getUsername().equalsIgnoreCase(name)) {
                System.out.println(v.getUsername());
                return PersonResponseDTO.builder()
                        .name(v.getName())
                        .email(v.getEmail())
                        .role(v.getRole()    )
                        .build();
            }
        }
        return null;
    }
}
