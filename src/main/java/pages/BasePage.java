package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import java.time.Duration;

import static com.microsoft.playwright.Locator.*;

public class BasePage {
    protected final Page page;
    protected final int DEFAULT_TIMEOUT = 30000; // 30 seconds

    public BasePage(Page page) {
        this.page = page;
    }

    /**
     * Wait for element to be visible
     */
    protected void waitForElement(String selector) {
        page.locator(selector).waitFor(new WaitForOptions()
                .setTimeout(DEFAULT_TIMEOUT));
    }

    /**
     * Wait and click element
     */
    protected void clickElement(String selector) {
        waitForElement(selector);
        page.locator(selector).click();
    }

    /**
     * Wait and type text
     */
    protected void typeText(String selector, String text) {
        waitForElement(selector);
        page.locator(selector).fill(text);
    }

    /**
     * Get text from element
     */
    protected String getText(String selector) {
        waitForElement(selector);
        return page.locator(selector).textContent();
    }

    /**
     * Check if element is visible
     */
    protected boolean isElementVisible(String selector) {
        try {
            return page.locator(selector).isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for element with custom timeout
     */
    protected void waitForElement(String selector, int timeout) {
        page.locator(selector).waitFor(new WaitForOptions()
                .setTimeout(timeout));
    }

    /**
     * Wait for element to be clickable and click
     */
//    protected void safeClick(String selector) {
//        waitForElement(selector);
//        Locator element = page.locator(selector);
//
//        try {
//            element.waitFor(new WaitForOptions()
//                    .setState());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        element.click();
//    }

    /**
     * Scroll element into view
     */
    protected void scrollIntoView(String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    /**
     * Check if element exists
     */
    protected boolean elementExists(String selector) {
        return page.locator(selector).count() > 0;
    }

    /**
     * Hover over element
     */
    protected void hoverElement(String selector) {
        page.locator(selector).hover();
    }

    /**
     * Get attribute value
     */
    protected String getAttribute(String selector, String attributeName) {
        return page.locator(selector).getAttribute(attributeName);
    }

    /**
     * Wait for page load state
     */
    protected void waitForPageLoad() {
        page.waitForLoadState();
    }

    /**
     * Clear and type text
     */
    protected void clearAndType(String selector, String text) {
        Locator element = page.locator(selector);
        element.clear();
        element.fill(text);
    }

    /**
     * Wait for element to disappear
     */
//    protected void waitForElementToDisappear(String selector) {
//        page.locator(selector).waitFor(new WaitForOptions()
//                .setState(Locator.WaitForSelectorState.HIDDEN));
//    }

    /**
     * Take screenshot of specific element
     */
    protected byte[] takeElementScreenshot(String selector) {
        return page.locator(selector).screenshot();
    }

    /**
     * Take full page screenshot
     */
    protected byte[] takeFullPageScreenshot() {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }

    /**
     * Execute JavaScript
     */
    protected Object executeJavaScript(String script) {
        return page.evaluate(script);
    }

    /**
     * Switch to frame
     */
//    protected void switchToFrame(String frameSelector) {
//        page.frameLocator(frameSelector).waitFor();
//    }

    /**
     * Handle alert
     */
    protected void acceptAlert() {
        page.onDialog(dialog -> dialog.accept());
    }

    /**
     * Dismiss alert
     */
    protected void dismissAlert() {
        page.onDialog(dialog -> dialog.dismiss());
    }

    /**
     * Wait for network idle
     */
//    protected void waitForNetworkIdle() {
//        page.waitForLoadState(Page.LoadState.NETWORKIDLE);
//    }

    /**
     * Get count of elements
     */
    protected int getElementCount(String selector) {
        return page.locator(selector).count();
    }
}