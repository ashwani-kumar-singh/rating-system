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

  /**
   * Service: create/update rating review for below request.
   * @param productRatingDTO i.e. product rating request object.
   * @return RatingResponse<Boolean>
   */
  RatingResponse<Boolean> submitRating(@NotNull(message = "product rating request cannot be null")
      ProductRatingDTO productRatingDTO);

  /**
   * Service: To get rating analytics for given product.
   * @param productId i.e. product id.
   * @return RatingResponse<ProductRatingAnalytics>
   */
  RatingResponse<ProductRatingAnalytics> getRatingAnalyticsForProduct(
      @NotNull(message = "product id cannot be null") String productId);
}
