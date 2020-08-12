package com.daytoday.ratingsystem.service.api;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import com.daytoday.ratingsystem.model.dto.ProductDTO;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface ProductService {

  RatingResponse<ProductDTO> createProduct(
      @NotNull(message = "product dto request cannot be null") ProductDTO productDTO);

  RatingResponse<Page<ProductDTO>> getPaginatedProducts( Pageable pageable);

  RatingResponse<ProductDTO> getProduct(
      @NotNull(message = "product id cannot be null") String productId);

  RatingResponse<Boolean> deleteProduct(
      @NotNull(message = "product id cannot be null") String productId);
}
