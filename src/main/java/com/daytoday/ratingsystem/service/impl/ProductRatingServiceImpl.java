package com.daytoday.ratingsystem.service.impl;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.constant.RatingResponseCode;
import com.daytoday.ratingsystem.entity.Product;
import com.daytoday.ratingsystem.entity.ProductRating;
import com.daytoday.ratingsystem.model.dto.ProductRatingDTO;
import com.daytoday.ratingsystem.model.response.ProductRatingAnalytics;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import com.daytoday.ratingsystem.repository.ProductRatingRepository;
import com.daytoday.ratingsystem.service.api.ProductRatingService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Object;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Slf4j
@Validated
@Service
public class ProductRatingServiceImpl implements ProductRatingService {

  @Autowired
  private ProductRatingRepository productRatingRepository;

  @Override
  public RatingResponse<Boolean> submitRating(ProductRatingDTO productRatingDTO) {
    log.debug("submit rating for request:{}", productRatingDTO);
    RatingResponse<Boolean> ratingResponse = new RatingResponse<>(RatingResponseCode.FAILED);
    ProductRating productRating = productRatingRepository
        .findByCustomerIdAndProductId(productRatingDTO.getCustomerId(),
            productRatingDTO.getProductId());
    if (Objects.isNull(productRating)) {
      productRating = new ProductRating();
      BeanUtils.copyProperties(productRatingDTO, productRating);
      productRatingRepository.save(productRating);
    } else {
      BeanUtils.copyProperties(productRatingDTO, productRating);
      productRatingRepository.save(productRating);
    }
    ratingResponse.setResponseObject(Boolean.TRUE);
    ratingResponse.setStatus(RatingResponseCode.SUCCESS);
    return ratingResponse;
  }

  @Override
  public RatingResponse<ProductRatingAnalytics> getRatingAnalyticsForProduct(String productId) {
    log.debug("get rating analytics for productId:{}", productId);
    RatingResponse<ProductRatingAnalytics> ratingResponse =
        new RatingResponse<>(RatingResponseCode.FAILED);
    // TODO 1. logic to get average rating for single product
    //  2. count per rating
    ratingResponse.setStatus(RatingResponseCode.SUCCESS);
    return ratingResponse;
  }

}
