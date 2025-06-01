# Playwright-Cucumber-TestNG Framework

A robust test automation framework combining Playwright with Cucumber and TestNG for Java-based UI testing.

## Framework Structure
project-root/
├── pom.xml # Maven project configuration
├── README.md # Project documentation
├── src/
│ ├── main/
│ │ └── java/
│ │ ├── pages/ # Page Object Model classes
│ │ │ ├── BasePage.java
│ │ │ └── LoginPage.java
│ │ ├── utils/ # Utility/helper classes
│ │ │ └── TestContext.java
│ └── test/
│ ├── java/
│ │ ├── config/ # Test configuration and listeners
│ │ │ └── TestNGListener.java
│ │ ├── hooks/ # Cucumber hooks (Before/After)
│ │ │ └── Hooks.java
│ │ ├── stepdefs/ # Step Definitions
│ │ │ └── LoginSteps.java
│ │ └── runners/ # TestNG Cucumber Runner
│ │ └── TestRunner.java
│ └── resources/
│ └── features/ # Cucumber Feature Files
│ └── login.feature

## Key Components

### 1. TestContext (src/main/java/utils/TestContext.java)
Manages the Playwright Page instance across the framework using ThreadLocal for parallel execution support.

java package utils;
public class TestContext { private static final ThreadLocal PAGE_THREAD_LOCAL = new ThreadLocal<>();

public static void setPage(Page pageInstance) {
PAGE_THREAD_LOCAL.set(pageInstance);
}

public static Page getPage() {
return PAGE_THREAD_LOCAL.get();
}

public static void clearContext() {
PAGE_THREAD_LOCAL.remove();
}

### 2. Hooks (src/test/java/hooks/Hooks.java)
Handles Playwright setup and teardown for each scenario.

java package hooks;
public class Hooks { private static Browser browser; private static BrowserContext context; private static Page page;
@Before
public void setUp(Scenario scenario) {
Playwright playwright = Playwright.create();
browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
.setHeadless(false));
context = browser.newContext();
page = context.newPage();
TestContext.setPage(page);
}

@After
public void tearDown(Scenario scenario) {
if (scenario.isFailed()) {
byte[] screenshot = page.screenshot();
scenario.attach(screenshot, "image/png", "Screenshot");
}

    if (page != null) page.close();
    if (context != null) context.close();
    if (browser != null) browser.close();
}
}

### 3. TestNGListener (src/test/java/config/TestNGListener.java)
Manages test execution lifecycle and reporting.

java package config;
public class TestNGListener implements ITestListener { @Override public void onStart(ITestContext context) { System.out.println("Test Suite started!"); }
@Override
public void onFinish(ITestContext context) {
System.out.println("Test Suite finished!");
TestContext.clearContext();
}

// Additional listener methods...
}

### 4. TestRunner (src/test/java/runners/TestRunner.java)
Configures and executes Cucumber tests with TestNG.

ava package runners;
@Listeners(TestNGListener.class) @CucumberOptions( features =  public class TestRunner extends AbstractTestNGCucumberTests { @Override @DataProvider(parallel = true) public Object[][] scenarios() { return super.scenarios(); } }

## Setup Instructions

1. Add the following dependencies to your `pom.xml`:
   xml    com. microsoft. playwright   playwright   ${playwright. version}
<!-- Cucumber -->
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>${cucumber.version}</version>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-testng</artifactId>
    <version>${cucumber.version}</version>
</dependency>

<!-- TestNG -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>${testng.version}</version>
</dependency>

2. Install Playwright browsers:
   bash mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"

## Writing Tests

### 1. Create a Feature File
Create feature files in `src/test/resources/features/`:
gherkin Feature: Login Functionality
Scenario: Successful login with valid credentials Given I am on the login page When I enter valid credentials And I click the login button Then I should be logged in successfully

### 2. Implement Step Definitions
Create step definitions in `src/test/java/stepdefs/`:
java package stepdefs;
public class LoginSteps { private final Page page;
public LoginSteps() {
this.page = TestContext.getPage();
}

@Given("I am on the login page")
public void navigateToLoginPage() {
page.navigate("https://example.com/login");
}

// Additional step definitions...
}
``` 

### 3. Create Page Objects
Create page objects in `src/main/java/pages/`:
```
java package pages;
public class LoginPage extends BasePage { private final String USERNAME_FIELD = "#username"; private final String PASSWORD_FIELD = "#password"; private final String LOGIN_BUTTON = "#login-btn";
public LoginPage(Page page) {
super(page);
}

public void login(String username, String password) {
page.fill(USERNAME_FIELD, username);
page.fill(PASSWORD_FIELD, password);
page.click(LOGIN_BUTTON);
}
}
``` 

## Running Tests

### Running via Maven
```
bash mvn clean test
``` 

### Running Specific Tests
```
bash mvn test -Dcucumber.filter.tags="@smoke"
``` 

## Reporting

Test reports are generated in:
- HTML Report: `target/cucumber-reports/cucumber-pretty.html`
- JSON Report: `target/cucumber-reports/CucumberTestReport.json`

## Best Practices

1. **Page Object Pattern**: Keep all page-specific locators and methods in corresponding page objects.
2. **Thread Safety**: Use ThreadLocal for sharing objects between steps in parallel execution.
3. **Screenshots**: Automatically capture screenshots on test failures.
4. **Clean Code**: Follow proper package structure and naming conventions.
5. **Error Handling**: Implement proper exception handling and logging.

## Framework Features

- Parallel test execution support
- Automatic screenshot capture on failure
- Page Object Model implementation
- Thread-safe test context
- Detailed reporting
- Clean and maintainable code structure
- TestNG listener for advanced test lifecycle management

## Additional Notes

- Make sure to update the Playwright, Cucumber, and TestNG versions in `pom.xml` to their latest stable versions.
- Configure browser options in Hooks class based on your requirements.
- Add additional configuration for different environments if needed.
```




