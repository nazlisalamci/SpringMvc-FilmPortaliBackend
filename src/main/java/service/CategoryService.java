package service;

import com.google.gson.Gson;
import dao.CategoryRepository;
import dao.MainDao;
import lombok.RequiredArgsConstructor;
import model.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MainDao mainDao;

    public List<Category> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Transactional(readOnly = false)
    public Boolean create(String name, String description) {
        Category category = new Category();
        category.setDescription(description);
        category.setName(name);
        try {
            mainDao.saveObject(category);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    @Transactional
    public  Category update(String strCategory)
    {
        Gson gson = new Gson();
        Category category = gson.fromJson(strCategory, Category.class);
        mainDao.updateObject(category);
        return category;
    }

    public Category getByID(Long id) {
        Category category = new Category();
        try {
            category = (Category) mainDao.loadObject(Category.class, id);
        }catch (Exception e){
            category = null;
        }

        return category;
    }
}
