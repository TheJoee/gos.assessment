package com.gospace.assesment.utils;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;

import static java.lang.Boolean.parseBoolean;

public class BrowserCapabilities {
    private static final boolean headless = parseBoolean(ConfigReader.getProperty("HEADLESS"));
    private static final boolean acceptInsecureCertificates = parseBoolean(ConfigReader.getProperty("ACCEPT_INSECURE_CERTIFICATES"));
    private static final String seleniumLogLevel = ConfigReader.getProperty("SELENIUM_LOG_LEVEL").toUpperCase();
    private static final String downloadsDir = ConfigReader.getProperty("DOWNLOADS_DIR");

    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(acceptInsecureCertificates);
        chromeOptions.setHeadless(headless);
        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems and a must-have step to run tests in docker pipeline or docker selenium grid
        chromeOptions.addArguments("--no-sandbox"); // overcome limited resource problems and a should-have step to run tests in docker pipeline or docker selenium grid
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--enable-javascript");
        chromeOptions.addArguments("--disable-notifications");

        Map<String, Object> prefs = new Hashtable<>();
        prefs.put("plugins.always_open_pdf_externally", true);
        prefs.put("download.default_directory", String.format("%s\\%s", System.getProperty("user.dir"), downloadsDir));
        prefs.put("profile.default_content_settings.cookies", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.parse(seleniumLogLevel));
        chromeOptions.setCapability("goog:loggingPrefs", logPrefs);
        chromeOptions.setCapability("browserVersion", "107.0");
        return chromeOptions;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(acceptInsecureCertificates);
        firefoxOptions.setHeadless(headless);
        return firefoxOptions;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setAcceptInsecureCerts(acceptInsecureCertificates);
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return edgeOptions;
    }
}