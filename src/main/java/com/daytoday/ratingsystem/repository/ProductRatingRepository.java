package com.daytoday.ratingsystem.repository;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.entity.ProductRating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRatingRepository extends MongoRepository<ProductRating, String>,
    ProductRatingRepositoryCustom {

  ProductRating findByCustomerIdAndProductId(String productId, String customerId);
}
