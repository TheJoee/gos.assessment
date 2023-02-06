package com.gospace.assesment.pages;

import com.gospace.assesment.utils.HelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewRepositoryPage {
    WebDriver driver;
    @FindBy(id = "new_repository")
    @CacheLookup
    private WebElement newRepositoryElement;

    @FindBy(id = "repository_name")
    @CacheLookup
    private WebElement repositoryNameField;

    @FindBy(xpath = "//*[@id=\"repository_name\"][contains(@class, 'is-autocheck-successful')]")
    private WebElement repositoryNameOk;

    @FindBy(id = "repository_description")
    @CacheLookup
    private WebElement repositoryDescriptionField;

    @FindBy(xpath = "//*[@id=\"new_repository\"]/*/button[contains(text(), 'Create repository')]")
    @CacheLookup
    private WebElement createRepositoryButton;

    public NewRepositoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void onNewRepositoryPage(WebDriver driver){
        HelperMethods.waitForPageToBeReady(driver);
        try{
            HelperMethods.waitForElementToBeVisible(driver, newRepositoryElement);
        } catch (Exception e){
        }
    }

    public void fillNewRepositoryFields(WebDriver driver, String repoName){
        repositoryNameField.sendKeys(repoName);
        repositoryDescriptionField.sendKeys("This is a test repository");
        HelperMethods.waitForElementToBeVisible(driver, repositoryNameOk);
    }
    public void submitNewRepositoryForm(WebDriver driver){
        HelperMethods.waitForElementToBeClickable(driver, createRepositoryButton);
        HelperMethods.waitAndClick(driver, createRepositoryButton);
        HelperMethods.waitForPageToBeReady(driver);
    }


}
