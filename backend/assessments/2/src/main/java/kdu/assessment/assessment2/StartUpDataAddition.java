package kdu.assessment.assessment2;

import kdu.assessment.assessment2.dao.UserDAO;
import kdu.assessment.assessment2.entity.User;
import kdu.assessment.assessment2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for adding startup data to the application.
 */
@Service
public class StartUpDataAddition implements CommandLineRunner {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Adds startup data to the application.
     *
     * @param args The command line arguments (not used).
     * @throws Exception If an exception occurs during startup data addition.
     */
    @Override
    public void run(String... args) throws Exception {
        userDAO.addPerson(new User(4, "Jainam", "jainam@gmail.com", passwordEncoder.encode("jainam"),"ROLE_ADMIN"));
        userRepository.save(new User(4, "Jainam", "jainam@gmail.com", passwordEncoder.encode("jainam"),"ROLE_ADMIN"));
        userDAO.addPerson(new User(4, "Ram Sharma", "ram@gmail.com", passwordEncoder.encode("ram"),"ROLE_USER"));
        userRepository.save(new User(5, "Ram Sharma", "ram@gmail.com", passwordEncoder.encode("ram"),"ROLE_USER"));
    }
}
