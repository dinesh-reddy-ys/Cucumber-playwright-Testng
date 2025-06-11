package hooks;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.TestContext;
import java.nio.file.Paths;

public class Hooks {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    
    @Before
    public void setUp(Scenario scenario) {
        try {
            // Initialize Playwright
            playwright = Playwright.create();
            
            // Launch browser with additional options for stability
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setTimeout(30000) // 30 seconds timeout
                    .setSlowMo(100));  // Slow down Playwright operations by 100ms
            
            // Create a new context with viewport settings
            context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(1920, 1080));
            
            // Create a new page and set timeouts
            page = context.newPage();
            page.setDefaultNavigationTimeout(30000);  // 30 seconds for navigation
            page.setDefaultTimeout(10000);           // 10 seconds for other operations
            
            // Store the page object where it can be accessed by step definitions
            TestContext.setPage(page);
        } catch (Exception e) {
            tearDown(scenario);
            throw e;
        }
    }
    
    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed() && page != null) {
                byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                        .setFullPage(true)
                        .setPath(Paths.get("target/screenshots", 
                                scenario.getName().replaceAll("\\s+", "_") + ".png")));
                scenario.attach(screenshot, "image/png", "Screenshot");
            }
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        } finally {
            // Clean up Playwright resources in reverse order
            if (page != null) {
                try {
                    page.close();
                } catch (Exception e) {
                    System.err.println("Failed to close page: " + e.getMessage());
                }
            }
            if (context != null) {
                try {
                    context.close();
                } catch (Exception e) {
                    System.err.println("Failed to close context: " + e.getMessage());
                }
            }
            if (browser != null) {
                try {
                    browser.close();
                } catch (Exception e) {
                    System.err.println("Failed to close browser: " + e.getMessage());
                }
            }
            if (playwright != null) {
                try {
                    playwright.close();
                } catch (Exception e) {
                    System.err.println("Failed to close playwright: " + e.getMessage());
                }
            }
        }
    }
}