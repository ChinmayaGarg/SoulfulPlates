package com.Group11.soulfulplates.repository;


import com.Group11.soulfulplates.models.Category;
import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.models.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {


    @Query(nativeQuery = true, value = "SELECT * FROM subcategories where category_id = :categoryId")
    List<Subcategory> getAllSubCategoriesByCategory(Long categoryId);

}
