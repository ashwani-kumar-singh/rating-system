package com.daytoday.ratingsystem.executor;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component("com.daytoday.ratingsystem.executor.ProductRatingCalculatorExecutor")
public class ProductRatingCalculatorExecutor {

  @Scheduled(cron = "${scheduling.job.db.calculate.product.rating.analytics}", zone = "UTC")
  @SchedulerLock(name = "calculateProductRating")
  public void calculateProductRatingAnalytics(){
    log.info("calculate");

  }
}
