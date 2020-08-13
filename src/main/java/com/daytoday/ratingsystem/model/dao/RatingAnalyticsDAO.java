package com.daytoday.ratingsystem.model.dao;

/**
 * Created By: Ashwani Singh
 * Dated: 13th August 2020
 */

import com.daytoday.ratingsystem.constant.RatingConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class RatingAnalyticsDAO {

  @Field(RatingConstants.ID)
  private String id;

  @Field(RatingConstants.RATING_COUNT)
  private Long ratingCount;

}
