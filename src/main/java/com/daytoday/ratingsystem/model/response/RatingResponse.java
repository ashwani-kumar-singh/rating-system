package com.daytoday.ratingsystem.model.response;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.constant.RatingResponseCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatingResponse<T> {

  RatingResponseCode status;

  T responseObject;

  @JsonIgnore
  private HttpStatus statusCode;

  public RatingResponse() {
    this.statusCode = HttpStatus.OK;
  }

  public RatingResponse(RatingResponseCode status) {
    this();
    this.status = status;
  }

  public RatingResponse(T responseObject, RatingResponseCode status) {
    this();
    this.responseObject = responseObject;
    this.status = status;
  }
}