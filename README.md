# Checkout Kata Application

## Checkout Kata Application to calculate the total amount for the shopping items

### Design:

### Model Classes:-
1) ShoppingItem 	- Holds the shopping item
2) ItemPrice 		- Holds the normal price of item
3) ItemOfferPrice 	- Holds the offer price of item

ItemPrice composed of ItemOfferPrice. There are two constructors defined in ItemPrice.

1) To hold the item with offer price 
2) To hold the item without offer price 

Offer price is optional attribute as some item may not have offers. However, all items should have the price and quantity.

### Service Classes:-

#### CheckoutService Class:-

##### Validations:-
The following validations are done on the input parameters.

1) ShoppingItems cannot be empty
2) Item price list cannot be empty
3) Shopping item must be present in the price list (i.e. assuming nothing is free)

##### Business Logic:-

The main responsibility of this class is to calculate the total amount for the shopping items. It calls the "CalculateItemPrice.getItemPrice()" method to calculate the price for each shopping items.

##### Arithmetic Overflow:-
The arithmetic overflow scenario has been handled though it is very unlikely to happen.


### CalculateItemPrice Class:-

#### Business Logic:-
The main responsibility of this class is to calculate the price for each item. If the item has special offer, apply the offer price for the corresponding quantities accordingly

If the item is returned (customer returning the item) due to some reason, the total amount will be calculated subtracting the amount for the returned item

If the shopping item is not present in item price list, it will throw an exception CheckoutKataApplicationException

**CheckoutKataApplicationException class:-**
Custom runtime exception class

Constants class - Holds the error message text

### Technology Stack :-

Java 8, Spring core, JUnit 4 and Maven

### To execute the unit tests:-

    mvn clean test
	
![Build Result](https://github.com/notionquest/checkoutkata_coverage/blob/master/checkout_kata_build_image.JPG)
	
### Code zip html report is present as zip file:-	

Please open the index.html to check the code coverage report.

![Build Coverage](https://github.com/notionquest/checkoutkata_coverage/blob/master/checkout_kata_coverage_image.JPG)

### Improvements:-

1) The code is not fully thread safe