package com.gospace.assesment.pages;

import com.gospace.assesment.utils.HelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
     final WebDriver driver;

    @FindBy(id = "dashboard")
    @CacheLookup
    private WebElement dashboardElement;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean onDashboardPage(WebDriver driver){
        HelperMethods.waitForPageToBeReady(driver);
        try{
            HelperMethods.waitForElementToBeVisible(driver, dashboardElement);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
