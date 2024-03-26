package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Category;
import com.Group11.soulfulplates.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(Long categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
        return category;
    }

    public Category updateCategory(Long categoryId, Category updatedCategory) {
        Category category = findById(categoryId);
        category.setCategoryName(updatedCategory.getCategoryName());
        category.setStoreId(updatedCategory.getStoreId());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        Category category = findById(categoryId);
        categoryRepository.delete(category);
    }

    public List<Category> getAllByStoreId() {
        return categoryRepository.findAll();
    }

}


