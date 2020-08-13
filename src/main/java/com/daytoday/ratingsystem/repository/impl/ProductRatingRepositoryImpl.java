package com.daytoday.ratingsystem.repository.impl;

/**
 * Created By Ashwani Singh
 * Dated: 13th Aug 2020
 */

import com.daytoday.ratingsystem.constant.RatingConstants;
import com.daytoday.ratingsystem.entity.ProductRating;
import com.daytoday.ratingsystem.model.dao.RatingAnalyticsDAO;
import com.daytoday.ratingsystem.repository.ProductRatingRepositoryCustom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductRatingRepositoryImpl implements ProductRatingRepositoryCustom {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public List<RatingAnalyticsDAO> getProductRatingAnalytics(String productId) {
    log.debug("get product rating analytics for productId:{}", productId);
    MatchOperation matchOperation = Aggregation.match(new Criteria()
        .andOperator(Criteria.where(RatingConstants.PRODUCT_ID).is(productId),
            Criteria.where(RatingConstants.RATING).gt(0)));
    GroupOperation groupOperation =
        Aggregation.group(RatingConstants.RATING).count().as(RatingConstants.RATING_COUNT);
    Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation);
    AggregationResults<RatingAnalyticsDAO> aggregationResults =
        mongoTemplate.aggregate(aggregation, ProductRating.class, RatingAnalyticsDAO.class);
    return aggregationResults.getMappedResults();
  }
}
