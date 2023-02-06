package com.gospace.assesment.pages;

import com.gospace.assesment.utils.HelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserProfilePage {
    WebDriver driver;
    @FindBy(id = "user-profile-frame")
    @CacheLookup
    private WebElement userProfileFrame;

    @FindBy(xpath = "//*[@itemprop='name codeRepository']")
    @CacheLookup
    private List<WebElement> allReposList;

    @FindBy(xpath = "//*[@id=\"user-profile-frame\"]/div/div[1]/div/div/a[contains(@href,'/new')]")
    @CacheLookup
    private WebElement createNewRepositoryButton;

    @FindBy(id = "your-repos-filter")
    @CacheLookup
    private WebElement searchRepositoryFilter;

    @FindBy(xpath = "//*[@id=\"user-repositories-list\"]/*/div[contains(@class,'user-repo-search-results-summary TableObject-item TableObject-item--primary v-align-top')]")
    @CacheLookup
    private WebElement searchResultsHeader;

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean onRepositoriesTab(WebDriver driver){
        HelperMethods.waitForPageToBeReady(driver);
        try{
            HelperMethods.waitForElementToBeVisible(driver, userProfileFrame);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void createNewRepository(WebDriver driver){
        HelperMethods.waitAndClick(driver, createNewRepositoryButton);
        HelperMethods.waitForPageToBeReady(driver);
    }

    public Boolean doesRepositoryExist(WebDriver driver, String repoName){
        searchRepositoryFilter.sendKeys(repoName);
        HelperMethods.waitForElementToBeVisible(driver, searchResultsHeader);
        int numberOfReposResult = Integer.parseInt(searchResultsHeader.getText().substring(0,1));
        return numberOfReposResult != 0;
    }
}
