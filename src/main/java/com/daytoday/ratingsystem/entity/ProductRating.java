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
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(doNotUseGetters = true)
@Document(collection = "product_rating")
public class ProductRating implements Serializable {

  private static final long serialVersionUID = 829421311300342313L;

  @Id
  @Field("id")
  private String id;

  @NotNull
  @Indexed
  @Field("product_id")
  private String productId;

  @NotNull
  @Field("customer_id")
  private String customerId;

  @Field("rating")
  private Integer rating;

  @Field("review")
  private String review;

  @CreatedDate
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @Field("created_on")
  private Date createdOn;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @LastModifiedDate
  @Field("updated_on")
  private Date updatedOn;

}
