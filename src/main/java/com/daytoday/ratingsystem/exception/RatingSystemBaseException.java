package com.daytoday.ratingsystem.exception;

/**
 * Created By: Ashwani Singh
 * Dated: 13th August 2020
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RatingSystemBaseException extends RuntimeException {

  public RatingSystemBaseException(String error){
    super(error);
  }
}
