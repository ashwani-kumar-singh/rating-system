package com.daytoday.ratingsystem.constant;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(doNotUseGetters = true)
@AllArgsConstructor
public enum RatingResponseCode {

  SUCCESS(1000, "SUCCESS"),
  FAILED(1002, "FAILED"),
  DATA_VALIDATION_FAILED(1003, "DATA_VALIDATION_FAILED");

  private final int code;
  private final String desc;

}
