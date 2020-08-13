package com.daytoday.ratingsystem.service.impl;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.constant.RatingResponseCode;
import com.daytoday.ratingsystem.entity.ProductRating;
import com.daytoday.ratingsystem.exception.RatingSystemBaseException;
import com.daytoday.ratingsystem.model.dao.RatingAnalyticsDAO;
import com.daytoday.ratingsystem.model.dto.ProductRatingDTO;
import com.daytoday.ratingsystem.model.response.ProductRatingAnalytics;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import com.daytoday.ratingsystem.repository.ProductRatingRepository;
import com.daytoday.ratingsystem.service.api.ProductRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Validated
@Service
public class ProductRatingServiceImpl implements ProductRatingService {

  @Autowired
  private ProductRatingRepository productRatingRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public RatingResponse<Boolean> submitRating(ProductRatingDTO productRatingDTO) {
    log.debug("submit rating for request:{}", productRatingDTO);
    RatingResponse<Boolean> ratingResponse = new RatingResponse<>(RatingResponseCode.FAILED);
    try {
      ProductRating productRating = productRatingRepository
          .findByCustomerIdAndProductId(productRatingDTO.getCustomerId(),
              productRatingDTO.getProductId());
      if (Objects.isNull(productRating)) {
        productRating = new ProductRating();
        BeanUtils.copyProperties(productRatingDTO, productRating);
        productRatingRepository.save(productRating);
        productRatingDTO.setId(productRating.getId());
      } else {
        BeanUtils.copyProperties(productRatingDTO, productRating);
        productRatingRepository.save(productRating);
      }
    } catch (Exception exp) {
      log.error("failed in saving product rating details in db for productRatingDTO:{}",
          productRatingDTO, exp);
      throw new RatingSystemBaseException(RatingResponseCode.DATABASE_ERROR.getDesc());
    }
    ratingResponse.setResponseObject(Boolean.TRUE);
    ratingResponse.setStatus(RatingResponseCode.SUCCESS);
    return ratingResponse;
  }

  @Override
  public RatingResponse<ProductRatingAnalytics> getRatingAnalyticsForProduct(String productId) {
    log.debug("get rating analytics for productId:{}", productId);
    RatingResponse<ProductRatingAnalytics> ratingAnalyticsResponse =
        new RatingResponse<>(RatingResponseCode.FAILED);
    ProductRatingAnalytics productRatingAnalytics = new ProductRatingAnalytics();
    DecimalFormat df2 = new DecimalFormat("#.#");
    df2.setRoundingMode(RoundingMode.UP);
    try {
      List<RatingAnalyticsDAO> ratingAnalyticDAOS =
          productRatingRepository.getProductRatingAnalytics(productId);
      Map<String, Long> ratingStatistics = ratingAnalyticDAOS.stream()
          .collect(Collectors.toMap(RatingAnalyticsDAO::getId, RatingAnalyticsDAO::getRatingCount));
      float avg = getAvgRating(ratingStatistics);
      productRatingAnalytics.setAverageRating(Float.valueOf(df2.format(avg)));
      productRatingAnalytics.setRatingStatistics(ratingStatistics);
      productRatingAnalytics.setProductId(productId);
    } catch (Exception exp) {
      log.error("failed in getting product rating analytics for product id:{}", productId, exp);
      throw new RatingSystemBaseException(
          RatingResponseCode.FAILED_IN_GETTING_RATING_ANALYTICS.getDesc());
    }
    ratingAnalyticsResponse.setResponseObject(productRatingAnalytics);
    ratingAnalyticsResponse.setStatus(RatingResponseCode.SUCCESS);
    return ratingAnalyticsResponse;
  }

  private float getAvgRating(Map<String, Long> ratingStatistics) {
    long sum = 0;
    long userCount = 0;
    for (Map.Entry<String, Long> entry : ratingStatistics.entrySet()) {
      sum += Long.valueOf(entry.getKey()) * entry.getValue();
      userCount += entry.getValue();
    }
    return sum / (float) userCount;
  }

}
