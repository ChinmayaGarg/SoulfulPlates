package com.Group11.soulfulplates.repository;


import com.Group11.soulfulplates.models.Category;
import com.Group11.soulfulplates.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubCategory, Long> {
}