package kdu.assessment.assessment2.dao;

import kdu.assessment.assessment2.entity.User;
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
public class UserDAO {
    List<User> userList;

    /**
     * Default constructor that initializes an empty list of persons.
     */
    public UserDAO() {
        this.userList = new ArrayList<>();
    }

    /**
     * Adds a person to the list of persons.
     *
     * @param user The person object to add.
     */
    public void addPerson(User user) {
        userList.add(user);
    }

    /**
     * Retrieves all persons from the list.
     *
     * @return A list of all persons.
     */
    public List<User> getAllPersons() {
        return userList;
    }
}
