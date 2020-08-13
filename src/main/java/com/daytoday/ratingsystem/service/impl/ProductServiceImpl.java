package com.daytoday.ratingsystem.service.impl;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.constant.RatingResponseCode;
import com.daytoday.ratingsystem.entity.Product;
import com.daytoday.ratingsystem.exception.RatingSystemBaseException;
import com.daytoday.ratingsystem.model.dto.ProductDTO;
import com.daytoday.ratingsystem.model.response.ProductRatingAnalytics;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import com.daytoday.ratingsystem.repository.ProductRepository;
import com.daytoday.ratingsystem.service.api.ProductRatingService;
import com.daytoday.ratingsystem.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Validated
@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRatingService productRatingService;

  @Autowired
  private ProductRepository productRepository;

  @Override
  public RatingResponse<ProductDTO> createProduct(ProductDTO productDTO) {
    log.debug("create/update request for product with request:{}", productDTO);
    RatingResponse<ProductDTO> productResponse = new RatingResponse<>(RatingResponseCode.FAILED);
    Product product;
    try{
      if(StringUtils.isEmpty(productDTO.getId())){
        product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        product = productRepository.save(product);
        productDTO.setId(product.getId());
      } else {
        Optional<Product> productOptional = productRepository.findById(productDTO.getId());
        if(productOptional.isPresent()){
          product = productOptional.get();
          BeanUtils.copyProperties(productDTO, product);
          productRepository.save(product);
        } else {
          log.error("failed in getting product for product id:{}", productDTO.getId());
          throw new RatingSystemBaseException(RatingResponseCode.DATA_VALIDATION_FAILED.getDesc());
        }
      }
    } catch (Exception exp){
      log.error("failed in saving product details in db for productDTO:{}", productDTO, exp);
      throw new RatingSystemBaseException(RatingResponseCode.DATABASE_ERROR.getDesc());
    }
    productResponse.setStatus(RatingResponseCode.SUCCESS);
    productResponse.setResponseObject(productDTO);
    return productResponse;
  }

  @Override
  public RatingResponse<ProductDTO> getProduct(String productId) {
    log.debug("get product for product id:{}", productId);
    RatingResponse<ProductDTO> productResponse = new RatingResponse<>(RatingResponseCode.FAILED);
    ProductDTO productDTO = new ProductDTO();
    Optional<Product> productOptional = productRepository.findById(productId);
    if(productOptional.isPresent()){
      RatingResponse<ProductRatingAnalytics> ratingResponse =
          productRatingService.getRatingAnalyticsForProduct(productId);
      BeanUtils.copyProperties(productOptional.get(), productDTO);
      if (RatingResponseCode.SUCCESS.equals(ratingResponse.getStatus()) && Objects
          .nonNull(ratingResponse.getResponseObject())) {
        productDTO.setAverageRating(ratingResponse.getResponseObject().getAverageRating());
      }
    } else {
      log.error("failed in getting product for product id:{}", productId);
      throw new RatingSystemBaseException(RatingResponseCode.DATA_VALIDATION_FAILED.getDesc());
    }
    productResponse.setResponseObject(productDTO);
    productResponse.setStatus(RatingResponseCode.SUCCESS);
    return productResponse;
  }

  @Override
  public RatingResponse<Boolean> deleteProduct(String productId) {
    log.debug("delete product for product id:{}", productId);
    RatingResponse<Boolean> productResponse =
        new RatingResponse<>(Boolean.FALSE, RatingResponseCode.FAILED);
    productRepository.deleteById(productId);
    productResponse.setStatus(RatingResponseCode.SUCCESS);
    productResponse.setResponseObject(Boolean.TRUE);
    return productResponse;
  }
}
