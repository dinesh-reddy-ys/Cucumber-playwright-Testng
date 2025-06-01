package config;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * A custom TestNG listener implementation that listens for and responds to various 
 * test execution events. This class allows users to perform custom actions 
 * during the lifecycle of test execution, including setup, test failures, and cleanup.
 * 
 * Implementing the {@link ITestListener} interface provides hooks to override specific 
 * methods for executing custom logic during the start, end, and execution of test cases.
 */
public class TestNGListener implements ITestListener {

    /**
     * This method is invoked before any test set or suite begins execution 
     * and is commonly used for test suite-level setup tasks.
     *
     * @param context The {@link ITestContext} object provides details about
     *                the current test execution context, such as suite and test names.
     */
    @Override
    public void onStart(ITestContext context) {
        // Test suite level setup (e.g., initializing resources, logging information, etc.)
        System.out.println("Test Suite Started: " + context.getSuite().getName());
    }

    /**
     * This method is invoked after all tests in the suite have completed execution.
     * It is commonly used for cleanup tasks such as closing resources.
     *
     * @param context The {@link ITestContext} object provides details about 
     *                the completed test suite context.
     */
    @Override
    public void onFinish(ITestContext context) {
        // Test suite level cleanup (e.g., releasing resources, generating reports, etc.)
        System.out.println("Test Suite Finished: " + context.getSuite().getName());
    }

    /**
     * This method is triggered when a test method fails.
     * It can be used for handling failure scenarios, such as taking screenshots, 
     * saving logs, or marking failures for further investigation.
     *
     * @param result The {@link ITestResult} object provides information about the failed test,
     *               including the method name, exception thrown, and more.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        // Handle test failures (e.g., logging, capturing diagnostics, notifying teams, etc.)
        System.out.println("Test Failed: " + result.getName());
    }
    
    // Implement other methods as needed for additional event handling.
    // Examples include:
    // - onTestStart: Triggered before each test method begins execution.
    // - onTestSuccess: Triggered when a test method passes.
    // - onTestSkipped: Triggered when a test method is skipped.
}