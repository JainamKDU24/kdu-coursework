package kdu.assessment.assessment2.controller;

import kdu.assessment.assessment2.entity.User;
import kdu.assessment.assessment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam Integer id) {
        List<User> users = userService.findAllUsers(id);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserDetails(@PathVariable Integer id, @RequestBody User user){
        userService.updateUser(id, user);
        return ResponseEntity.ok("User details updated successfully");
    }
}
