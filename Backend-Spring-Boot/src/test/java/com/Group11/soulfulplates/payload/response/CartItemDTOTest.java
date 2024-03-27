package com.Group11.soulfulplates.payload.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartItemDTOTest {
    @Test
    public void testGettersAndSetters() {
        CartItemDTO dto = new CartItemDTO();

        Long testUserId = 1L;
        Long testMenuId = 2L;
        int testQuantity = 3;
        String testNotes = "Special instructions";
        LocalDateTime testCreated = LocalDateTime.now();
        Long testOrderId = 4L;
        boolean testIsActive = true;

        // Setters
        dto.setUserId(testUserId);
        dto.setMenuId(testMenuId);
        dto.setQuantity(testQuantity);
        dto.setNotes(testNotes);
        dto.setCreated(testCreated);
        dto.setOrderId(testOrderId);
        dto.setIsActive(testIsActive);

        // Getters
        assertEquals(testUserId, dto.getUserId());
        assertEquals(testMenuId, dto.getMenuId());
        assertEquals(testQuantity, dto.getQuantity());
        assertEquals(testNotes, dto.getNotes());
        assertEquals(testCreated, dto.getCreated());
        assertEquals(testOrderId, dto.getOrderId());
        assertEquals(testIsActive, dto.getIsActive());
    }
}
