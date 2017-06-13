package span.home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import span.home.entities.Categories;
import span.home.entities.Users;
import span.home.repo.CategoriesRepo;

import java.util.List;

/**
 * Создано Span 04.05.2017.
 */
@Service
public class CategoriesService {

    private final
    CategoriesRepo categoriesRepo;

    @Autowired
    public CategoriesService(CategoriesRepo categoriesRepo) {
        this.categoriesRepo = categoriesRepo;
    }

    public List<Categories> getExpensesCategoriesList(String username) {
        //  System.out.println("USER ID: " + userID);
        return categoriesRepo.findAllByUserIDAndType(username, (byte) 0);
    }

    public List<Categories> getIncomesCategoriesList(String username) {
        //  System.out.println("USER ID: " + userID);
        return categoriesRepo.findAllByUserIDAndType(username, (byte) 1);
    }

    public Long addNewCategory(String catName, byte type,Long id) {
        Categories c = categoriesRepo.save(new Categories(catName, type, id));
        return c != null ? c.getId() : null;
    }

    public void deleteCategory(Long id) {
        categoriesRepo.delete(id);
    }


    private Sort sortByCategory() {
        return new Sort(Sort.Direction.ASC, "category");
    }


    public void initCategoriesTable() {
        categoriesRepo.save(new Categories("ex1", (byte) 0, false, new Users(1L)));
        categoriesRepo.save(new Categories("ex2", (byte) 0, true, new Users(1L)));
        categoriesRepo.save(new Categories("ex3", (byte) 0, false, new Users(1L)));
        categoriesRepo.save(new Categories("inc1", (byte) 1, true, new Users(1L)));
        categoriesRepo.save(new Categories("inc2", (byte) 1, true, new Users(1L)));
        categoriesRepo.save(new Categories("exp1", (byte) 0, true, new Users(2L)));
        categoriesRepo.save(new Categories("exp2", (byte) 1, true, new Users(2L)));
        categoriesRepo.save(new Categories("c", (byte) 1, false, new Users(2L)));
    }

}
