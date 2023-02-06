package com.gospace.assesment.pages;

import com.bastiaanjansen.otp.TOTP;
import com.gospace.assesment.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.URI;
import java.net.URISyntaxException;

import static com.gospace.assesment.utils.HelperMethods.waitForElementToBeClickable;

public class TwoFactorPage {
    WebDriver driver;
    @FindBy(xpath = "//*[@id=\"login\"]/div[1]/h1[text()=\"Two-factor authentication\"]")
    @CacheLookup
    private WebElement otpPageTitle;

    @FindBy(id = "app_totp")
    @CacheLookup
    private WebElement otpField;

    @FindBy(xpath="//*[@id=\"login\"]/div[5]/div[2]/form/button[contains(text(), 'Verify')]")
    @CacheLookup
    private WebElement verifyButton;

    public TwoFactorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private String generateOTRP() throws URISyntaxException {
        String username = ConfigReader.getProperty("USERNAME");
        String secret = ConfigReader.getProperty("OTP_KEY");
        String uriString = "otpauth://totp/GitHub:" + username + "?secret=" + secret + "&issuer=GitHub";
        URI uri = new URI(uriString);
        TOTP totp = TOTP.fromURI(uri);
        return totp.now();
    }
    public void enterOTP() throws URISyntaxException {
        String otpCode = generateOTRP();
        otpField.sendKeys(otpCode);
    }
    public void enterOTPIfNeeded() throws URISyntaxException {
        boolean otpOnStatus = Boolean.parseBoolean(ConfigReader.getProperty("OTP"));
        if (otpOnStatus){
            waitForElementToBeClickable(driver, verifyButton);
            enterOTP();
        }
    }
}
