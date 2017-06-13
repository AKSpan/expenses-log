package span.home.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import span.home.entities.Users;

import javax.transaction.Transactional;

/**
 * Создано Span 18.04.2017.
 */
@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("update Users u set u.last_session = :session where u.username = :username")
//    void setUserSessionID(@Param("session") String session, @Param("username") String username);
//
//    @Query("select u.id from Users u where u.last_session = :session")
//    Long getUserIDbySession(@Param("session") String session);
//
//    @Query("update Users u set u.last_session = '' where u.last_session = :session")
//    void clearSession(@Param("session") String sess);
}
