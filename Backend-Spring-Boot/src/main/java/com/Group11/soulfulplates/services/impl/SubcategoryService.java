package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Subcategory;
import com.Group11.soulfulplates.repository.SubcategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public Subcategory createSubcategory(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }


    public Subcategory findById(Long subCategoryId){
        Subcategory subcategory = subcategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Subcategory not found with id: " + subCategoryId));
        return subcategory;
    }


    public Subcategory updateSubcategory(Long subcategoryId, Subcategory updatedSubcategory) {
        Subcategory subcategory = findById(subcategoryId);
        subcategory.setSubCategoryName(updatedSubcategory.getSubCategoryName());
        subcategory.setCategoryId(updatedSubcategory.getCategoryId());
        return subcategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(Long subcategoryId) {
        Subcategory subcategory = findById(subcategoryId);
        subcategoryRepository.delete(subcategory);
    }

    public List<Subcategory> getAllSubCategories() {
        return subcategoryRepository.findAll();
    }

}


