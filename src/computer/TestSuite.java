package computer;

import browsertesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.Select;

public class TestSuite extends BaseTest {


    @Before
    public void verifyProductArrangeInAlphaBeticalOrder() {
        String baseUrl = "https://demo.nopcommerce.com/";
        openBrowser(baseUrl);
        Actions actions = new Actions(driver);
        WebElement computer = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        actions.moveToElement(computer).build().perform();
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        clickOnElement(By.xpath("//select[@id='products-orderby']"));
        WebElement dropDown = driver.findElement(By.name("products-orderby"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("Name: Z to A");
        String expectedOrder = "Name: Z to A";
        WebElement actualOrder = driver.findElement(By.xpath("//option[contains(text(),'Name: Z to A')]"));
        String realOrder = actualOrder.getText();
        Assert.assertEquals("The List Is not In Descending Order", expectedOrder, realOrder);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        verifyProductArrangeInAlphaBeticalOrder();
        WebElement sortBy = driver.findElement(By.id("products-orderby"));
        Select select = new Select(sortBy);
        select.selectByValue("5");
        Thread.sleep(3000);
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        String expectedDisplayMessage = "Build your own computer";
        WebElement actualMessage = driver.findElement(By.xpath("//h1[text() = 'Build your own computer']"));
        String realDisplayMessage = actualMessage.getText();
        Assert.assertEquals("Customer is not on build Computer page", expectedDisplayMessage, realDisplayMessage);
        Thread.sleep(3000);
        clickOnElement(By.xpath("//dd[@id='product_attribute_input_1']"));
        driver.findElement(By.xpath("//option[contains(text(),'2.2 GHz Intel Pentium Dual-Core E2200')]")).click();
        WebElement dropDown = driver.findElement(By.cssSelector("#product_attribute_2"));
        Select selected = new Select(dropDown);
        selected.selectByValue("5");
        clickOnElement(By.cssSelector("#product_attribute_3_7"));
        clickOnElement(By.cssSelector("div.master-wrapper-page:nth-child(6) div.master-wrapper-content div.master-column-wrapper" +
                " div.center-1 div.page.product-details-page div.page-body div.product-essential div.overview div.attributes " +
                "dl:nth-child(1) dd:nth-child(8) ul.option-list li:nth-child(2) > label:nth-child(2)"));
        //  clickOnElement(By.cssSelector("#product_attribute_5_10"));
        clickOnElement(By.cssSelector("#product_attribute_5_12"));//tick button
        String expectedTotalprice = "$1,475.00";
        WebElement actualTotalPrice = driver.findElement(By.xpath("//span[text()='$1,475.00']"));
        String actualPrice = actualTotalPrice.getText();
        Assert.assertEquals("The Total Price Is Not Correct", expectedTotalprice, actualPrice);
        Thread.sleep(3000);
        clickOnElement(By.cssSelector("#add-to-cart-button-1"));//add to cart
        String expectedMessage = "The product has been added to your shopping cart";
        WebElement actualoriginalMessage = driver.findElement(By.xpath("//div[@class = 'bar-notification success']"));
        String actualMessagedisplay = actualoriginalMessage.getText();
        Assert.assertEquals("The product has been added to your shopping cart", expectedMessage, actualMessagedisplay);
        Thread.sleep(3000);
        clickOnElement(By.xpath("//span[@class = 'close']"));//click on cross
        Actions actions = new Actions(driver);
        WebElement shoppingCart = driver.findElement(By.xpath("//span[@class = 'cart-label']"));
        actions.moveToElement(shoppingCart).build().perform();
        clickOnElement(By.xpath("//button[@class = 'button-1 cart-button']"));
        String expectedCartPageMessage = "Shopping cart";
        WebElement actualPageMsg = driver.findElement(By.xpath("//h1[text()='Shopping cart']"));
        String actualCartPageMessage = actualPageMsg.getText();
        Assert.assertEquals("Customer Is Not On Shopping Cart Page", expectedCartPageMessage, actualCartPageMessage);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@value = '1']")).clear();
        driver.findElement(By.xpath("//input[@value = '1']")).sendKeys("2");
        clickOnElement(By.xpath("//button[contains(text(),'Update shopping cart')]"));
        String expectedTotal = "$2,950.00";
        WebElement actualTotal = driver.findElement(By.xpath("//span[@class='product-subtotal']"));
        String actualTotalValue = actualTotal.getText();
        Assert.assertEquals("Total Is Wrong", expectedTotal, actualTotalValue);
        Thread.sleep(3000);
        clickOnElement(By.cssSelector("#termsofservice"));
        clickOnElement(By.cssSelector("#checkout"));
        String expectedSignInMessage = "Welcome, Please Sign In!";
        WebElement actualSignInMessage = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        String actualWelcomeSignInMessage = actualSignInMessage.getText();
        Assert.assertEquals("User Is Not On Sign In Page", expectedSignInMessage, actualWelcomeSignInMessage);
        Thread.sleep(3000);

        clickOnElement(By.xpath("//button[@class = 'button-1 checkout-as-guest-button']"));
        driver.findElement(By.cssSelector("#BillingNewAddress_FirstName")).sendKeys("Prime");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_LastName']")).sendKeys("Testing");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_Email']")).sendKeys("test@gmail.com");
        WebElement country = driver.findElement(By.id("BillingNewAddress_CountryId")) ;    //dropdown menu
        select = new Select(country);
        select.selectByValue("233");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_City']")).sendKeys("Watford");
        driver.findElement(By.cssSelector("#BillingNewAddress_Address1")).sendKeys("123 Prime Street");
        driver.findElement(By.xpath("//input[@id = 'BillingNewAddress_ZipPostalCode']")).sendKeys("WD25 4HL");
        driver.findElement(By.xpath("//input[@id = 'BillingNewAddress_PhoneNumber']")).sendKeys("07988899900");
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));
        clickOnElement(By.xpath("//input[@id ='shippingoption_1']"));
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        clickOnElement(By.xpath("//label[contains(text(),'Credit Card')]"));
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));
        Thread.sleep(3000);


        WebElement Mastercard = driver.findElement(By.xpath("//select[@id='CreditCardType']"));
        select = new Select(Mastercard);
        select.selectByValue("MasterCard");
        driver.findElement(By.xpath("//input[@id='CardholderName']")).sendKeys("Mrunal Patel");
        driver.findElement(By.xpath("//input[@id='CardNumber']")).sendKeys("5367852429396419");
        WebElement expmon = driver.findElement(By.xpath("//select[@id='ExpireMonth']"));
        select = new Select(expmon);
        select.selectByValue("5");
        Thread.sleep(3000);
        WebElement expyr = driver.findElement(By.xpath("//select[@id='ExpireYear']"));
        select = new Select(expyr);
        select.selectByValue("2022");
        driver.findElement(By.xpath("//input[@id='CardCode']")).sendKeys("163");
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]"));
        //Assert for Mastercard
        String expectedPaymentMessage = "Payment Method: Credit Card";
        WebElement errorMessage = driver.findElement(By.xpath("//li[@class='payment-method']"));
        String actualPaymentMessage = errorMessage.getText();
        Assert.assertEquals("Error message not display", expectedPaymentMessage, actualPaymentMessage);
        Thread.sleep(3000);

        //Assert for Shipping
        String expectedShippingMessage = "Shipping Method: Next Day Air";
        WebElement errorShippingMessage = driver.findElement(By.xpath("//li[@class='shipping-method']"));
        String actualShippingMessage = errorShippingMessage.getText();
        Assert.assertEquals("Error message not display", expectedShippingMessage, actualShippingMessage);
        Thread.sleep(3000);

        //Assert for Shipping next day air
        String expectedDeliveryMessage = "Total: $2,950.00";
        WebElement errorDeliveryMessage = driver.findElement(By.xpath("//tr[@class='order-total']"));
        String actualDeliveryMessage = errorDeliveryMessage.getText();
        Assert.assertEquals("Error message not display", expectedDeliveryMessage, actualDeliveryMessage);
        Thread.sleep(3000);

        clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));

        String expectedThanksMessage = "Thank you";
        WebElement actualThanksMessage = driver.findElement(By.xpath("//h1[text()='Thank you']"));
        String actualThankYouMessage = actualThanksMessage.getText();
        Assert.assertEquals("Wrong ThankYou Message Appearing",expectedThanksMessage,actualThankYouMessage);
        String expectedConfirmationMessage = "Your order has been successfully processed!";
        WebElement actualMessageIs = driver.findElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        String actualConfirmationMessage = actualMessageIs.getText();
        Assert.assertEquals("Wrong Confirmation Message",expectedConfirmationMessage,actualConfirmationMessage);
        clickOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));
        Thread.sleep(3000);
        String expectedLastMessage= "Welcome to our store";
        WebElement actualLastMessage=driver.findElement(By.xpath("//h2[text()='Welcome to our store']"));
        String realLastMessage= actualLastMessage.getText();
        Assert.assertEquals("Welcome message wrong",expectedLastMessage,realLastMessage);



    }
    @After
    public void tearDown(){
        closeBrowser();
    }
}
