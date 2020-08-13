package com.daytoday.ratingsystem.entity;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.constant.RatingConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(doNotUseGetters = true)
@Document(collection = RatingConstants.PRODUCT_RATING)
public class ProductRating implements Serializable {

  private static final long serialVersionUID = 829421311300342313L;

  @Id
  @Field(RatingConstants.ID)
  private String id;

  @NotNull
  @Indexed
  @Field(RatingConstants.PRODUCT_ID)
  private String productId;

  @NotNull
  @Field(RatingConstants.CUSTOMER_ID)
  private String customerId;

  @Field(RatingConstants.RATING)
  private Integer rating;

  @Field(RatingConstants.REVIEW)
  private String review;

  @CreatedDate
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @Field(RatingConstants.CREATED_ON)
  private Date createdOn;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @LastModifiedDate
  @Field(RatingConstants.UPDATED_ON)
  private Date updatedOn;

}
