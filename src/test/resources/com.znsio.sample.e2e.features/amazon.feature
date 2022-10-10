Feature: Amazon site scenario

#  - Launch amazon.in
#  - search for 'iphone 13'
#  - validate the results
#  - click on 1st product
#  - validate the product details page and the product title
#  - validate add to cart
#  - click on add to cart
#  - go to cart
#  - validate the same product is in the cart

#  CONFIG=./configs/theapp_local_config.properties PLATFORM=web TAG=theapp ./gradlew run
#  CONFIG=./configs/theapp_local_config.properties PLATFORM=android TAG=theapp ./gradlew run

  @web
  Scenario: Verify product add to cart scenario
    Given I search for "iPhone 13" on Amazon
    When I add the first product to cart
    Then the same product should be available in the cart
