package com.Group11.soulfulplates.repository;


import com.Group11.soulfulplates.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.subcategories WHERE c.storeId = :storeId")
    List<Category> findAllCategoriesWithSubcategoriesByStoreId(Long storeId);
}