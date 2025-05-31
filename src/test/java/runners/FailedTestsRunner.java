package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

/**
 * The FailedTestsRunner class is responsible for re-executing failed Cucumber scenarios. 
 * It uses the TestNG framework and Cucumber options to locate and execute 
 * the specific scenarios that failed in a previous test execution.
 *
 * This class:
 * 1. Specifies the location of the failed scenarios feature file.
 * 2. Configures step definitions, hooks, and report plugins.
 * 3. Utilizes a custom TestNG listener for additional control over test execution outcomes.
 */
@Listeners(config.TestNGListener.class) // Attaches a custom TestNG listener for tracking test execution.
@CucumberOptions(
        // Points to the feature file containing the failed scenarios for re-execution.
        features = "src/test/resources/features/failed_scenarios.feature",
        
        // Specifies the location of step definitions and hooks used to execute the scenarios.
        glue = {"stepdefs", "hooks"},
        
        // Configures the reporting and logging plugins for the execution.
        plugin = {
                "pretty", // Outputs execution results in a human-readable format.
                "html:target/failed_scenarios.txt" // Generates a simple report for failed scenarios.
        },
        
        // Ensures that the console output during execution is clean and readable.
        monochrome = true
)
public class FailedTestsRunner extends AbstractTestNGCucumberTests {
    // This class does not override or add any methods, as it inherits all the required 
    // functionality from AbstractTestNGCucumberTests. Customization can be added here if needed.
}