package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.models.Wishlist;
import com.Group11.soulfulplates.payload.request.WishlistRequest;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.repository.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    void testGetWishlistById() {
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistId(1L);
        Optional<Wishlist> optionalWishlist = Optional.of(wishlist);
        when(wishlistRepository.findById(1L)).thenReturn(optionalWishlist);

        Optional<Wishlist> result = wishlistService.getWishlistById(1L);

        assertTrue(result.isPresent());
        assertEquals(wishlist, result.get());
        verify(wishlistRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveOrUpdateWishlist() {
        WishlistRequest wishlistRequest = new WishlistRequest();
        wishlistRequest.setUserId(1L);
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Wishlist savedWishlist = new Wishlist();
        savedWishlist.setWishlistId(1L);
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(savedWishlist);

        Wishlist result = wishlistService.saveOrUpdateWishlist(wishlistRequest);

        assertNotNull(result);
        assertEquals(savedWishlist, result);
        verify(userRepository, times(1)).findById(1L);
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
    }

    @Test
    void testSaveOrUpdateWishlistUserNotFound() {
        WishlistRequest wishlistRequest = new WishlistRequest();
        wishlistRequest.setUserId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> wishlistService.saveOrUpdateWishlist(wishlistRequest));
        verify(userRepository, times(1)).findById(1L);
        verify(wishlistRepository, never()).save(any(Wishlist.class));
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
