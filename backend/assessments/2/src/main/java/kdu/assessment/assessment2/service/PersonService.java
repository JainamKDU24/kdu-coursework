package kdu.assessment.assessment2.service;


import kdu.assessment.assessment2.dao.UserDAO;
import kdu.assessment.assessment2.dto.UserResponseDTO;
import kdu.assessment.assessment2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Person entities.
 */
@Service
public class PersonService {

    @Autowired
    UserDAO personDAO;

    /**
     * Adds a person to the database.
     *
     * @param user The Person object to add.
     */
    public void addPerson(User user){
        personDAO.getUserList().add(user);
    }

    /**
     * Retrieves all persons from the database.
     *
     * @return A list of all persons.
     */
    public List<User> getAll(){
        return personDAO.getUserList();
    }

    /**
     * Retrieves a person by username from the database.
     *
     * @param email The email of the person to retrieve.
     * @return The Person object corresponding to the username, or null if not found.
     */
    public User getPersonUsername(String email){
        for(User p : personDAO.getAllPersons()){
            if(p.getEmail().equals(email)){
                return p;
            }
        }
        return null;
    }

    /**
     * Retrieves detailed information about a person by username.
     *
     * @param email The email of the person.
     * @return A PersonResponseDTO object containing information about the person.
     */
    public UserResponseDTO getPerson(String email) {
        for (User v : personDAO.getUserList()) {
            if (v.getEmail().equalsIgnoreCase(email)) {
                return UserResponseDTO.builder()
                        .fullName(v.getFullName())
                        .email(v.getEmail())
                        .role(v.getRole())
                        .build();
            }
        }
        return null;
    }
}
