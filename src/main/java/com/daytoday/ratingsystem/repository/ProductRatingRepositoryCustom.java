package com.daytoday.ratingsystem.repository;

/**
 * Created By Ashwani Singh
 * Dated: 13th Aug 2020
 */

import com.daytoday.ratingsystem.model.dao.RatingAnalyticsDAO;

import java.util.List;

public interface ProductRatingRepositoryCustom {

  /**
   * Repository: To get count of each rating for a product.
   * @param productId i.e. product id.
   * @return List<RatingAnalyticsDAO>
   */
  List<RatingAnalyticsDAO> getProductRatingAnalytics(String productId);
}
