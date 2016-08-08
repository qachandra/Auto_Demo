package com.framework.selenium.launcher;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.gkola.framework.core.Browser;
import com.gkola.framework.core.Config;
import com.gkola.framework.core.ConfigKeys;

public class DriversLauncher {
	
	private static Logger logger = Logger.getLogger(DriversLauncher.class);
    private Config config = null;

    public DriversLauncher(Config config) {
        this.config = config;

    }
    
    public WebDriver launchDrivers(
            String browserStr
            /*String runOnSauceLabs,
            String operatingSystem,
            String browserVersion*/) throws Exception {

        // String browserStr = config.getValue(ConfigKeys.KEY_BROWSER.getKey()).toUpperCase();

        Browser browser;
        browserStr = browserStr.toUpperCase();
        try {
            browser = Browser.valueOf(browserStr);

        } catch (IllegalArgumentException illegalArgument) {
            logger.warn("Failed to parse browser type '" + browserStr + "', setting browser to firefox");
            // TODO browser = Browser.DEFAULT;
            browser = Browser.FIREFOX;
        }
        String driverURL = config.getValue(ConfigKeys.KEY_DRIVER_URL.getKey());
        if (driverURL == null || "".equals(driverURL))
            driverURL = "localhost";
        if ("localhost".equals(driverURL.trim()))
            try {
                return getLocalWebDriver(browser/*, runOnSauceLabs, operatingSystem, browserVersion*/);
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
        else {
            if (!driverURL.contains("http"))
                driverURL = "http://" + driverURL;
            DesiredCapabilities cap = getCapabilities(browser);
            try {
                return new RemoteWebDriver(new URL(driverURL), cap);
            } catch (MalformedURLException e) {
                logger.debug(e);
            }
        }
        return null;
    }
	private WebDriver getLocalWebDriver(
            Browser browser
            /*String runOnSauceLabs,
            String operatingSystem,
            String browserVersion*/) throws Exception {
        
            switch (browser) {
                case FIREFOX:

                    FirefoxProfile prof = new FirefoxProfile();
                    prof.setAssumeUntrustedCertificateIssuer(true);
                    prof.setPreference("Browser.link.open_newwindow.restriction", 1);
                    prof.setPreference("privacy.popups.policy", 0);
                    return new FirefoxDriver(prof);

                case CHROME:
                    String chromeDriverPath = config.getValue(ConfigKeys.KEY_CHROME_DRIVER_PATH
                            .getKey());
                    chromeDriverPath = checkDriverPath(
                            chromeDriverPath,
                            "/drivers/chromedriver.exe");
                    System.setProperty(ConfigKeys.KEY_CHROME_DRIVER.getKey(), chromeDriverPath);
                    // return new ChromeDriver();
                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("test-type");
                    capabilities.setCapability("chrome.binary", chromeDriverPath);
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    return new ChromeDriver(capabilities);
                case INTERNETEXPLORER:

                    String ieDriverPath = System.getenv(ConfigKeys.KEY_IE_DRIVER.getKey());
                    if (ieDriverPath == null) {
                        ieDriverPath = config.getValue(ConfigKeys.KEY_IE_DRIVER_PATH.getKey());
                        ieDriverPath = checkDriverPath(ieDriverPath, "/drivers/iedriver.exe");
                    }
                    System.setProperty(ConfigKeys.KEY_IE_DRIVER.getKey(), ieDriverPath);
                    DesiredCapabilities capabilities1 = new DesiredCapabilities();
                    capabilities1.setCapability("ignoreProtectedModeSettings", true);
                    capabilities1.setCapability("ignoreZoomLevel", true);
                    capabilities1.setCapability("requireWindowFocus", true);
                    capabilities1.setCapability("logLevel", "FATAL");
                    capabilities1.setJavascriptEnabled(true);
                    capabilities1.setCapability(
                            CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
                            true);
                    /* capabilities1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); */
                    capabilities1.setCapability("ignoreProtectedModeSettings", true);

                    return new InternetExplorerDriver(capabilities1);

                case SAFARI:
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability("cleanSession", true);
                    return new SafariDriver(caps);
                    /*
                     * case ANDROID:
                     * return new AndroidDriver();
                     */

                case HTMLUNIT:
                    DesiredCapabilities caps1 = DesiredCapabilities.htmlUnitWithJs();
                    // caps = DesiredCapabilities.htmlUnitWithJs();
                    caps1.setJavascriptEnabled(true);
                    // caps.setCapability("cleanSession", true);
                    return new HtmlUnitDriver(caps1);

                default:
                    return new FirefoxDriver();
            }
        
        }
    

    private static DesiredCapabilities getCapabilities(Browser browser) {
        switch (browser) {
            case FIREFOX:
                // desiredCapabilities.setCapability(CapabilityType.VERSION,
                // testConfiguration.getBrowserVersion());
                // desiredCapabilities.setCapability("deviceName", "");
                return DesiredCapabilities.firefox();
            case CHROME:
                return DesiredCapabilities.chrome();
            case INTERNETEXPLORER:
                return DesiredCapabilities.internetExplorer();
            case SAFARI:
                return DesiredCapabilities.safari();
            case HTMLUNITWITHJS:
                return DesiredCapabilities.htmlUnitWithJs();
            default:
                return DesiredCapabilities.firefox();
        }
    }

    private String checkDriverPath(String path, String defaultPath) throws Exception {
        if ("".equals(path) || path.startsWith("/")) {
            if ("".equals(path))
                path = defaultPath;
            URL url = getClass().getResource(path);
            if (url != null)
                path = url.getFile();
            else
                throw new Exception(path);
        } else {
            File file = new File(path);
            if (!file.exists())
                throw new Exception(path);
        }
        return path;
    }
}
