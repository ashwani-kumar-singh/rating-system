package com.daytoday.ratingsystem.model.dto;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.constant.RatingConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(doNotUseGetters = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

  @JsonProperty(RatingConstants.ID)
  private String id;

  @NotNull
  private String name;

  private String description;

  @NotNull
  @JsonProperty(RatingConstants.BASE_PRICE)
  private Float basePrice;

  @JsonProperty(RatingConstants.AVERAGE_RATING)
  private Float averageRating;

}
