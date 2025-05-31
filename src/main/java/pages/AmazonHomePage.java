package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class AmazonHomePage extends BasePage {
    // Locators
    private final String SEARCH_BOX = "//*[@id=\"twotabsearchtextbox\"]";
    private final String SEARCH_BUTTON = "//*[@id=\"nav-search-submit-button\"]";
    //private final String LOGIN_POPUP_CLOSE = "button._2KpZ6l._2doB4z";
    private final String SEARCH_RESULTS = "//*[text() = \"Results\"]";
    private final String PRICE_FILTER = "//*[@id=\"p_36/range-slider_slider-item_upper-bound-slider\"]";
    private final String RATING_FILTER = "//div[text()='Customer Ratings']//following::div[contains(text(),'%s')]";
    private final String SORT_DROPDOWN = "div._10UF8M";
    private final String PRODUCT_CARDS = "div._1AtVbE";
    private final String PRODUCT_SPECS = "div._1UhVsV";

    public AmazonHomePage(Page page) {
        super(page);
    }

    // Actions
    public void launch_amazon(){
        page.navigate("https://www.amazon.in");
    }

    public void searchFor(String searchTerm){
        page.fill(SEARCH_BOX, searchTerm);
        page.click(SEARCH_BUTTON);
    }
    public void validateSearchResult(String searchTerm){
        waitForElement(SEARCH_RESULTS);
        String searchResultText = getText(SEARCH_RESULTS);
        assert searchResultText.contains(searchTerm);
    }


    public void enterMinPrice(String minPrice) {
        page.fill(PRICE_FILTER, minPrice);
    }

    public void enterMaxPrice(String maxPrice) {
        page.fill(PRICE_FILTER, maxPrice);
    }

    public void applyPriceFilter() {
        page.click(SEARCH_BUTTON);
    }

    public void setPriceRange( ) {

        // Update sliders if needed (mostly visual)
        Locator minSlider = page.locator("#p_36\\/range-slider_slider-item_lower-bound-slider");
       Locator maxSlider = page.locator("#p_36\\/range-slider_slider-item_upper-bound-slider");

        // Interact with slider by setting value attributes
        minSlider.evaluate("el => el.value = 11"); // corresponding to ₹590
       maxSlider.evaluate("el => el.value = 78"); // corresponding to ₹59300

        // Submit the form
        page.locator("input[aria-label='Go - Submit price range']").click();
    }
}
