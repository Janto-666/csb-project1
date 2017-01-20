package sec.project.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class User extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String username;

    private String emailAddress;

    private String description;

    private String password;

    @OneToMany(mappedBy = "organizer")
    private Set<Event> organizedEvents = new HashSet();

    @ManyToMany(mappedBy = "attendees")
    private Set<Event> attendedEvents = new HashSet();

    public User() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Event> getOrganizedEvents() {
        return organizedEvents;
    }

    public void setOrganizedEvents(Set<Event> organizedEvents) {
        this.organizedEvents = organizedEvents;
    }

    public Set<Event> getAttendedEvents() {
        return attendedEvents;
    }

    public void setAttendedEvents(Set<Event> attendedEvents) {
        this.attendedEvents = attendedEvents;
    }
}