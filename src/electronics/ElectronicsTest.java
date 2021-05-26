package electronics;

import browsertesting.BaseTest;
import homepage.TopMenuTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.By.cssSelector;


public class ElectronicsTest extends BaseTest {


    @Before
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {
        String baseUrl = "https://demo.nopcommerce.com/";
        openBrowser(baseUrl);
        Actions actions = new Actions(driver);
        WebElement electronics = driver.findElement(By.xpath("//body[1]/div[6]/div[2]/ul[1]/li[2]/a[1]"));
        WebElement cellphone = driver.findElement(By.xpath("//body[1]/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));
        actions.moveToElement(electronics).moveToElement(cellphone).click().build().perform();
        String expectedMessage = "Cell phones";
        WebElement celldriv = driver.findElement(By.xpath("//h1[text()='Cell phones']"));
        String actualMessage = celldriv.getText();
        Assert.assertEquals("You are not on Cell page",expectedMessage,actualMessage);
        Thread.sleep(3000);

    }
    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        verifyUserShouldNavigateToCellPhonesPageSuccessfully();

        clickOnElement(By.xpath("//a[@class ='viewmode-icon list']"));//2.4
        Thread.sleep(3000);

        clickOnElement(By.xpath("//a[text()='Nokia Lumia 1020']"));//2.5
        String expectedPhone = "Nokia Lumia 1020";//2.6
        String actualPhone = getTextFromElement(By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"));
        Assert.assertEquals("Nokia Lumia 1020",expectedPhone,actualPhone);
        Thread.sleep(3000);
        String expectedPrice = "$349.00";
        String actualPrice = getTextFromElement(cssSelector("#price-value-20"));
        Assert.assertEquals("Price for Nokia Lumia 1020 is wrong",expectedPrice,actualPrice);
       Thread.sleep(3000);

       driver.findElement(cssSelector("#product_enteredQuantity_20")).clear();//2.8
       sendTextToElement(By.cssSelector("#product_enteredQuantity_20"),"2");//2.9
       clickOnElement(By.xpath("//button[@onclick = 'return AjaxCart.addproducttocart_details(\"/addproducttocart/details/20/1\"," +
               "\"#product-details-form\"),!1']"));//2.10
        String expectedcartMessage = "The product has been added to your shopping cart";
        String actualMessagedisplay = getTextFromElement(By.xpath("//div[@class = 'bar-notification success']"));
        Assert.assertEquals("The product has been added to your shopping cart", expectedcartMessage, actualMessagedisplay);
        Thread.sleep(3000);
        clickOnElement(By.xpath("//span[@class = 'close']"));//click on cross
        hoverToElement(By.xpath("//span[@class = 'cart-label']"));
        clickOnElement(By.xpath("//button[@class = 'button-1 cart-button']"));
        String expectedCartPageMessage = "Shopping cart";
        String actualCartPageMessage = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        Assert.assertEquals("Customer Is Not On Shopping Cart Page", expectedCartPageMessage, actualCartPageMessage);//2.12
        Thread.sleep(3000);
        String expectedCartAmount = "$698.00";
        String actualCartAmount = getTextFromElement(By.xpath("//span[@class = 'product-subtotal']"));
        Assert.assertEquals("Amount of 2 Nokia phones incorrect",expectedCartAmount,actualCartAmount);
        Thread.sleep(3000);
        clickOnElement(cssSelector("#termsofservice"));
        clickOnElement(cssSelector("#checkout"));
        String expectedSignInMessage = "Welcome, Please Sign In!";
        String actualWelcomeSignInMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        Assert.assertEquals("User Is Not On Sign In Page", expectedSignInMessage, actualWelcomeSignInMessage);
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[@onclick = 'location.href=\"https://demo.nopcommerce.com/register?returnUrl=%2Fcart\"']"));
        String expectedRegisterMessage = "Register";
        String actualRegisterMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Register')]"));
        Assert.assertEquals("Please Register on this Page",expectedRegisterMessage,actualRegisterMessage);
        Thread.sleep(3000);
        clickOnElement(By.xpath("//input[@id='gender-male']"));
        sendTextToElement(By.xpath("//input[@id='FirstName']"),"Prime");
        sendTextToElement(By.xpath("//input[@id='LastName']"),"Testing");
        sendTextToElement(By.xpath("//input[@id='Email']"),"test20@gmail.com");//change every time
        sendTextToElement(By.xpath("//input[@id='Password']"),"test123");
        sendTextToElement(By.xpath("//input[@id='ConfirmPassword']"),"test123");
        clickOnElement(By.xpath("//button[@id='register-button']"));
        Thread.sleep(3000);
        String expectedRegistrationMessage = "Your registration completed";
        String actualRegMessage = getTextFromElement(By.xpath("//div[text() = 'Your registration completed']"));
        Assert.assertEquals("Your Registration is not completed",expectedRegistrationMessage,actualRegMessage);
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));//2.23
        String expectedShoppingCart = "Shopping cart";
        String actualShopCart = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals("Your shopping cart",expectedShoppingCart,actualShopCart);//2.24
        Thread.sleep(3000);
        clickOnElement(By.xpath("//input[@id = 'termsofservice']"));
        clickOnElement(By.id("checkout"));
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"),"233");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"),"Watford");
        sendTextToElement(cssSelector("#BillingNewAddress_Address1"),"123 Prime Street");
        sendTextToElement(By.xpath("//input[@id = 'BillingNewAddress_ZipPostalCode']"),"WD25 4HL");
        sendTextToElement(By.xpath("//input[@id = 'BillingNewAddress_PhoneNumber']"),"07988899900");
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        clickOnElement(By.id("shippingoption_2"));
       // clickOnElement(By.xpath("//input[@id ='shippingoption_1']"));
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        clickOnElement(By.xpath("//label[contains(text(),'Credit Card')]"));
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));
        Thread.sleep(3000);
        sendTextToElement(By.xpath("//input[@id='CardholderName']"),"Mrunal Patel");
        sendTextToElement(By.xpath("//input[@id='CardNumber']")," 4539039257690610");
        selectByValueFromDropDown(By.xpath("//select[@id='ExpireMonth']"),"11");
        Thread.sleep(3000);
        selectByValueFromDropDown(By.xpath("//select[@id='ExpireYear']"),"2025");
        sendTextToElement(By.xpath("//input[@id='CardCode']"),"978");
       // clickOnElement(By.xpath("//button[@onclick ='ConfirmOrder.save()']"));
        clickOnElement(By.xpath("//button[@onclick='PaymentInfo.save()']"));
        String paymentCard = "Payment Method: Credit Card";
        String actualPaymentCard = getTextFromElement(By.xpath("//li[@class='payment-method']"));
        Assert.assertEquals("Payment method Is Wrong",paymentCard,actualPaymentCard);
        Thread.sleep(3000);
        String shipingMethod = "Shipping Method: 2nd Day Air";
        String actualShippingMethod  = getTextFromElement(By.xpath("//li[@class='shipping-method']"));
        Assert.assertEquals("Shipping method is Incorrect",shipingMethod,actualShippingMethod);
        Thread.sleep(3000);
        String totalOrder = "Total: $698.00";
        String actualTotalOrder = getTextFromElement(By.xpath("//tr[@class='order-total']"));
        Assert.assertEquals("Total Order Is Wrong",totalOrder,actualTotalOrder);
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));
        String thanks = "Thank you";
        String actualThanksPage = getTextFromElement(By.xpath("//h1[text()='Thank you']"));
        Assert.assertEquals("Thank you Page Not Displayed",thanks,actualThanksPage);
        String orderConfirmation = "Your order has been successfully processed!";
        String realorderConfirmation = getTextFromElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        Assert.assertEquals("Order Has Not Been Confirmed",orderConfirmation,realorderConfirmation);
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));
        String expectedWelcomeMessage = "Welcome to our store";
        String actualWelcomeMessage = getTextFromElement(By.xpath("//h2[text() ='Welcome to our store']"));
        Assert.assertEquals("Welcome to store",expectedWelcomeMessage,actualWelcomeMessage);
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[@class = 'ico-logout']"));
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,"https://demo.nopcommerce.com/");


    }
    @After
    public void tearDown(){
        closeBrowser();
    }
}
