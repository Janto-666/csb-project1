package sec.project.domain;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public User getDomainUserObject() {
        return user;
    }

    public void setDomainUserObject(User u) {
        user = u;
    }
}