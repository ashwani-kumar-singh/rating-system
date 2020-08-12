package com.daytoday.ratingsystem.service.api;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.model.dto.ProductRatingDTO;
import com.daytoday.ratingsystem.model.response.ProductRatingAnalytics;
import com.daytoday.ratingsystem.model.response.RatingResponse;

import javax.validation.constraints.NotNull;

public interface ProductRatingService {

  RatingResponse<Boolean> submitRating(@NotNull(message = "product rating request cannot be null")
      ProductRatingDTO productRatingDTO);

  RatingResponse<ProductRatingAnalytics> getRatingAnalyticsForProduct(
      @NotNull(message = "product id cannot be null") String productId);
}
