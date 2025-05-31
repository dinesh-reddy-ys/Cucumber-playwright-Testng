package utils;

import com.microsoft.playwright.Page;

/**
 * A utility class to manage Playwright Page instances using ThreadLocal.
 * This helps maintain thread safety and ensures each thread has its own Page instance
 * in a multi-threaded environment.
 */
public class TestContext {

    // ThreadLocal variable to hold a Playwright Page instance for each thread
    private static ThreadLocal<Page> page = new ThreadLocal<>();
    
    /**
     * Sets the current thread's Page instance.
     * 
     * @param pageInstance A Playwright Page instance to associate with the current thread
     */
    public static void setPage(Page pageInstance) {
        page.set(pageInstance);
    }
    
    /**
     * Retrieves the current thread's Page instance.
     * 
     * @return The Playwright Page instance associated with the current thread,
     *         or null if no instance has been set.
     */
    public static Page getPage() {
        return page.get();
    }
    
    /**
     * Clears the Page instance associated with the current thread.
     * This removes the thread's association with any Playwright Page instance.
     */
    public static void clearContext() {
        page.remove();
    }
}