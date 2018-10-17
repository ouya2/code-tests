Scenario

A TV broadcaster has decided to roll out 3D TV by geographic area. A software engineering
team, developing the online store, is working on the story below.
A “3D add­on” is an enhancement to a customers existing Entertainment Pack. E.g.
SPORTS_3D_ADD_ON enhances the SPORTS product.
The broadcaster has partnered with a third party that provides a service that checks the
3D TV availability for a given postcode.
The following files are included in the attached zip
* Basket.java
* ThreeDeeAddOnService.java
* AvailabilityChecker.java
* TechnicalFailureException.java

Instructions

You are required to provide an implementation of the ThreeDeeAddOnService interface,
a simple service containing a single method.
Basket and ThreeDeeAddOnService may be changed as you see fit.
The AvailabilityChecker and TechnicalFailureException cannot be modified.
Requirements

The product codes for the entertainment packs are:
* SPORTS
* KIDS
* VARIETY
* NEWS
* MOVIES_1
* MOVIES_2
The product codes for the add­on’s are:
* SPORTS_3D_ADD_ON
* NEWS_3D_ADD_ON
* MOVIES_3D_ADD_ON

The third party service returns the following values:
* SERVICE_AVAILABLE 3DTV service is available for the given post code
* SERVICE_UNAVAILABLE 3DTV service is unavailable for the given post code
* SERVICE_PLANNED 3DTV service is not available right now, but it should be available
within the next 3 months
* POSTCODE_INVALID The supplied postcode is invalid
If the service is unavailable then a TechnicalFailureException is thrown

Acceptance Criteria

| Postcode | Basket | Expected Output |
| :---: | :---: | :---: |
| Currently in a 3D area  | 3D compatible products in the basket | Return relevant 3D add­ons |
| N/A| No products with 3D addons | Return no 3D add­ons |
| Currently, not in a 3D area, or technical failure occurs | 3D compatible products in the basket | Return no 3D add­ons |
| Invalid Postcode | 3D compatible products in the products | Return no 3D add-ons and notify the client that postcode was invalid|


You may choose any means of accepting input and producing output, including the use of a test
harness.
Try and spend no more than two hours on this problem.