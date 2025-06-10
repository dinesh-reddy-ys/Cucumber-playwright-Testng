Feature: Amazon Product Search and Selection

  Scenario: Search and select a product based on specific criteria
    Given I am on the Amazon homepage
    When I search for "samsung mobile"
   And I should see "Results" in the search results
   When I filter results

  Scenario Outline: Search for mobiles
  Given I am on the Amazon homepage
  When I search for "mobiles"
  And I should see "results" in the search results
  And I filter only Samsung brand
  Then I Get the details of the devices and add to data base


#    And I teria:
#      | RAM   sort results by "Price -- Low to High"
##    And I select the first product matching cri  | 6 GB