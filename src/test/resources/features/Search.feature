Feature: Amazon Product Search and Selection

  Scenario: Search and select a product based on specific criteria
    Given I am on the Amazon homepage
    When I search for "samsung mobile"
   And I should see "Results" in the search results
   When I filter results

#    And I sort results by "Price -- Low to High"
#    And I select the first product matching criteria:
#      | RAM     | 6 GB   |
#      | Storage | 128 GB |
#    Then I should be redirected to the product details page
#    And product specifications should match selected criteria