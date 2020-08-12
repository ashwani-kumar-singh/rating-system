package com.daytoday.ratingsystem.controller;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.model.dto.ProductDTO;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import com.daytoday.ratingsystem.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@Slf4j
@EnableSwagger2
@RequestMapping(value = "/v1/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PutMapping("/add-product")
  public ResponseEntity<RatingResponse<ProductDTO>> createProduct(
      @RequestBody ProductDTO productDTO) {
    log.info("Got call to add/update product with request:{}", productDTO);
    RatingResponse<ProductDTO> productResponse =
        productService.createProduct(productDTO);
    log.info("Sending add/update product response for request:{}, response:{}", productDTO,
        productResponse);
    return new ResponseEntity<>(productResponse, productResponse.getStatusCode());
  }

  @GetMapping("/all")
  public ResponseEntity<RatingResponse<Page<ProductDTO>>> getPaginatedProducts(Pageable pageable) {
    log.info("Got call to get products with pageable:{}",  pageable);
    RatingResponse<Page<ProductDTO>> productResponse =
        productService.getPaginatedProducts(pageable);
    log.info("Sending get products response for pageable:{}, response: {}", pageable,
        productResponse);
    return new ResponseEntity<>(productResponse, productResponse.getStatusCode());
  }

  @GetMapping("/{product_id}")
  public ResponseEntity<RatingResponse<ProductDTO>> getProduct(@PathVariable String productId) {
    log.info("Got call to get product with productId:{}", productId);
    RatingResponse<ProductDTO> productResponse = productService.getProduct(productId);
    log.info("Sending get product response for productId:{}, response: {}", productId,
        productResponse);
    return new ResponseEntity<>(productResponse, productResponse.getStatusCode());
  }

  @DeleteMapping("/{product_id}")
  public ResponseEntity<RatingResponse<Boolean>> deleteProduct(@PathVariable String productId) {
    log.info("Got call to delete product with productId:{}", productId);
    RatingResponse<Boolean> productResponse =
        productService.deleteProduct(productId);
    log.info("Sending delete product response for productId:{}, response: {}", productId,
        productResponse);
    return new ResponseEntity<>(productResponse, productResponse.getStatusCode());
  }

}
