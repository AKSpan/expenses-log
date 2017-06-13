package span.home.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import span.home.entities.*;
import span.home.services.CategoriesService;
import span.home.services.ExpensesAndIncomesService;
import span.home.services.UsersService;

import java.text.ParseException;
import java.util.List;

/**
 * Создано Span 10.04.2017.
 */
@RestController
public class IndexController {


    private final CategoriesService categoriesService;
    private final UsersService usersService;
    private final ExpensesAndIncomesService expensesAndIncomesService;

    @Autowired
    public IndexController(CategoriesService categoriesService, UsersService usersService, ExpensesAndIncomesService expensesAndIncomesService) {
        this.expensesAndIncomesService = expensesAndIncomesService;
        this.categoriesService = categoriesService;
        this.usersService = usersService;
    }


    @RequestMapping(value = "/log/main-info", method = RequestMethod.GET, produces = "application/json")
    public JournalInfoAnswer userJournalInfo() {
        String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        JournalInfoAnswer res = null;
        try {
            res = new JournalInfoAnswer(
                    expensesAndIncomesService.getExpensesSumForToday(username),
                    expensesAndIncomesService.getExpensesSumForWeek(username),
                    expensesAndIncomesService.getExpensesSumForMonth(username),
                    expensesAndIncomesService.getIncomesSumForMonth(username),
                    expensesAndIncomesService.getBalanceForYear(username));

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "/log/getCategories", method = RequestMethod.GET, produces = "application/json")
    private List<Categories> getUserCategories(@RequestParam("type") int type) {
        String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        switch (type) {
            case Categories.EXPENSE_TYPE:
                return categoriesService.getExpensesCategoriesList(username);
            case Categories.INCOME_TYPE:
                return categoriesService.getIncomesCategoriesList(username);
        }
        return null;
    }

    @RequestMapping(value = "/log/addCategory", method = RequestMethod.POST, produces = "application/json")
    private Long addNewCategory(@RequestParam("name") String catName, @RequestParam("type") byte type) {
        String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Users users = usersService.getUserByUsername(username);
        return categoriesService.addNewCategory(catName, type, users.getId());
    }

    @RequestMapping(value = "/log/getBalanceForYear", method = RequestMethod.GET, produces = "application/json")
    private BalanceCostByMonth getBalanceForYear(@RequestParam("year") int year) {
        String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return expensesAndIncomesService.getBalanceForYear(year, username);
    }

    @RequestMapping(value = "/log/deleteCategory", method = RequestMethod.POST, produces = "application/json")
    private void deleteCategory(@RequestParam("id") Long id) {
        categoriesService.deleteCategory(id);
    }

    @RequestMapping(value = "/log/save", method = RequestMethod.POST)
    private int save(@RequestBody ExpensesAndIncomesList expensesData) {
        return expensesAndIncomesService.saveExpenseData(expensesData);
    }
    @RequestMapping(value = "/log/getDataByCategories", method = RequestMethod.POST)
    private List<ShortExpIncData> getDataByCategories(@RequestBody RequestForAllExpAndInc requestForAllExpAndInc) {
        String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return expensesAndIncomesService.getDataByCategories(username,requestForAllExpAndInc);
    }

}