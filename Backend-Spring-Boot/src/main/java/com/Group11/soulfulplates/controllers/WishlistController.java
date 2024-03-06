package com.Group11.soulfulplates.controllers;
import com.Group11.soulfulplates.models.Wishlist;
import com.Group11.soulfulplates.payload.request.WishlistRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.impl.WishlistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistServiceImpl wishlistService;

    @PreAuthorize("hasRole('ROLE_BUYER')")
    @GetMapping("/getAll")
    public List<Wishlist> getAllWishlists() {
        return wishlistService.getAllWishlists();
    }

    @PreAuthorize("hasRole('ROLE_BUYER')")
    @GetMapping( "/{id}")
    public ResponseEntity<MessageResponse> getWishlistById(@PathVariable Long id) {
        Optional<Wishlist> wishlist = wishlistService.getWishlistById(id);
        return wishlist.map(value -> ResponseEntity.ok(new MessageResponse(1, "Wishlist found", value))).orElseGet(() -> ResponseEntity.ok(new MessageResponse(-1, "Wishlist not found", null)));
    }

//    Needs Improvement
    @PreAuthorize("hasRole('ROLE_BUYER')")
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createWishlist(@RequestBody WishlistRequest wishlistRequest) {
        System.out.println(wishlistRequest);
        Wishlist createdWishlist = wishlistService.saveOrUpdateWishlist(wishlistRequest);
        if (createdWishlist != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(1, "Wishlist created", createdWishlist));
        } else {
            return ResponseEntity.ok(new MessageResponse(-1, "Failed to create wishlist", null));
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<MessageResponse> updateWishlist(@PathVariable Long id, @RequestBody WishlistRequest wishlistRequest) {
//        // Ensure the wishlistRequest has an ID set
//        wishlistRequest.setId(id);
//        Wishlist updatedWishlist = wishlistService.saveOrUpdateWishlist(wishlistRequest);
//        if (updatedWishlist != null) {
//            return ResponseEntity.ok(new MessageResponse(1, "Wishlist updated", updatedWishlist));
//        } else {
//            return ResponseEntity.ok(new MessageResponse(-1, "Failed to update wishlist", null));
//        }
//    }

    @PreAuthorize("hasRole('ROLE_BUYER')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<MessageResponse> deleteWishlist(@PathVariable Long id) {
        boolean deleted = wishlistService.deleteWishlist(id);
        if (deleted) {
            return ResponseEntity.ok(new MessageResponse(1, "Wishlist deleted", null));
        } else {
            return ResponseEntity.ok(new MessageResponse(-1, "Failed to delete wishlist", null));
        }
    }
}
