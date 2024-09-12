package kdu.assessment.assessment2.service;

import kdu.assessment.assessment2.entity.User;
import kdu.assessment.assessment2.exception.custom.MyCustomException;
import kdu.assessment.assessment2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save user.");
        }
    }
    @Transactional
    public void updateUser(int userId, User user) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setFullName(user.getFullName());
            existingUser.setEmail(user.getEmail());
            existingUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(existingUser);
        } else {
            throw new MyCustomException("User with ID " + userId + " does not exist.");
        }
    }
    public List<User> findAllUsers(Integer userId) {
        return userRepository.findAllById(userId);
    }
}
