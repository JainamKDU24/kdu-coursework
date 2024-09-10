package com.kdu.smarthome.service;

import com.kdu.smarthome.exception.custom.NotFound;
import com.kdu.smarthome.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo=userRepo;
    }
    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The UserDetails representation of the user.
     * @throws UsernameNotFoundException If the user with the specified username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findById(username).orElseThrow(()-> new NotFound("User does not exist"));
    }
}
