package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.payload.request.CreateRatingRequest;

import java.util.Map;

public interface RatingService {
    void addRatingAndLinkToOrder(CreateRatingRequest ratingData) throws Exception;
    double getAverageRating(Long storeId);
}