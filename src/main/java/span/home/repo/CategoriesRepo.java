package span.home.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import span.home.entities.Categories;

import java.util.List;

/**
 * Создано Span 04.05.2017.
 */
@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Long> {
    @Query("select c from Categories c where c.user_id = (select u.id from Users u where u.username = :username) and c.type = :type order by c.category")
    List<Categories> findAllByUserIDAndType(@Param("username") String username, @Param("type") byte type);


}
