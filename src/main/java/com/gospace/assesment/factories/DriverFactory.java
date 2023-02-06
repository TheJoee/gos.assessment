package com.gospace.assesment.factories;

import com.gospace.assesment.utils.BrowserCapabilities;
import com.gospace.assesment.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


@Slf4j
public class DriverFactory {
    private static final String runMethod = ConfigReader.getProperty("RUN_METHOD");
    private static final String browser = ConfigReader.getProperty("BROWSER");
    private static final String gridURL = ConfigReader.getProperty("GRID_URL");

    private DriverFactory() {
        throw new IllegalStateException("Static factory class");
    }

    public static WebDriver getDriver() throws MalformedURLException {
        log.info("Getting driver for host: {}", runMethod);
        switch (runMethod) {
            case "docker":
            case "local":
                return getLocalWebDriver();
            case "grid":
                return getRemoteWebDriver();
            default:
                throw new IllegalStateException(String.format("%s is not a valid runMethod choice.Options are: 'local', 'docker' and 'grid'",runMethod));
        }
    }

    private static WebDriver getLocalWebDriver() {
        log.info("Getting driver for browser: {}", browser);
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(BrowserCapabilities.getChromeOptions());
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(BrowserCapabilities.getFirefoxOptions());
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                throw new IllegalStateException(String.format("%s is not a valid browser choice. Options are: 'chrome', 'firefox' and 'edge'",browser));
        }
    }

    private static WebDriver getRemoteWebDriver() throws MalformedURLException {
        Capabilities chromeCapabilities = BrowserCapabilities.getChromeOptions();
        Capabilities firefoxCapabilities = BrowserCapabilities.getFirefoxOptions();
        Capabilities edgeCapabilities = BrowserCapabilities.getEdgeOptions();
        URL newgridURL = new URL(gridURL);
        switch (browser) {
            case "chrome":
                return new RemoteWebDriver(newgridURL, chromeCapabilities);
            case "firefox":
                return new RemoteWebDriver(newgridURL, firefoxCapabilities);
            case "edge":
                return new RemoteWebDriver(newgridURL, edgeCapabilities);
            default:
                throw new IllegalStateException("%s is not a valid browser choice. Options are: 'chrome', 'firefox' and 'edge' ");
        }
    }
}