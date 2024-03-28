package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.models.Wishlist;
import com.Group11.soulfulplates.payload.request.WishlistRequest;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    public Optional<Wishlist> getWishlistById(Long id) {
        return wishlistRepository.findById(id);
    }

    public Wishlist saveOrUpdateWishlist(WishlistRequest wishlistRequest) {
        Wishlist wishlist = new Wishlist();
        // Populate wishlist fields from the request
        wishlist.setCreatedDate(wishlistRequest.getCreatedDate());

        // Fetch the user based on userId from the repository
        Optional<User> userOptional = userRepository.findById(wishlistRequest.getUserId());
        if (userOptional.isPresent()) {
            // Set the user for the wishlist
            wishlist.setUser(userOptional.get());
        } else {
            // Handle the case when the user is not found
            throw new RuntimeException("User not found with ID: " + wishlistRequest.getUserId());
        }

        return wishlistRepository.save(wishlist);
    }

    public boolean deleteWishlist(Long id) {
        try {
            wishlistRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
