package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Subcategory;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.impl.SubcategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SubCategoryControllerTest {

    @Mock
    private SubcategoryServiceImpl subcategoryService;

    @InjectMocks
    private SubCategoryController subCategoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCategory() {
        // Mock data
        Subcategory request = new Subcategory();
        request.setSubCategoryName("Test Category");
        request.setCategoryId(1l);

        // Mock service method
        when(subcategoryService.createSubcategory(any(Subcategory.class))).thenReturn(request);

        // Call controller method
        ResponseEntity<?> responseEntity = subCategoryController.createSubcategory(request);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, ((MessageResponse) responseEntity.getBody()).getCode());
        assertEquals("Subcategory created.", ((MessageResponse) responseEntity.getBody()).getDescription());
        assertEquals(request, ((MessageResponse) responseEntity.getBody()).getData());
    }

    @Test
    void testEditCategory() {
        // Mock data
        Long subCategoryId = 1L;
        Subcategory request = new Subcategory();
        request.setCategoryId(subCategoryId);
        request.setSubCategoryName("Updated Test Category");

        // Mock service method
        when(subcategoryService.findById(subCategoryId)).thenReturn(request);

        // Call controller method
        ResponseEntity<?> responseEntity = subCategoryController.updateSubcategory( subCategoryId,request);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, ((MessageResponse) responseEntity.getBody()).getCode());
        assertEquals("Subcategory updated.", ((MessageResponse) responseEntity.getBody()).getDescription());
    }

    @Test
    void testDeleteCategory() {
        // Mock data
        Long subCategoryId = 1L;

        // Mock service method
        doNothing().when(subcategoryService).deleteSubcategory(subCategoryId);
        when(subcategoryService.findById(subCategoryId)).thenReturn(new Subcategory());

        // Call controller method
        ResponseEntity<?> responseEntity = subCategoryController.deleteSubcategory(subCategoryId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, ((MessageResponse) responseEntity.getBody()).getCode());
        assertEquals("Subcategory deleted.", ((MessageResponse) responseEntity.getBody()).getDescription());
        assertNull(((MessageResponse) responseEntity.getBody()).getData());

        // Verify service method invocation
        verify(subcategoryService, times(1)).deleteSubcategory(subCategoryId);
    }

    @Test
    void testGetAllSubCategories() {

        // Mock service method
        when(subcategoryService.getAllSubCategories()).thenReturn( new ArrayList<>());

        // Call controller method
        ResponseEntity<?> responseEntity = subCategoryController.getAllSubCategories();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, ((MessageResponse) responseEntity.getBody()).getCode());
        assertEquals("Subcategory fetched.", ((MessageResponse) responseEntity.getBody()).getDescription());
        assertEquals(new ArrayList<>(),((MessageResponse) responseEntity.getBody()).getData());
    }

}
