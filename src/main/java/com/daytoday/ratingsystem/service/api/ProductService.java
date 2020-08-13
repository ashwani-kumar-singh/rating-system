package com.daytoday.ratingsystem.service.api;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.model.dto.ProductDTO;
import com.daytoday.ratingsystem.model.response.RatingResponse;

import javax.validation.constraints.NotNull;

public interface ProductService {

  /**
   * Service: To create/update product for below request
   * @param productDTO i.e product create/update request object
   * @return RatingResponse<ProductDTO>
   */
  RatingResponse<ProductDTO> createProduct(
      @NotNull(message = "product dto request cannot be null") ProductDTO productDTO);

  /**
   * Service: To get product details for given product id.
   * @param productId i.e. product id.
   * @return RatingResponse<ProductDTO>
   */
  RatingResponse<ProductDTO> getProduct(
      @NotNull(message = "product id cannot be null") String productId);

  /**
   * Service: To delete product for given product id.
   * @param productId i.e. product id.
   * @return RatingResponse<Boolean>
   */
  RatingResponse<Boolean> deleteProduct(
      @NotNull(message = "product id cannot be null") String productId);
}
