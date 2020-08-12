package com.daytoday.ratingsystem.controller;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.model.dto.CustomerDTO;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import com.daytoday.ratingsystem.service.api.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@Slf4j
@EnableSwagger2
@RequestMapping(value = "/v1/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PutMapping("/add-customer")
  public ResponseEntity<RatingResponse<CustomerDTO>> createCustomer(
      @RequestBody CustomerDTO customerDTO) {
    log.info("Got call to add/update customer details with request:{}", customerDTO);
    RatingResponse<CustomerDTO> userResponse =
        customerService.createCustomer(customerDTO);
    log.info("Sending add/update customer response for request:{}, response:{}", customerDTO,
        userResponse);
    return new ResponseEntity<>(userResponse, userResponse.getStatusCode());
  }

}
