# Rating System for Product
## Assumptions
```
1. Rating will be Integer value.
2. Average Rating will be Integer or Decimal value upto 1 place of decimal.
```
## System Capabilities
```
1. create / update customer details.
2. create / update / delete / get product details.
3. create / update product rating.
4. get rating analytics for a product
```
## Future Enhancement / Improvement
```
1. Like / Dislike for product.
2. Support for Decimal rating.
3. Collect details on the basis of rating of product.
4. Percentage of customer rated particular rating for product. 
5. We can use of concept of curated field in product_rating field, so that it is reflected only after 
   review from BD/Support.
6. Messaging Queue System to calculate product rating analytics after the product is removed from curation 
   process.
 ```
 
## System Flows
   
![GitHub Logo](https://github.com/ashwani-kumar-singh/rating-system/blob/master/images/SaveRatingFlow.png)
![GitHub Logo](https://github.com/ashwani-kumar-singh/rating-system/blob/master/images/GetProductFlow.png)
![GitHub Logo](https://github.com/ashwani-kumar-singh/rating-system/blob/master/images/ProductRatingAnalyticsFlow.png)


## Database Changes

### Databse: MED_PRODUCT

<b>Collections</b>:

-: <b>product</b> :-  
```
{
    "_id" : ObjectId("5f34ef6a0b6e193437e6671e"),
    "_class" : "com.daytoday.ratingsystem.entity.Product",
    "name" : "X-ray",
    "description" : "X-ray",
    "base_price" : 5000.0,
    "created_on" : ISODate("2020-08-13T07:44:42.339Z"),
    "updated_on" : ISODate("2020-08-13T07:44:42.339Z")
}
```
-: <b>customer</b> :- 
```
{
    "_id" : ObjectId("5f34ec1f0b6e1932ec4d6bb3"),
    "_class" : "com.daytoday.ratingsystem.entity.Customer",
    "first_name" : "Raymond",
    "middle_name" : "T",
    "last_name" : "Hunt",
    "phone_number" : "985-247-6299",
    "country_code" : "+012",
    "email" : "raymonthunt@gmail.com",
    "address" : "4449  Swick Hill Street",
    "zip_code" : 70001,
    "created_on" : ISODate("2020-08-13T07:30:39.657Z"),
    "updated_on" : ISODate("2020-08-13T07:34:39.990Z")
}
```
-: <b>product_rating</b> :-
```
{
    "_id" : ObjectId("5f34f4390b6e193625e66e6a"),
    "_class" : "com.daytoday.ratingsystem.entity.ProductRating",
    "product_id" : "5f34ef6a0b6e193437e6671e",
    "customer_id" : "5f34ec1f0b6e1932ec4d6bb3",
    "rating" : 4,
    "review" : "Very helpful, saved me from hassle.",
    "created_on" : ISODate("2020-08-13T08:05:13.203Z"),
    "updated_on" : ISODate("2020-08-13T08:05:37.017Z")
}
```

********************************************** Curl Request *******************************************************

<b>Swagger Link</b> - http://localhost:9090/swagger-ui.html

-: <b>create customer details</b> :- 
```
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
  "first_name": "Bond",
  "middle_name": "M",
  "last_name": "Malvin",
  "phone_number": "985-347-6299",
  "country_code": "+012",
  "email": "malvink@outlook.com",
  "address": "4449 Stefen Street",
  "zip_code": 70002
}' 'http://localhost:9090/v1/customer/add-customer'
```
response -
```
{
  "status": "SUCCESS",
  "responseObject": {
    "_id": "5f3525370b6e1905941dbd94",
    "first_name": "Bond",
	  "middle_name": "M",
	  "last_name": "Malvin",
	  "phone_number": "985-347-6299",
	  "country_code": "+012",
	  "email": "malvink@outlook.com",
	  "address": "4449 Stefen Street",
	  "zip_code": 70002
  }
}
```

-: <b>update customer details</b> :-
```
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
  "_id": "5f3525370b6e1905941dbd94",
  "first_name": "Bond",
  "middle_name": "M",
  "last_name": "Malvin",
  "phone_number": "985-347-6299",
  "country_code": "+012",
  "email": "malvink@outlook.com",
  "address": "4449 Stefen Street",
  "zip_code": 70002
}' 'http://localhost:9090/v1/customer/add-customer'
```
response -
```
{
  "status": "SUCCESS",
  "responseObject": {
    "_id": "5f3525370b6e1905941dbd94",
    "first_name": "Bond",
	  "middle_name": "M",
	  "last_name": "Malvin",
	  "phone_number": "985-347-6299",
	  "country_code": "+012",
	  "email": "malvink@outlook.com",
	  "address": "4449 Stefen Street",
	  "zip_code": 70002
  }
}
```

-: <b>create product details</b> :-
```
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
  "base_price": 50000,
  "description": "X-ray",
  "name": "X-ray"
}' 'http://localhost:9090/v1/product/add-product'
```
response - 
```
{
  "status": "SUCCESS",
  "responseObject": {
    "_id": "5f3525d10b6e1905941dbd97",
    "name": "X-ray",
    "description": "X-ray",
    "base_price": 50000,
    "average_rating": null
  }
}
```

-: <b>update product details</b> :-
```
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
  "_id": "5f3525d10b6e1905941dbd97",
  "average_rating": 4,
  "base_price": 10000,
  "description": "X-ray",
  "name": "X-ray"
}' 'http://localhost:9090/v1/product/add-product'
```
response - 
```
{
  "status": "SUCCESS",
  "responseObject": {
    "_id": "5f3525d10b6e1905941dbd97",
    "name": "X-ray",
    "description": "X-ray",
    "base_price": 10000,
    "average_rating": 4
  }
}
```

-: <b>delete product</b> :-
```
curl -X DELETE 'http://localhost:9090/v1/product/5f35261d0b6e1905941dbd99'

response -
{
  "status": "SUCCESS",
  "responseObject": true
}
```
-: <b>get product</b> :- 
```
curl -X GET 'http://localhost:9090/v1/product/5f34efb30b6e193437e66720'
```
response -
```
{
  "status": "SUCCESS",
  "responseObject": {
    "_id": "5f3525d10b6e1905941dbd97",
    "name": "X-ray",
    "description": "X-ray",
    "base_price": 5000,
    "average_rating": 4
  }
}
```

-: <b>create rating</b> :- 
```
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
  "product_id": "5f3525d10b6e1905941dbd97",
  "customer_id": "5f355d9d0b6e191f9d17e716",
  "rating": 4,
  "review": "Very helpful, saved me from hassle."
}' 'http://localhost:9090/v1/product-rating/{productId}'
```
response -
```
{
  "status": "SUCCESS",
  "responseObject": true
}
```

-: <b>update rating</b> :- 
```
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
 "_id": "5f3527870b6e1905941dbd9e",
  "product_id": "5f3525d10b6e1905941dbd97",
  "customer_id": "5f355d9d0b6e191f9d17e716",
  "rating": 2,
  "review": "Good Plan"
}' 'http://localhost:9090/v1/product-rating/submit'
```
response - 
```
{
  "status": "SUCCESS",
  "responseObject": true
}
```

-: <b>get rating analytics for product</b> :-
```
curl -X GET --header 'Accept: application/json' 'http://localhost:9090/v1/product-rating/5f34ef6a0b6e193437e6671e'
```
response -
```
{
  "status": "SUCCESS",
  "responseObject": {
    "product_id": "5f3525d10b6e1905941dbd97",
    "average_rating": 4,
    "rating_statistics": {
      "2": 1,
      "4": 1,
      "5": 2
    }
  }
}
```
