package kdu.assessment.assessment2.config;

import kdu.assessment.assessment2.entity.User;
import kdu.assessment.assessment2.service.PersonService;
import kdu.assessment.assessment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom authentication manager for Spring Security.
 */
@Component
public class CustomAuthenticationManager implements AuthenticationProvider {

    @Autowired
    PersonService personService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Authenticates a user based on username and password.
     *
     * @param authentication An Authentication object representing the user's authentication request.
     * @return An Authentication object if authentication is successful.
     * @throws AuthenticationException If authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        User person = personService.getPersonUsername(username);

        if(person == null){
            throw new BadCredentialsException("No user registered with this details!");
        }else{
            if (passwordEncoder.matches(pwd, person.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(person.getRole()));
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }
    }

    /**
     * Checks if this AuthenticationProvider supports the specified authentication object.
     *
     * @param authentication The class of the Authentication object.
     * @return True if this AuthenticationProvider supports the authentication object, false otherwise.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    /**
     * Retrieves granted authorities based on user role.
     *
     * @param role The role of the user.
     * @return A list of GrantedAuthority objects.
     */
    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return grantedAuthorities;
    }
}
