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
@Document(collection = "customer")
public class Customer implements Serializable {

  private static final long serialVersionUID = 839421411300342313L;

  @Id
  @Field("id")
  private String id;

  @NotNull
  @Field("first_name")
  private String firstName;

  @Field("middle_name")
  private String middleName;

  @NotNull
  @Field("last_name")
  private String lastName;

  @NotNull
  @Field("phone_number")
  private Integer phoneNumber;

  @NotNull
  @Field("country_code")
  private String countryCode;

  @NotNull
  @Indexed(unique=true)
  @Field("email")
  private String email;

  @Field("address")
  private String address;

  @Field("zip_code")
  private Integer zipCode;

  @CreatedDate
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @Field("created_on")
  private Date createdOn;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @LastModifiedDate
  @Field("updated_on")
  private Date updatedOn;

}
