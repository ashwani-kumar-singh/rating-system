package com.daytoday.ratingsystem.service.api;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.model.dto.CustomerDTO;
import com.daytoday.ratingsystem.model.response.RatingResponse;

import javax.validation.constraints.NotNull;


public interface CustomerService {

  /**
   * Service: To create/update customer for below request
   * @param customerDTO i.e customer create/update request object
   * @return RatingResponse<CustomerDTO>
   */
  RatingResponse<CustomerDTO> createCustomer(
      @NotNull(message = "customer dto can not be null") CustomerDTO customerDTO);
}
