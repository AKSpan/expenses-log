package span.home.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import span.home.entities.Categories;
import span.home.entities.ExpensesAndIncomesList;
import span.home.entities.ShortExpIncData;

import java.util.Date;
import java.util.List;

/**
 * Создано Span 15.05.2017.
 */
@Repository
public interface ExpensesAndIncomesRepo extends JpaRepository<ExpensesAndIncomesList, Long> {

    @Query("select SUM(e.summary) from ExpensesAndIncomesList e where e.dateAndTime >=  :yesterday and e.dateAndTime <=:tomorrow  " +
            "and e.category_id in " +
            "(select cat.id from Categories cat where cat.user_id = " +
            "(select u.id from Users u where u.username = :username) and cat.type = 0)")
    Double getExpSumForInterval(@Param("username") String username, @Param("yesterday") Date yesterday, @Param("tomorrow") Date tomorrow);

    @Query("select e from ExpensesAndIncomesList e where e.dateAndTime >=  :yesterday and e.dateAndTime <=:tomorrow  " +
            "and e.category_id in " +
            "(select cat.id from Categories cat where cat.user_id = " +
            "(select u.id from Users u where u.username = :username) and cat.type = 0)")
    List<ExpensesAndIncomesList> getExpListForInterval(@Param("username") String username, @Param("yesterday") Date yesterday, @Param("tomorrow") Date tomorrow);


    @Query("select SUM(e.summary) from ExpensesAndIncomesList e where e.dateAndTime >=  :yesterday and e.dateAndTime <=:tomorrow  " +
            "and e.category_id in " +
            "(select cat.id from Categories cat where cat.user_id = " +
            "(select u.id from Users u where u.username = :username) and cat.type = 1)")
    Double getIncSumForInterval(@Param("username") String username, @Param("yesterday") Date yesterday, @Param("tomorrow") Date tomorrow);

    @Query("select e from ExpensesAndIncomesList e where e.dateAndTime >=  :yesterday and e.dateAndTime <=:tomorrow  " +
            "and e.category_id in " +
            "(select cat.id from Categories cat where cat.user_id = " +
            "(select u.id from Users u where u.username = :username) and cat.type = 1)")
    List<ExpensesAndIncomesList> getIncListForInterval(@Param("username") String username, @Param("yesterday") Date yesterday, @Param("tomorrow") Date tomorrow);

    @Query("select SUM(e.summary) from ExpensesAndIncomesList e where e.category_id in (select cat.id from Categories cat where cat.type = :cat_type and cat.user_id = (select u.id from Users u where u.username = :username))")
    Double getBalanceSumForMonthByType(@Param("username") String username, @Param("cat_type") byte type);

    @Query("select new span.home.entities.ShortExpIncData(e.category_id,(select c.category from Categories c where c.id = e.category_id),SUM(e.summary)) from ExpensesAndIncomesList e where e.category_id in (select c.id from Categories c where c.type = :t and c.user_id = (select u.id from Users u where u.username = :username)) and e.dateAndTime >= :da and e.dateAndTime <= :db group by e.category_id")
    List<ShortExpIncData> getDataByCategories(@Param("username") String username, @Param("t") byte type,  @Param("da") Date da, @Param("db") Date db);

}