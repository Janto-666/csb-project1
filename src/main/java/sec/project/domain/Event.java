package sec.project.domain;

import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Event extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String eventName;

    private Date eventDate;

    @ManyToOne
    @JoinColumn(name="organizer_id", nullable=false, updatable=false)
    private User organizer;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "event_attendees", 
        joinColumns = {
            @JoinColumn(name = "event_id", nullable = false, updatable = false) 
        },
	inverseJoinColumns = { 
            @JoinColumn(name = "user_id", nullable = false, updatable = false) 
        }
    )
    private Collection<User> attendees;

    public Event() {
        super();
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Collection<User> getAttendees() {
        return attendees;
    }

    public void addAttendee(User attendee) {
        this.attendees.add(attendee);
    }
}