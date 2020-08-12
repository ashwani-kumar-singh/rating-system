package com.daytoday.ratingsystem.controller;

/**
 * Created By Ashwani Singh
 * Dated: 12th Aug 2020
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@Slf4j
@EnableSwagger2
@RequestMapping(value = "/v1/product-rating/")
public class ProductRatingController {

}
