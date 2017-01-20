package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sec.project.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e JOIN FETCH e.organizer LEFT JOIN FETCH e.attendees WHERE e.id = (:id)")
    public Event findByIdAndFetchAttendees(@Param("id") Long eventId);

}
