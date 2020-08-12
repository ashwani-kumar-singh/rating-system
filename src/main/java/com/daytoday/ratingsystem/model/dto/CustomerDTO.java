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
public class CustomerDTO {

  @NotNull
  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("middle_name")
  private String middleName;

  @NotNull
  @JsonProperty("last_name")
  private String lastName;

  @NotNull
  @JsonProperty("phone_number")
  private Integer phoneNumber;

  @NotNull
  @JsonProperty("country_code")
  private String countryCode;

  @NotNull
  private String email;

  private String address;

  @JsonProperty("zip_code")
  private Integer zipCode;

}
