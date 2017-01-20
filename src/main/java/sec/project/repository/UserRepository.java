package sec.project.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sec.project.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.organizedEvents LEFT JOIN FETCH u.attendedEvents WHERE u.id = (:id)")
    public User findByIdAndFetchEvents(@Param("id") Long userId);

    public Collection<User> findAllByOrderByUsername();

    public User findByUsername(String username);

}
