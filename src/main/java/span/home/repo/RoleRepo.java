package span.home.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import span.home.entities.Users_role;

/**
 * Created by Alexey on 14.05.2017 21:41.
 */
@Repository
public interface RoleRepo extends JpaRepository<Users_role, Long> {
}
