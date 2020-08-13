package com.daytoday.ratingsystem.service;

/**
 * Created By Ashwani Singh
 * Dated: 14th Aug 2020
 */

import com.daytoday.ratingsystem.constant.RatingResponseCode;
import com.daytoday.ratingsystem.entity.Product;
import com.daytoday.ratingsystem.model.dto.ProductDTO;
import com.daytoday.ratingsystem.model.response.ProductRatingAnalytics;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import com.daytoday.ratingsystem.repository.ProductRepository;
import com.daytoday.ratingsystem.service.api.ProductRatingService;
import com.daytoday.ratingsystem.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Objects;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

  @InjectMocks
  private ProductServiceImpl productService;

  @Mock
  private ProductRatingService productRatingService;

  @Mock
  private ProductRepository productRepository;

  private ProductDTO productDTO;

  private Product product;

  private static final String PRODUCT_ID = "5f34ef6a0b6e193437e6671e";

  private static final String PRODUCT_NAME = "X-ray";

  @Before
  public void setUp() {
    productDTO = new ProductDTO();
    productDTO.setName(PRODUCT_NAME);
    productDTO.setDescription("X-ray");
    productDTO.setBasePrice(5000f);

    product = new Product();
    productDTO.setName(PRODUCT_NAME);
    productDTO.setDescription("X-ray");
    productDTO.setBasePrice(5000f);
  }

  @Test
  public void createProductSuccessTest() {
    productDTO.setId(PRODUCT_ID);
    Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
    Mockito.when(productRepository.save(product)).thenReturn(product);

    RatingResponse<ProductDTO> response = productService.createProduct(productDTO);

    Mockito.verify(productRepository).findById(PRODUCT_ID);
    Mockito.verify(productRepository).save(product);

    Assert.assertTrue(Objects.nonNull(response));
    Assert.assertEquals(response.getStatus(), RatingResponseCode.SUCCESS);
    Assert.assertTrue(Objects.nonNull(response.getResponseObject()));
    Assert.assertEquals(response.getResponseObject().getName(), PRODUCT_NAME);
  }

  @Test
  public void createFailureTest_product_not_present() {
    productDTO.setId(PRODUCT_ID);
    Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.empty());
    RatingResponse<ProductDTO> response = new RatingResponse<>(RatingResponseCode.FAILED);
    try {
      response = productService.createProduct(productDTO);
    } catch (Exception exp) {
      Mockito.verify(productRepository).findById(PRODUCT_ID);
      Assert.assertNull(response.getResponseObject());
      Assert.assertEquals(response.getStatus(), RatingResponseCode.FAILED);
      Assert.assertEquals(exp.getMessage(), RatingResponseCode.DATABASE_ERROR.getDesc());
    }
  }

  @Test
  public void createSaveFailureTest() {
    productDTO.setId(PRODUCT_ID);
    Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
    Mockito.when(productRepository.save(product)).thenThrow(new RuntimeException());
    RatingResponse<ProductDTO> response = new RatingResponse<>(RatingResponseCode.FAILED);
    try {
      response = productService.createProduct(productDTO);
    } catch (Exception exp) {
      Mockito.verify(productRepository).findById(PRODUCT_ID);
      Mockito.verify(productRepository).save(product);
      Assert.assertNull(response.getResponseObject());
      Assert.assertEquals(response.getStatus(), RatingResponseCode.FAILED);
      Assert.assertEquals(exp.getMessage(), RatingResponseCode.DATABASE_ERROR.getDesc());
    }
  }

  @Test
  public void getProductSuccessTest() {
    product.setId(PRODUCT_ID);
    product.setName(PRODUCT_NAME);
    ProductRatingAnalytics productRatingAnalytics = new ProductRatingAnalytics();
    productRatingAnalytics.setProductId(PRODUCT_ID);
    productRatingAnalytics.setAverageRating(3.5f);
    RatingResponse<ProductRatingAnalytics> analyticsRatingResponse =
        new RatingResponse<>(productRatingAnalytics, RatingResponseCode.SUCCESS);
    Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
    Mockito.when(productRatingService.getRatingAnalyticsForProduct(PRODUCT_ID))
        .thenReturn(analyticsRatingResponse);

    RatingResponse<ProductDTO> response = productService.getProduct(PRODUCT_ID);

    Mockito.verify(productRepository).findById(PRODUCT_ID);
    Mockito.verify(productRatingService).getRatingAnalyticsForProduct(PRODUCT_ID);

    Assert.assertTrue(Objects.nonNull(response));
    Assert.assertEquals(response.getStatus(), RatingResponseCode.SUCCESS);
    Assert.assertTrue(Objects.nonNull(response.getResponseObject()));
    Assert.assertEquals(3.5f, response.getResponseObject().getAverageRating(), 0.0);
    Assert.assertEquals(response.getResponseObject().getName(), PRODUCT_NAME);
  }

  @Test
  public void getProductFailureTest() {
    product.setId(PRODUCT_ID);
    Mockito.when(productRepository.findById(PRODUCT_ID)).thenThrow(new RuntimeException());
    RatingResponse<ProductDTO> response = new RatingResponse<>(RatingResponseCode.FAILED);
    try {
      response = productService.getProduct(PRODUCT_ID);
    } catch (Exception exp) {
      Mockito.verify(productRepository).findById(PRODUCT_ID);
      Assert.assertNull(response.getResponseObject());
      Assert.assertEquals(response.getStatus(), RatingResponseCode.FAILED);
      Assert.assertEquals(exp.getMessage(), RatingResponseCode.DATABASE_ERROR.getDesc());
    }
  }

  @Test
  public void getProductFailureTest_when_product_is_not_found() {
    product.setId(PRODUCT_ID);
    Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.empty());
    RatingResponse<ProductDTO> response = new RatingResponse<>(RatingResponseCode.FAILED);
    try {
      response = productService.getProduct(PRODUCT_ID);
    } catch (Exception exp) {
      Mockito.verify(productRepository).findById(PRODUCT_ID);
      Assert.assertNull(response.getResponseObject());
      Assert.assertEquals(response.getStatus(), RatingResponseCode.FAILED);
      Assert.assertEquals(exp.getMessage(), RatingResponseCode.DATA_VALIDATION_FAILED.getDesc());
    }
  }

  @Test
  public void getProductFailedToGetRating() {
    product.setId(PRODUCT_ID);
    product.setName(PRODUCT_NAME);
    ProductRatingAnalytics productRatingAnalytics = new ProductRatingAnalytics();
    productRatingAnalytics.setProductId(PRODUCT_ID);
    productRatingAnalytics.setAverageRating(3.5f);
    RatingResponse<ProductRatingAnalytics> analyticsRatingResponse =
        new RatingResponse<>(productRatingAnalytics,
            RatingResponseCode.FAILED_IN_GETTING_RATING_ANALYTICS);
    Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
    Mockito.when(productRatingService.getRatingAnalyticsForProduct(PRODUCT_ID))
        .thenReturn(analyticsRatingResponse);

    RatingResponse<ProductDTO> response = productService.getProduct(PRODUCT_ID);

    Mockito.verify(productRepository).findById(PRODUCT_ID);
    Mockito.verify(productRatingService).getRatingAnalyticsForProduct(PRODUCT_ID);

    Assert.assertTrue(Objects.nonNull(response));
    Assert.assertEquals(response.getStatus(), RatingResponseCode.SUCCESS);
    Assert.assertTrue(Objects.nonNull(response.getResponseObject()));
    Assert.assertNull(response.getResponseObject().getAverageRating());
    Assert.assertEquals(response.getResponseObject().getName(), PRODUCT_NAME);
  }

  @Test
  public void deleteProductSuccessTest() {
    Mockito.doNothing().when(productRepository).deleteById(PRODUCT_ID);

    RatingResponse<Boolean> response = productService.deleteProduct(PRODUCT_ID);

    Mockito.verify(productRepository).deleteById(PRODUCT_ID);

    Assert.assertTrue(Objects.nonNull(response));
    Assert.assertEquals(response.getStatus(), RatingResponseCode.SUCCESS);
    Assert.assertTrue(response.getResponseObject());
  }

  public void tearDown() {
    Mockito.verifyNoMoreInteractions(productRepository, productRatingService);
  }
}
