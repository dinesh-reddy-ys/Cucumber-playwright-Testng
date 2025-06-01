package stepdefs;

import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pages.AmazonHomePage;
import utils.TestContext;

import java.util.Map;

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
}