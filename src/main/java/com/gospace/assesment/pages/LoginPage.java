package com.gospace.assesment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"login\"]/div[1]/h1[text()=\"Sign in to GitHub\"]")
    @CacheLookup
    private WebElement loginPageTitle;

    @FindBy(id = "login_field")
    @CacheLookup
    private WebElement loginField;

    @FindBy(id="password")
    @CacheLookup
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"login\"]/div[4]/form/div/input[@data-signin-label='Sign in']")
    @CacheLookup
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean onLoginPage(){
        return loginPageTitle.isDisplayed();
    }
    public void enterLoginInfo(String username, String password) {
        loginField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    public void pressLoginButton(){
        loginButton.click();
    }
}
