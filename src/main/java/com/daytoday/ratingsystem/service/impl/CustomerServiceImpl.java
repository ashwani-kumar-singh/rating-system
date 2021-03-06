package com.daytoday.ratingsystem.service.impl;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.constant.RatingResponseCode;
import com.daytoday.ratingsystem.entity.Customer;
import com.daytoday.ratingsystem.exception.RatingSystemBaseException;
import com.daytoday.ratingsystem.model.dto.CustomerDTO;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import com.daytoday.ratingsystem.repository.CustomerRepository;
import com.daytoday.ratingsystem.service.api.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Slf4j
@Validated
@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public RatingResponse<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
    log.debug("create/update request for customer with request:{}", customerDTO);
    RatingResponse<CustomerDTO> customerResponse = new RatingResponse<>(RatingResponseCode.FAILED);
    Customer customer = customerRepository.findByEmail(customerDTO.getEmail());
    try {
      if (Objects.isNull(customer)) {
        customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer = customerRepository.save(customer);
        customerDTO.setId(customer.getId());
      } else {
        BeanUtils.copyProperties(customerDTO, customer);
        customerRepository.save(customer);
      }
    } catch (Exception exp) {
      log.error("failed in saving customer details in db for customerDTO:{}", customerDTO, exp);
      throw new RatingSystemBaseException(RatingResponseCode.DATABASE_ERROR.getDesc());
    }
    customerResponse.setStatus(RatingResponseCode.SUCCESS);
    customerResponse.setResponseObject(customerDTO);
    return customerResponse;
  }
}
