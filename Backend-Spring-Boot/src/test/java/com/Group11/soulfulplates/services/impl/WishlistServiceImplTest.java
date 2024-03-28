package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Wishlist;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.repository.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WishlistServiceImplTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllWishlists() {
        List<Wishlist> wishlistList = new ArrayList<>();
        when(wishlistRepository.findAll()).thenReturn(wishlistList);

        List<Wishlist> result = wishlistService.getAllWishlists();

        assertEquals(wishlistList, result);
        verify(wishlistRepository, times(1)).findAll();
    }

    @Test
    void testDeleteWishlist() {
        doNothing().when(wishlistRepository).deleteById(1L);

        boolean result = wishlistService.deleteWishlist(1L);

        assertTrue(result);
        verify(wishlistRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteWishlistFailure() {
        doThrow(RuntimeException.class).when(wishlistRepository).deleteById(1L);

        boolean result = wishlistService.deleteWishlist(1L);

        assertFalse(result);
        verify(wishlistRepository, times(1)).deleteById(1L);
    }

}
