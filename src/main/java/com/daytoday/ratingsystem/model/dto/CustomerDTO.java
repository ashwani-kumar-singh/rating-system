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
public class CustomerDTO {

  @JsonProperty(RatingConstants.ID)
  private String id;

  @NotNull
  @JsonProperty(RatingConstants.FIRST_NAME)
  private String firstName;

  @JsonProperty(RatingConstants.MIDDLE_NAME)
  private String middleName;

  @NotNull
  @JsonProperty(RatingConstants.LAST_NAME)
  private String lastName;

  @NotNull
  @JsonProperty(RatingConstants.PHONE_NUMBER)
  private String phoneNumber;

  @NotNull
  @JsonProperty(RatingConstants.COUNTRY_CODE)
  private String countryCode;

  @NotNull
  private String email;

  private String address;

  @JsonProperty(RatingConstants.ZIP_CODE)
  private Integer zipCode;

}
