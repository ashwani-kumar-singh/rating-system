package com.daytoday.ratingsystem.service.impl;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.constant.RatingResponseCode;
import com.daytoday.ratingsystem.entity.Product;
import com.daytoday.ratingsystem.model.dto.ProductDTO;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import com.daytoday.ratingsystem.repository.ProductRepository;
import com.daytoday.ratingsystem.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Slf4j
@Validated
@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public RatingResponse<ProductDTO> createProduct(ProductDTO productDTO) {
    log.debug("create/update request for product with request:{}", productDTO);
    RatingResponse<ProductDTO> productResponse = new RatingResponse<>(RatingResponseCode.FAILED);
    Product product;
    if(StringUtils.isEmpty(productDTO.getId())){
      product = new Product();
      BeanUtils.copyProperties(productDTO, product);
      product = productRepository.save(product);
      productDTO.setId(product.getId());
    } else {
      product = productRepository.findOne(productDTO.getId());
      BeanUtils.copyProperties(productDTO, product);
      productRepository.save(product);
    }
    productResponse.setStatus(RatingResponseCode.SUCCESS);
    productResponse.setResponseObject(productDTO);
    return productResponse;
  }

  @Override
  public RatingResponse<Page<ProductDTO>> getPaginatedProducts(Pageable pageable) {
    log.debug("get paginated products");
    RatingResponse<Page<ProductDTO>> productResponse =
        new RatingResponse<>(RatingResponseCode.FAILED);
    Page<Product> productDTOPage = productRepository.findAll(pageable);
    if (Objects.nonNull(productDTOPage)) {
      List<Product> product = productDTOPage.getContent();

    }
    // TODO 1. logic to get average rating for all products
    productResponse.setStatus(RatingResponseCode.SUCCESS);
    return productResponse;
  }

  @Override
  public RatingResponse<ProductDTO> getProduct(String productId) {
    log.debug("get product for product id:{}", productId);
    RatingResponse<ProductDTO> productResponse = new RatingResponse<>(RatingResponseCode.FAILED);
    ProductDTO productDTO = new ProductDTO();
    Product product = productRepository.findOne(productId);
    // TODO 1. logic to get average rating for single product
    BeanUtils.copyProperties(product, productDTO);
    productResponse.setResponseObject(productDTO);
    productResponse.setStatus(RatingResponseCode.SUCCESS);
    return productResponse;
  }

  @Override
  public RatingResponse<Boolean> deleteProduct(String productId) {
    log.debug("delete product for product id:{}", productId);
    RatingResponse<Boolean> productResponse = new RatingResponse<>(RatingResponseCode.FAILED);
    productRepository.delete(productId);
    productResponse.setStatus(RatingResponseCode.SUCCESS);
    productResponse.setResponseObject(Boolean.TRUE);
    return productResponse;
  }
}
