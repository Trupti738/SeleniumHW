package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.xml.datatype.Duration;
import java.sql.Timestamp;


import java.util.concurrent.TimeUnit;

public class TestSuit {
    protected static WebDriver driver;
    static String expectedRegistrationCompleteMsg="Your registration completed";
    // static String actualResult="Your registration completed";
    static String expectedVoteSuccessfully="Only Registered User Can Vote";
   //  static String actualResult ="Only Registered User Can Vote";
    static String expectedCompareProductMessage= "you have no items to compare";
    static String expectedElectronics="Leica T Mirrorless Digital Camera";
    static String expectedEmailToFriend="Only registered customers can use email a friend feature";
    static String expectedAbleToVote="votes....";
    @BeforeMethod
    public static void openDrive(){
        driver= new ChromeDriver();
        driver.get("https://demo.nopcommerce.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterMethod
    public static void closeBrowser() {
        driver.close();

    }



    @Test
    public static void verifyUserShouldBeAbleToRegisterSuccessfully(){
        //click on register
        clickOnElement(By.className("ico-register"));
        //type first name
        typeText(By.id("FirstName"),"raj");
        //type last name
        typeText(By.id("LastName"),"patel");
        //type  email address
        typeText(By.name("Email"),"rp28+"+ timestamp()+"+@gmail.com");
        //type password
        typeText(By.name("Password"),"test1234");
        //type confirm password
        typeText(By.id("ConfirmPassword"),"test1234");
        //click on register submit button
        clickOnElement(By.id("register-button"));
        String actualMessage=getTextFromElement(By.xpath("//div[@class='result']"));
        System.out.println("My message: "+actualMessage);
        Assert.assertEquals(actualMessage,expectedRegistrationCompleteMsg,"registration not working");
    }
    @Test
     public static  void verifyUserShouldBeAbleToVote  ()     {
        //click on good button
        clickOnElement(By.id("pollanswers-2"));
        //Click on vote button
        clickOnElement(By.id("vote-poll-1"));
        //Use the selenium wait
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //get the text message for web element
       // wait.until (ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"poll-vote-error\"]")));

        String actualMessage = getTextFromElement(By.xpath("//div[@class=\"poll-vote-error\"]"));
        System.out.println("My message:" + actualMessage);
        //text message  is disappearing
        Assert.assertEquals(actualMessage,expectedVoteSuccessfully , "Error message is disappearing.");



    }
    @Test
    public static void verifyUserBeAbleToCompareProduct() {


    clickOnElement(By.linkText("HTC One M8 Android L 5.0 Lollipop"));
    //click compare button
    clickOnElement(By.xpath("//div[@class='compare-products']/button"));
    //select product
    clickOnElement(By.linkText("Gift Cards"));
    clickOnElement(By.linkText("$25 Virtual Gift Card"));
    clickOnElement(By.xpath("//div[@class='compare-products']/button"));
    //product comparison
    clickOnElement(By.linkText("product comparison"));
    String actualMessage = getTextFromElement(By.linkText("HTC One M8 Android L 5.0 Lollipop"));
        System.out.println("My Message :" + actualMessage);
    String actualMessage1 = getTextFromElement(By.linkText("$25 Virtual Gift Card"));
        System.out.println("My Message :" + actualMessage1);
    clickOnElement(By.className("clear-list"));
    String actualMessage2 = getTextFromElement(By.className("no-data"));
        System.out.println("MyMessage :" + actualMessage2);
        Assert.assertEquals(actualMessage2, expectedCompareProductMessage, "Can not comparing product");}



    @Test
    public static void verifyUserBeAbleToAddElectronicsItems(){
        //click on electronics
        clickOnElement((By.linkText("Electronics")));
        //click on camera and photo
        clickOnElement(By.linkText("Camera & photo"));
        //add digital camera
        clickOnElement(By.linkText("Leica T Mirrorless Digital Camera"));
        clickOnElement(By.id("add-to-cart-button-16"));
        //click on shopping cart
        clickOnElement(By.linkText("Shopping cart"));
        String actualMessage=getTextFromElement(By.xpath("//*[@id=\"shopping-cart-form\"]/div[1]/table/tbody/tr/td[3]"));
        System.out.println("my message;"+actualMessage);
        Assert.assertEquals(actualMessage,expectedElectronics,"product not adding");
    }
    @Test
public static void verifyUserShouldBeAbleToSendEmail(){
        //click on Apple MacBook
        clickOnElement(By.linkText("Apple MacBook Pro 13-inch"));
        //click email a friend
        clickOnElement((By.className("email-a-friend")));
        //type friend email
        typeText(By.className("friend-email"),"001+"+timestamp()+"@gmail.com");
        //type your email
        typeText(By.className("your-email"),"002+"+timestamp()+"@mail.com");
        //type personal message
        typeText(By.id("PersonalMessage"),"here is your mac");
        //click on send mail
        clickOnElement(By.name("send-email"));
        String actualMessage = getTextFromElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]"));
        System.out.println("my message: " +actualMessage);
        Assert.assertEquals(actualMessage,expectedEmailToFriend,"Only registered customers can use email a friend feature");
    }
    @Test
    public static void verifyUserShouldBeAbleToCountVote(){
        clickOnElement(By.className("ico-register"));
        //type first name
        typeText(By.id("FirstName"),"raj");
        //type last name
        typeText(By.id("LastName"),"patel");
        //type  email address
        typeText(By.name("Email"),"rp28+"+ timestamp()+"+@gmail.com");
        //type password
        typeText(By.name("Password"),"test1234");
        //type confirm password
        typeText(By.id("ConfirmPassword"),"test1234");
        //click on register submit button
        clickOnElement(By.id("register-button"));
        clickOnElement(By.className("ico-login"));
        typeText(By.id("Email"),"rp28@gmail.com");
        typeText(By.id("Password"),"test1234");
        clickOnElement(By.xpath("div[@class=\"buttons\"]//button[@class=\"button-1 login-button\"]"));
        clickOnElement(By.id("pollanswers-2"));
        clickOnElement(By.id("vote-poll-1"));
        String actualMessage=getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
        System.out.println("My message:"+actualMessage);
        Assert.assertEquals(actualMessage,expectedAbleToVote,"total votes wrong");

    }
    @Test
    public static void verifyUserShouldRefferTotheFriend() {
        clickOnElement(By.className("ico-register"));
        //type first name
        typeText(By.id("FirstName"), "raj");
        //type last name
        typeText(By.id("LastName"), "patel");
        //type email address
        typeText(By.name("Email"), "raj1234@gmail.com");
        //type password
        typeText(By.id("Password"), "raj1234");
        //type confirm password
        typeText(By.id("ConfirmPassword"), "raj1234");
        clickOnElement(By.id("register-button"));
        //click on login button
        clickOnElement(By.className("ico-login"));
        //Type  email address
        //   typeText(By.xpath("//div[@class='form-fields']/div[1]/input"),"raml123@gmail.com");
        typeText(By.id("Email"), "raj1234@gmail.com");
    }

        public static void clickOnElement (By by){
            driver.findElement(by).click();

        }
        public static void typeText (By by, String text){
            driver.findElement(by).sendKeys(text);
        }
        public static String getTextFromElement (By by){
            return driver.findElement(by).getText();

        }
        public static long timestamp () {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            return timestamp.getTime();
        }
        public static void main (String[]args){


        }
    }



