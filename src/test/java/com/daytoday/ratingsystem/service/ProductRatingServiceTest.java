package com.daytoday.ratingsystem.service;

/**
 * Created By Ashwani Singh
 * Dated: 14th Aug 2020
 */

import com.daytoday.ratingsystem.constant.RatingResponseCode;
import com.daytoday.ratingsystem.entity.ProductRating;
import com.daytoday.ratingsystem.model.dao.RatingAnalyticsDAO;
import com.daytoday.ratingsystem.model.dto.ProductRatingDTO;
import com.daytoday.ratingsystem.model.response.ProductRatingAnalytics;
import com.daytoday.ratingsystem.model.response.RatingResponse;
import com.daytoday.ratingsystem.repository.ProductRatingRepository;
import com.daytoday.ratingsystem.service.impl.ProductRatingServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RunWith(MockitoJUnitRunner.class)
public class ProductRatingServiceTest {

  @InjectMocks
  private ProductRatingServiceImpl productRatingService;

  @Mock
  private ProductRatingRepository productRatingRepository;

  private ProductRatingDTO productRatingDTO;

  private ProductRating productRating;

  private static final String PRODUCT_ID = "5f34ef6a0b6e193437e6671e";

  private static final Integer PRODUCT_RATING = 3;

  private static final String CUSTOMER_ID = "5f34ec1f0b6e1932ec4d6bb3";

  @Before
  public void setUp() {
    productRatingDTO = new ProductRatingDTO();
    productRatingDTO.setCustomerId(CUSTOMER_ID);
    productRatingDTO.setProductId(PRODUCT_ID);
    productRatingDTO.setReview("Good to Have");
    productRatingDTO.setRating(PRODUCT_RATING);

    productRating = new ProductRating();
    productRating.setCustomerId(CUSTOMER_ID);
    productRating.setProductId(PRODUCT_ID);
    productRating.setReview("Good to Have");
    productRating.setRating(PRODUCT_RATING);

  }

  @Test
  public void submitRatingSuccessTest() {
    productRating.setId("5f34f4390b6e193625e66e6a");
    Mockito.when(productRatingRepository.findByCustomerIdAndProductId(CUSTOMER_ID, PRODUCT_ID))
        .thenReturn(productRating);
    Mockito.when(productRatingRepository.save(productRating)).thenReturn(productRating);

    RatingResponse<Boolean> response = productRatingService.submitRating(productRatingDTO);

    Mockito.verify(productRatingRepository).findByCustomerIdAndProductId(CUSTOMER_ID, PRODUCT_ID);
    Mockito.verify(productRatingRepository).save(productRating);

    Assert.assertTrue(Objects.nonNull(response));
    Assert.assertEquals(response.getStatus(), RatingResponseCode.SUCCESS);
    Assert.assertTrue(response.getResponseObject());
  }

  @Test
  public void submitRatingFailureTest() {
    productRating.setId("5f34f4390b6e193625e66e6a");
    Mockito.when(productRatingRepository.findByCustomerIdAndProductId(CUSTOMER_ID, PRODUCT_ID))
        .thenReturn(productRating);
    Mockito.when(productRatingRepository.save(productRating)).thenThrow(new RuntimeException());
    RatingResponse<Boolean> response =
        new RatingResponse<>(Boolean.FALSE, RatingResponseCode.FAILED);
    try {
      response = productRatingService.submitRating(productRatingDTO);
    } catch (Exception exp) {
      Mockito.verify(productRatingRepository).findByCustomerIdAndProductId(CUSTOMER_ID, PRODUCT_ID);
      Mockito.verify(productRatingRepository).save(productRating);
      Assert.assertFalse(response.getResponseObject());
      Assert.assertEquals(response.getStatus(), RatingResponseCode.FAILED);
      Assert.assertEquals(exp.getMessage(), RatingResponseCode.DATABASE_ERROR.getDesc());
    }
  }

  @Test
  public void getRatingAnalyticsForProductSuccessTest() {
    RatingAnalyticsDAO dao1 = new RatingAnalyticsDAO();
    dao1.setId("3");
    dao1.setRatingCount(5L);
    RatingAnalyticsDAO dao2 = new RatingAnalyticsDAO();
    dao2.setId("4");
    dao2.setRatingCount(3L);
    List<RatingAnalyticsDAO> ratingAnalyticsDAOList = new ArrayList<>();
    ratingAnalyticsDAOList.add(dao1);
    ratingAnalyticsDAOList.add(dao2);
    Mockito.when(productRatingRepository.getProductRatingAnalytics(PRODUCT_ID))
        .thenReturn(ratingAnalyticsDAOList);

    RatingResponse<ProductRatingAnalytics> response =
        productRatingService.getRatingAnalyticsForProduct(PRODUCT_ID);

    Mockito.verify(productRatingRepository).getProductRatingAnalytics(PRODUCT_ID);

    Assert.assertTrue(Objects.nonNull(response));
    Assert.assertTrue(Objects.nonNull(response.getResponseObject()));
    Assert.assertEquals(response.getStatus(), RatingResponseCode.SUCCESS);
    Assert.assertEquals(response.getResponseObject().getProductId(), PRODUCT_ID);
    Assert.assertTrue(response.getResponseObject().getAverageRating() == 3.4f);
    Assert.assertFalse(CollectionUtils.isEmpty(response.getResponseObject().getRatingStatistics()));
    Assert.assertEquals(response.getResponseObject().getRatingStatistics().size(), 2);
    Assert.assertTrue(response.getResponseObject().getRatingStatistics().get("3") == 5f);
    Assert.assertTrue(response.getResponseObject().getRatingStatistics().get("4") == 3f);
  }

  @Test
  public void getRatingAnalyticsForProductFailureTest() {
    Mockito.when(productRatingRepository.getProductRatingAnalytics(PRODUCT_ID))
        .thenThrow(new RuntimeException());
    RatingResponse<ProductRatingAnalytics> response =
        new RatingResponse<>(RatingResponseCode.FAILED);
    try {
      response = productRatingService.getRatingAnalyticsForProduct(PRODUCT_ID);
    } catch (Exception exp) {
      Mockito.verify(productRatingRepository).getProductRatingAnalytics(PRODUCT_ID);
      Assert.assertNull(response.getResponseObject());
      Assert.assertEquals(response.getStatus(), RatingResponseCode.FAILED);
      Assert.assertEquals(exp.getMessage(),
          RatingResponseCode.FAILED_IN_GETTING_RATING_ANALYTICS.getDesc());
    }
  }

  @After
  public void tearDown() {
    Mockito.verifyNoMoreInteractions(productRatingRepository);
  }
}
