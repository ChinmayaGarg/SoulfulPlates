package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.payload.request.CreateRatingRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/createrating")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> addRating(@RequestBody(required = false) CreateRatingRequest request) {
        try {
            ratingService.addRatingAndLinkToOrder(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(1, "Rating Added.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(-1, e.getMessage(), null));
        }

    }

    @GetMapping("/average/{storeId}")
    public ResponseEntity<?> getAverageRating(@PathVariable Long storeId) {
        try {
            double averageRating = ratingService.getAverageRating(storeId);
            return ResponseEntity.ok().body(averageRating);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(-1, e.getMessage(), null));
        }
    }
}
