package hooks;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.TestContext;

public class Hooks {
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;
    
    @Before
    public void setUp(Scenario scenario) {
        // Initialize Playwright
        Playwright playwright = Playwright.create();
        
        // Launch browser (you can configure this based on your needs)
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
        
        // Create a new context for each scenario
        context = browser.newContext();
        
        // Create a new page
        page = context.newPage();
        
        // Store the page object where it can be accessed by step definitions
       TestContext.setPage(page);
    }
    
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot if a scenario fails
            byte[] screenshot = page.screenshot();
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
        
        // Clean up Playwright resources
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
    }
}