package com.daytoday.ratingsystem.entity;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(doNotUseGetters = true)
@Document(collection = "product_rating_analytics")
public class ProductRatingAnalytics implements Serializable {

  private static final long serialVersionUID = 829421311311782313L;

  @Id
  @Field("id")
  private String id;

  @Indexed
  @Field("product_id")
  private String productId;

  @Field("average_rating")
  private Float averageRating;

  @Field("rating_statistics")
  private Map<Float, Integer> ratingStatistics;

}
