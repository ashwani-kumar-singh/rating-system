package com.daytoday.ratingsystem.entity;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */
import com.daytoday.ratingsystem.constant.RatingConstants;
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
@Document(collection = RatingConstants.CUSTOMER_COLLECTION)
public class Customer implements Serializable {

  private static final long serialVersionUID = 839421411300342313L;

  @Id
  @Field(RatingConstants.ID)
  private String id;

  @NotNull
  @Field(RatingConstants.FIRST_NAME)
  private String firstName;

  @Field(RatingConstants.MIDDLE_NAME)
  private String middleName;

  @NotNull
  @Field(RatingConstants.LAST_NAME)
  private String lastName;

  @NotNull
  @Field(RatingConstants.PHONE_NUMBER)
  private String phoneNumber;

  @NotNull
  @Field(RatingConstants.COUNTRY_CODE)
  private String countryCode;

  @NotNull
  @Indexed(unique=true)
  @Field(RatingConstants.CUSTOMER_EMAIL)
  private String email;

  @Field(RatingConstants.CUSTOMER_ADDRESS)
  private String address;

  @Field(RatingConstants.ZIP_CODE)
  private Integer zipCode;

  @CreatedDate
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @Field(RatingConstants.CREATED_ON)
  private Date createdOn;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @LastModifiedDate
  @Field(RatingConstants.UPDATED_ON)
  private Date updatedOn;

}
