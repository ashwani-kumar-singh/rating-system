package com.daytoday.ratingsystem.model.response;

import com.daytoday.ratingsystem.constant.RatingConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class ProductRatingAnalytics {

  @JsonProperty(RatingConstants.PRODUCT_ID)
  private String productId;

  @JsonProperty(RatingConstants.AVERAGE_RATING)
  private float averageRating;

  @JsonProperty(RatingConstants.RATING_STATISTICS)
  private Map<String, Long> ratingStatistics;

}
