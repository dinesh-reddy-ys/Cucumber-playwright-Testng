package stepdefs;

import io.cucumber.java.en.Given;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Then;
import utils.TestContext;

public class LoginSteps {
    private final Page page;
    
    public LoginSteps() {
        this.page = TestContext.getPage();
    }
    
    @Given("I navigate to the login page")
    public void navigateToLoginPage() {
        page.navigate("https://example.com/login");
    }


}