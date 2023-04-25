package testsuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class LoginTest extends Utility {

    String baseUrl = "https://www.saucedemo.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {

        //Enter “standard_user” username
        sendTextToElement(By.xpath("//input[@id='user-name']"), "standard_user");
        //Enter “secret_sauce” password
        sendTextToElement(By.xpath("//input[@id='password']"), "secret_sauce");
        //Click on ‘LOGIN’ button
        clickOnElement(By.xpath("//input[@id='login-button']"));

        /**
         * Verify the text “PRODUCTS”
         */
        String expectedHomeMsg = "Products";
        String actualHomeMsg = getTextFromElement(By.xpath("//span[@class='title']"));
        verifyText("Expected Homepage message is not displayed", expectedHomeMsg, actualHomeMsg);

    }

    @Test
    public void verifyThatSixProductsAreDisplayedOnPage() {

        //Enter “standard_user” username
        sendTextToElement(By.xpath("//input[@id='user-name']"), "standard_user");
        //Enter “secret_sauce” password
        sendTextToElement(By.xpath("//input[@id='password']"), "secret_sauce");
        //Click on ‘LOGIN’ button
        clickOnElement(By.xpath("//input[@id='login-button']"));

        /**
         * Verify that six products are displayed
         * on page
         */
        List<WebElement> expectedProductsDisplayed = driver.findElements(By.xpath("//div[@class=" +
                "'inventory_list']"));
        int expectedNumberOfProducts = expectedProductsDisplayed.size();

        List<WebElement> actualProductsDisplayed = driver.findElements(By.xpath("//div[@class=" +
                "'inventory_list']"));
        int actualNumberOfProducts = actualProductsDisplayed.size();

        verifyText("Expected number of products are not displayed", String.valueOf(expectedNumberOfProducts),
                String.valueOf(actualNumberOfProducts));


    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
