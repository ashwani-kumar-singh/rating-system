package com.daytoday.ratingsystem.service.api;

import com.daytoday.ratingsystem.model.dto.CustomerDTO;
import com.daytoday.ratingsystem.model.response.RatingResponse;

import javax.validation.constraints.NotNull;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

public interface CustomerService {

  RatingResponse<CustomerDTO> createCustomer(
      @NotNull(message = "customer dto can not be null") CustomerDTO customerDTO);
}
