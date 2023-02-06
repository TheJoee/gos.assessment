package com.gospace.assesment.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperMethods {

    /**
     * Helper method to click on a web element
     */
    public static void click(WebElement element) {
        element.click();
    }

    /**
     * This method uses both JQuery and Javascript to confirm page has finish loading.
     */
    public static void waitForPageToBeReady(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Wait for Jquery to finish loading first.
        ExpectedCondition<Boolean> jQuery = driver1 -> {
            try {
                {
                    return ((Long) ((JavascriptExecutor) driver1).executeScript("return jQuery.active") == 0);
                }
            } catch (Exception e) {
                return true;
                // This exception means there was no JQuery present.
            }
        };
        // Wait for JavaScript to finish loading after.
        ExpectedCondition<Boolean> js = driver12 -> ((JavascriptExecutor) driver12).executeScript("return document.readyState")
                .toString().equals("complete");
        if (wait.until(jQuery)) {
            wait.until(js);
        }
    }

    /**
     * This method waits for an element to be visible.
     */
    public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This method waits for an element to be clickable.
     */
    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This method waits for the element to be clickable and only then clicks.
     */
    public static void waitAndClick(WebDriver driver, WebElement element){
        waitForElementToBeClickable(driver, element);
        click(element);
    }

}
