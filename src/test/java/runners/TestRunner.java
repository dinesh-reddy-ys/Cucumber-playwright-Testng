package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

/**
 * The TestRunner class serves as the entry point for executing Cucumber feature files 
 * using the TestNG framework. It combines the capabilities of Cucumber and TestNG 
 * to run tests in an efficient and organized manner.
 *
 * This class:
 * 1. Configures Cucumber-specific options such as feature file location, glue code, and plugins.
 * 2. Defines a mechanism to execute Cucumber scenarios in parallel if required.
 * 3. Utilizes a custom TestNG listener for additional test monitoring and reporting.
 */
@Listeners(config.TestNGListener.class) // Specifies the custom TestNG listener to monitor events.
@CucumberOptions(
    // Specifies the path to the Cucumber feature files for test execution.
    features = "src/test/resources/features",
    
    // Defines the package locations for step definitions and hooks used in the tests.
    glue = {"stepdefs", "hooks"},
    
    // Configures the plugins used for generating reports and logging Cucumber test outcomes.
    plugin = {
        "pretty", // Prints test execution logs in a readable format.
        "html:target/cucumber-reports/cucumber-pretty.html", // Generates an HTML report.
        "json:target/cucumber-reports/CucumberTestReport.json", // Exports results in JSON format.
        "rerun:target/failed_scenarios.txt" // Outputs a file with failed scenarios for re-execution.
    }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Overrides the scenarios() method from the AbstractTestNGCucumberTests class 
     * to enable parallel execution of Cucumber scenarios.
     *
     * TestNG DataProviders are used for parameterized test execution, 
     * and setting the parallel attribute to "true" ensures that scenarios 
     * are run in multiple threads.
     *
     * @return A 2D object array containing the scenarios to execute.
     */
    @Override
    @DataProvider(parallel = true) // Enables parallel execution of test scenarios.
    public Object[][] scenarios() {
        return super.scenarios(); // Calls the parent class implementation to retrieve scenarios.
    }
}