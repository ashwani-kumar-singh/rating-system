package com.daytoday.ratingsystem.model.dto;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

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
public class ProductRatingDTO {

  @NotNull
  @JsonProperty("product_id")
  private String productId;

  @NotNull
  @JsonProperty("customer_id")
  private String customerId;

  @NotNull
  private Integer rating;

  private String review;


}
