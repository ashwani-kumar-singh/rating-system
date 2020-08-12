package com.daytoday.ratingsystem.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ProductRatingAnalytics {

  @JsonProperty("product_id")
  private String productId;

  @JsonProperty("average_rating")
  private Float averageRating;

  @JsonProperty("rating_statistics")
  private Map<Float, Integer> ratingStatistics;

}
