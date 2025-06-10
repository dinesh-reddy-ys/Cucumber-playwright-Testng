package stepdefs;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import io.cucumber.java.en.*;
import pages.AmazonHomePage;
import utils.DBUtils;
import utils.TestContext;

import java.util.List;


public class AmazonSearchSteps {
    private final Page page;
    private final AmazonHomePage amazonHomePage;

    public AmazonSearchSteps() {
        this.page = TestContext.getPage();
        this.amazonHomePage = new AmazonHomePage(page);
    }

    @Given("I am on the Amazon homepage")
    public void navigateToAmazon() {
        amazonHomePage.launch_amazon();
    }

    @When("I search for {string}")
    public void searchFor(String searchTerm) {
        amazonHomePage.searchFor(searchTerm);
    }

    @And("I should see {string} in the search results")
    public void searchResult(String searchTerm) {
        amazonHomePage.validateSearchResult(searchTerm);
    }

    @When("I filter results")
    public void filterResultsBy() {
       amazonHomePage.enterMinPrice();
       amazonHomePage.enterMaxPrice();
       amazonHomePage.applyPriceFilter();
    }
    @And("I filter only Samsung brand")
    public void filterByBrand() {
        amazonHomePage.applySamsungBrandFilter();
    }
    @Then("I Get the details of the devices and add to data base")
        public void getDetailsAndAddToDB() {
        List<ElementHandle> products = page.querySelectorAll("//h2[@class=\"a-size-medium a-spacing-none a-color-base a-text-normal\"]");
        System.out.println("Total products found: " + products.size());

        // Wait for all products to load
        page.waitForLoadState();
            try {
                double price = Double.parseDouble("1000");
                DBUtils.insertMobileDetails("samsung s24", "RAM 128", "Androdi 20", price);
                // skip if price parsing fails
                System.out.println("Added " + "samsung " + " to DB");
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }







}