package spring.site.news.services;

import spring.site.news.dto.CategoryDTO;
import spring.site.news.mapper.CategoryMapper;
import spring.site.news.models.Category;
import spring.site.news.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCourseById(Long id) {
        categoryRepository.deleteById(id);
    }


    public List<CategoryDTO> getAllCategoriesDTO() {
        return categoryMapper.toCategoryDTOList(categoryRepository.findAll());
    }

}