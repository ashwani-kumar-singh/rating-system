package com.daytoday.ratingsystem.service;

/**
 * Created By Ashwani Singh
 * Dated: 14th Aug 2020
 */
import com.daytoday.ratingsystem.constant.RatingResponseCode;
import com.daytoday.ratingsystem.entity.Customer;
import com.daytoday.ratingsystem.model.dto.CustomerDTO;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import com.daytoday.ratingsystem.repository.CustomerRepository;
import com.daytoday.ratingsystem.service.impl.CustomerServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Objects;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

  @InjectMocks
  private CustomerServiceImpl customerService;

  @Mock
  private CustomerRepository customerRepository;

  private CustomerDTO customerDTO;

  private Customer customer;

  @Before
  public void setUp(){
    customerDTO = new CustomerDTO();
    customerDTO.setAddress("4449  Swick Hill Street");
    customerDTO.setCountryCode("+012");
    customerDTO.setEmail("raymonthunt@gmail.com");
    customerDTO.setFirstName("Raymond");
    customerDTO.setLastName( "Hunt");
    customerDTO.setPhoneNumber("985-247-6299");
    customerDTO.setZipCode(70001);

    customer = new Customer();
    customer.setAddress("4449  Swick Hill Street");
    customer.setCountryCode("+012");
    customer.setEmail("raymonthunt@gmail.com");
    customer.setFirstName("Raymond");
    customer.setLastName( "Hunt");
    customer.setPhoneNumber("985-247-6299");
    customer.setZipCode(70001);
  }

  @Test
  public void createCustomerSuccessTest(){
    Mockito.when(customerRepository.findByEmail(customerDTO.getEmail())).thenReturn(customer);
    Mockito.when(customerRepository.save(customer)).thenReturn(customer);

    RatingResponse<CustomerDTO> response = customerService.createCustomer(customerDTO);

    Mockito.verify(customerRepository).findByEmail(customerDTO.getEmail());
    Mockito.verify(customerRepository).save(customer);

    Assert.assertTrue(Objects.nonNull(response));
    Assert.assertEquals(response.getStatus(), RatingResponseCode.SUCCESS);
    Assert.assertTrue(Objects.nonNull(response.getResponseObject()));
    Assert.assertEquals(response.getResponseObject().getEmail(),"raymonthunt@gmail.com" );
  }

  @Test
  public void createCustomerFailureTest(){
    Mockito.when(customerRepository.findByEmail(customerDTO.getEmail())).thenReturn(customer);
    Mockito.when(customerRepository.save(customer))
        .thenThrow(new RuntimeException());
    RatingResponse<CustomerDTO> response = new RatingResponse<>(RatingResponseCode.FAILED);
    try{
      response = customerService.createCustomer(customerDTO);
    } catch (Exception exp){
      Mockito.verify(customerRepository).findByEmail(customerDTO.getEmail());
      Mockito.verify(customerRepository).save(customer);
      Assert.assertNull(response.getResponseObject());
      Assert.assertEquals(response.getStatus(), RatingResponseCode.FAILED);
      Assert.assertEquals(exp.getMessage(), RatingResponseCode.DATABASE_ERROR.getDesc());
    }
  }

  @After
  public void tearDown(){
    Mockito.verifyNoMoreInteractions(customerRepository);
  }


}
