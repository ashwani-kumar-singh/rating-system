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
@Document(collection = RatingConstants.PRODUCT_COLLECTION)
public class Product implements Serializable {

  private static final long serialVersionUID = 829421411300342313L;

  @Id
  @Field(RatingConstants.ID)
  private String id;

  @NotNull
  @Field(RatingConstants.PRODUCT_NAME)
  private String name;

  @Field(RatingConstants.PRODUCT_DESCRIPTION)
  private String description;

  @NotNull
  @Field(RatingConstants.BASE_PRICE)
  private Float basePrice;

  @CreatedDate
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @Field(RatingConstants.CREATED_ON)
  private Date createdOn;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @LastModifiedDate
  @Field(RatingConstants.UPDATED_ON)
  private Date updatedOn;
}
