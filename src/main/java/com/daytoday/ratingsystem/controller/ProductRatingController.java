package com.daytoday.ratingsystem.controller;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.model.dto.ProductRatingDTO;
import com.daytoday.ratingsystem.model.response.ProductRatingAnalytics;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import com.daytoday.ratingsystem.service.api.ProductRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@Slf4j
@EnableSwagger2
@RequestMapping(value = "/v1/product-rating")
public class ProductRatingController {

  @Autowired
  private ProductRatingService productRatingService;

  @PutMapping("/submit")
  public ResponseEntity<RatingResponse<Boolean>> submitRating(
      @RequestBody ProductRatingDTO productRatingDTO) {
    log.info("Got call to submit review with request:{} ", productRatingDTO);
    RatingResponse<Boolean> ratingResponse = productRatingService.submitRating(productRatingDTO);
    log.info("Sending submit review response for request:{} with response:{}", productRatingDTO,
        ratingResponse);
    return new ResponseEntity<>(ratingResponse, ratingResponse.getStatusCode());
  }

  @GetMapping("/{productId}")
  public ResponseEntity<RatingResponse<ProductRatingAnalytics>> getRatingAnalyticsForProduct(
      @PathVariable String productId) {
    log.info("Got call to get product rating analytics with productId:{}", productId);
    RatingResponse<ProductRatingAnalytics> productResponse =
        productRatingService.getRatingAnalyticsForProduct(productId);
    log.info("Sending get product rating analytics response for productId:{}, response: {}",
        productId, productResponse);
    return new ResponseEntity<>(productResponse, productResponse.getStatusCode());
  }
}
