package sec.project.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.domain.CustomUserDetails;
import sec.project.domain.User;
import sec.project.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
        CustomUserDetails cud = new CustomUserDetails(u.getUsername(), u.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")));
        cud.setDomainUserObject(u);
        return cud;
    }
}