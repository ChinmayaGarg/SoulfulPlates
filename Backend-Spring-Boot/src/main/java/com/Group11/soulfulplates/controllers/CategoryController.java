package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Category;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.payload.response.OrderDetailsResponse;
import com.Group11.soulfulplates.payload.response.OrdersResponse;
import com.Group11.soulfulplates.services.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public ResponseEntity getAllByStoreId() {
        try {
            List<Category> categories = categoryService.getAllByStoreId();
            return ResponseEntity.ok(new MessageResponse(1, "Category fetched.", categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OrderDetailsResponse(-1,
                    "Error creating category: " + e.getMessage(), null));
        }
    }

    @PostMapping("/categories")
    public ResponseEntity createCategory(@RequestBody Category category) {
        try {
            Category createdCategory = categoryService.createCategory(category);
            return ResponseEntity.ok(new MessageResponse(1, "Category created.", createdCategory));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OrderDetailsResponse(-1,
                    "Error creating category: " + e.getMessage(), null));
        }
    }

    // Update Category
    @PutMapping("/categories/{categoryId}")
    public ResponseEntity updateCategory(@PathVariable Long categoryId, @RequestBody Category updatedCategory) {
        try {
            Category category = categoryService.updateCategory(categoryId, updatedCategory);
            return ResponseEntity.ok(new MessageResponse(1, "Category updated.", category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OrderDetailsResponse(-1,
                    "Error updating category: " + e.getMessage(), null));
        }
    }

    // Delete Category
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok(new MessageResponse(1, "Category deleted.", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OrderDetailsResponse(-1,
                    "Error deleting category: " + e.getMessage(), null));
        }
    }


}
