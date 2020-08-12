package com.daytoday.ratingsystem.entity;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(doNotUseGetters = true)
@Document(collection = "customer")
public class Customer implements Serializable {

  private static final long serialVersionUID = 839421411300342313L;

  @Id
  @Field("id")
  private String id;

  @Field("first_name")
  private String firstName;

  @Field("middle_name")
  private String middleName;

  @Field("last_name")
  private String lastName;

  @Field("phone_number")
  private Integer phoneNumber;

  @Field("country_code")
  private String countryCode;

  @Field("email")
  private String email;

  @Field("address")
  private String address;

  @Field("zip_code")
  private Integer zipCode;

  @Field("created_on")
  @CreatedDate
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date createdOn;

  @Field("updated_on")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @LastModifiedDate
  private Date updatedOn;

}
