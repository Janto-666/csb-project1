package sec.project.repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository("insecureEventRepository")
public class LegacyEventRepository {

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(String eventName, Date eventDate, Long organizingUserId) {
        String queryString = String.format(
                "insert into event(event_name, event_date, organizer_id) " +
                "values('%s', '%s', %s)", eventName, df.format(eventDate), organizingUserId);
        Query q = em.createNativeQuery(queryString);
        q.executeUpdate();
    }

    @Transactional
    public void saveSecure(String eventName, Date eventDate, Long organizingUserId) {
        String queryString = 
                "insert into event(event_name, event_date, organizer_id) " +
                "values(:name, :date, :organizer)";
        Query q = em.createQuery(queryString);
        q.setParameter("name", eventName);
        q.setParameter("date", eventDate);
        q.setParameter("organizer", organizingUserId);
        q.executeUpdate();
    }
}