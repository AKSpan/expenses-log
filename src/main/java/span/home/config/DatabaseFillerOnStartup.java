package span.home.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import span.home.services.*;

/**
 * Created by Alexey on 14.05.2017 21:20.
 */

@Component
public class DatabaseFillerOnStartup implements ApplicationListener<ContextRefreshedEvent> {
    private final
    UsersService usersService;
    private final
    RolesService rolesService;
    private final
    CategoriesService categoriesService;
    private final ExpensesAndIncomesService expensesAndIncomesService;

    @Autowired
    public DatabaseFillerOnStartup(UsersService usersService, RolesService rolesService, CategoriesService categoriesService, ExpensesAndIncomesService expensesAndIncomesService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.categoriesService = categoriesService;
        this.expensesAndIncomesService = expensesAndIncomesService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        System.out.println("onApplicationEvent");
//        rolesService.initRoleTable();
//        usersService.initUsersTable();
//        categoriesService.initCategoriesTable();
//        expensesAndIncomesService.initExpensesAndIncomesTable();


    }
}