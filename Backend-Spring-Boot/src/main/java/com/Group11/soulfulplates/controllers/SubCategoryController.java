package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Subcategory;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.impl.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SubCategoryController {
    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("/getSubCategories")
    public ResponseEntity getAllSubCategories() {
        try {
            List<Subcategory> subcategories = subcategoryService.getAllSubCategories();
            return ResponseEntity.ok(new MessageResponse(1, "Subcategory fetched.", subcategories));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(-1,
                    "Error creating subcategories: " + e.getMessage(), null));
        }
    }

    // Insert Subcategory
    @PostMapping("/subcategories")
    public ResponseEntity createSubcategory(@RequestBody Subcategory subcategory) {
        try {
            Subcategory createdSubcategory = subcategoryService.createSubcategory(subcategory);
            return ResponseEntity.ok(new MessageResponse(1, "Subcategory created.", createdSubcategory));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(-1,
                    "Error creating subcategory: " + e.getMessage(), null));
        }
    }

    // Update Subcategory
    @PutMapping("/subcategories/{subcategoryId}")
    public ResponseEntity updateSubcategory(@PathVariable Long subcategoryId, @RequestBody Subcategory updatedSubcategory) {
        try {
            Subcategory subcategory = subcategoryService.updateSubcategory(subcategoryId, updatedSubcategory);
            return ResponseEntity.ok(new MessageResponse(1, "Subcategory updated.", subcategory));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(-1,
                    "Error updating subcategory: " + e.getMessage(), null));
        }
    }

    // Delete Subcategory
    @DeleteMapping("/subcategories/{subcategoryId}")
    public ResponseEntity deleteSubcategory(@PathVariable Long subcategoryId) {
        try {
            subcategoryService.deleteSubcategory(subcategoryId);
            return ResponseEntity.ok(new MessageResponse(1, "Subcategory deleted.", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(-1,
                    "Error deleting subcategory: " + e.getMessage(), null));
        }
    }


}
