package com.framework.selenium.launcher;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gkola.framework.core.FWObject;
import com.gkola.framework.core.UIDriver;
import com.gkola.framework.core.UIElement;
import com.gkola.framework.drivers.UIAlert;
import com.gkola.framework.elements.DefaultUIElement;
import com.gkola.framework.locators.ElementLocator;
import com.gkola.framework.locators.ElementLocatorByDriver;
import com.gkola.framework.util.ByUtil;
import com.gkola.framework.util.SearchScope;
import com.gkola.framework.util.UIType;




public class DefaultUIDrivers extends FWObject implements UIDriver {
	 private static Logger logger = Logger.getLogger(DefaultUIDrivers.class);
	    protected WebDriver driver;
	    //protected static Config config = DefaultConfig.getConfig(); 
	    private List<LogEntry> logEntries = new ArrayList<LogEntry>();

	    

	    public DefaultUIDrivers(
	            String browser) {
	        DriversLauncher launcher = new DriversLauncher(config);
	        try {
				driver = launcher.launchDrivers(browser);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    /**
	     * @param webDriver
	     */
	    public DefaultUIDrivers(WebDriver webDriver) {
	        driver = webDriver;
	    }

	    /**
	     * Set {@link FirefoxProfile} before lunching {@link FirefoxDriver} <br>
	     * If you are using this constructor you are responsible to initialize {@link FWObject#uiDriver}
	     * instance. <br>
	     * <br>
	     * <b>Consider using following methods instead: </b> <li> {@link BasicTestObject#getStartPage()},
	     * <li> {@link BasicTestObject#launchBrowser(Browser, String)}, <li>
	     * {@link BasicTestObject#launchFirefoxBrowserWithProfile(FirefoxProfile)}, <li>
	     * {@link BasicTestObject#launchFirefoxBrowserWithProfile(FirefoxProfile, String)} <li>
	     * {@link BasicTestObject#launchBrowserWithDesiredCapabilities(DesiredCapabilities)}
	     * 
	     * 
	     * @param profile
	     */
	    /*
	     * public DefaultUIDriver(FirefoxProfile profile) {
	     * DriverLauncher launcher = new DriverLauncher(config);
	     * launcher.setFirefoxProfile(profile);
	     * driver = launcher.launchDriver(Browser.FIREFOX);
	     * }
	     */

	    /**
	     * Set {@link DesiredCapabilities} before lunching {@link UIDriver} <br>
	     * If you are using this constructor you are responsible to initialize {@link FWObject#uiDriver}
	     * instance. <br>
	     * <br>
	     * <b>Consider using following methods instead: </b> <li> {@link BasicTestObject#getStartPage()},
	     * <li> {@link BasicTestObject#launchBrowser(Browser, String)}, <li>
	     * {@link BasicTestObject#launchFirefoxBrowserWithProfile(FirefoxProfile)}, <li>
	     * {@link BasicTestObject#launchFirefoxBrowserWithProfile(FirefoxProfile, String)},<li>
	     * {@link BasicTestObject#launchBrowserWithDesiredCapabilities(DesiredCapabilities)}
	     * 
	     * @param cap
	     */
	    /*
	     * public DefaultUIDrives(DesiredCapabilities cap) {
	     * DriverLauncher launcher = new DriverLauncher(config);
	     * launcher.setDesiredCapabilities(cap);
	     * driver = launcher.launchDriver(Browser.valueOf(config.getValue(
	     * ConfigKeys.KEY_BROWSER.getKey()).toUpperCase()));
	     * }
	     */

	/**
	         * If you are using this constructor you are responsible to initialize {@link FWObject#uiDriver}
	         * instance. <br>
	         * <br>
	         * <b>Consider using following methods instead: </b> <li> {@link BasicTestObject#getStartPage()},
	         * <li> {@link BasicTestObject#launchBrowser(Browser, String)}, <li>
	         * {@link BasicTestObject#launchFirefoxBrowserWithProfile(FirefoxProfile)}, <li>
	         * {@link BasicTestObject#launchFirefoxBrowserWithProfile(FirefoxProfile, String)
	         * 
	         * @link BasicTestObject#launchBrowserWithDesiredCapabilities(DesiredCapabilities)}
	         * 
	         * @param browser
	         */
	    /*
	     * public DefaultUIDriver(Browser browser) {
	     * DriverLauncher launcher = new DriverLauncher(config);
	     * driver = launcher.launchDriver(browser);
	     * }
	     */

	    /**
	     * @param newDriver
	     * @return see {@link Capabilities}
	     */
	    private static Capabilities getCapabilities(WebDriver newDriver) {
	        if (newDriver instanceof HasCapabilities) {
	            HasCapabilities version = (HasCapabilities) newDriver;
	            Capabilities cap = version.getCapabilities();
	            return cap;
	        } else {
	            return null;
	        }
	    }

	    public void setWebDriver(WebDriver webDriver) {
	        driver = webDriver;
	    }

	    @Override
	    public UIAlert getUIAlert() {
			return null;
	       // return new DefaultAlert(driver);
	    }

	    @Override
	    public void close() {
	        try {
	            String url = driver.getCurrentUrl();
	          
	        } catch (Exception e) {
	            logger.debug("Failed to add current url to zap-proxy listener");
	        }
	        int numOfWindows = driver.getWindowHandles().size();
	        String title = driver.getTitle();
	        if (isAvailable()) {
	            if (numOfWindows == 0) {
	                logger.warn("Driver does not have any windows, quitting WebDriver");
	                quit();
	                return;
	            } else
	                if (numOfWindows == 1) {
	                    logger.debug("'" + title + "' is the only window present. Closing this window and quit webDriver");
	                    driver.close();
	                    sleep(1000);// it takes some time to close webdriver
	                    logger.debug("Quit WebDriver");
	                    return;
	                } else
	                    if (numOfWindows > 1) {
	                        logger.debug("WebDriver found " + numOfWindows + " windows that are open, going to close currently selected window");
	                        driver.close();
	                        while (!(driver.getWindowHandles().size() < numOfWindows)) {
	                            logger.debug("Waiting for '" + title + "' window to close");
	                            try {
	                                Runtime.getRuntime().exec(
	                                        "src/test/resources/config/ClosePrintDialog.exe");
	                            } catch (IOException e) {
	                                e.printStackTrace();
	                            }
	                            this.sleep(100);
	                        }
	                        logger.debug("Closed window titled '" + title + "'");
	                    }
	        }
	    }

	    @Override
	    public void deleteAllCookies() {
	        if (isAvailable()) {
	            driver.manage().deleteAllCookies();
	            logger.debug("Deleted all the cookies for the current domain");
	        }
	    }

	    @Override
	    public Object executeAsyncScript(String arg0, Object... arg1) {
	        Object result = null;
	        if (isAvailable()) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            result = js.executeAsyncScript(arg0, arg1);
	            logger.debug("Running Async JavaScript '" + arg0 + "' with parameters '" + Arrays
	                    .toString(arg1) + "', result: " + result.toString());
	        }
	        return result;
	    }

	    @Override
	    public String executeScript(String script) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        Object executeResult = js.executeScript(script);
	        return executeResult == null ? "" : executeResult.toString();
	    }

	    @Override
	    public Object executeScript(String script, Object... args) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        logger.debug("Running JavaScript '" + script + "' with parameters '" + Arrays
	                .toString(args) + "'");
	        return js.executeScript(script, args);
	    }

	    @Override
	    public WebElement findElement(By by) {
	        WebElement ele = null;
	        if (isAvailable()) {
	            ele = driver.findElement(by);
	        }
	        return ele;
	    }

	    @Override
	    public List<WebElement> findElements(By by) {
	        // logger.debug("Looking for elements by: " + ByUtil.getByStr(by));
	        List<WebElement> list = null;
	        if (isAvailable()) {
	            list = driver.findElements(by);
	        }
	        return list;
	    }

	    @Override
	    public UIElement findUIElement(UIType type, String value) {
	        return findUIElement(type, value, null);
	    }

	    @Override
	    public UIElement findUIElement(UIType type, String value, String description) {
	        ElementLocator locator = new ElementLocatorByDriver(this, ByUtil.getBy(type, value));
	        return new DefaultUIElement(this, locator, description);
	    }

	    @Override
	    public void get(String url) {
	        
	    }

	    @Override
	    public ArrayList<String> getAllLinksOnPage() {
	        List<WebElement> anchors = this.findElements(By.xpath("//a"));
	        ArrayList<String> urls = new ArrayList<String>();
	        for (WebElement a : anchors) {
	            try {
	                String href = a.getAttribute("href");
	                urls.add(href);
	                logger.debug("Found link on page:" + href);
	            } catch (Exception e) {
	                logger.warn("Unable to get href from anchor: " + e.getLocalizedMessage(), e);
	            }
	        }
	        return urls;
	    }

	    @Override
	    public String getAttributeFromDisabledFieldByID(String id, String attribute) {
	        String result = null;
	        result = String.valueOf(((JavascriptExecutor) this).executeScript(
	                "return document.getElementById('" + id + "').getAttribute('" + attribute + "');",
	                ""));
	        logger.debug("Got attribute from disabled field: id=\"" + id + "\", " + attribute + "=\"" + result + "\"");
	        return result;
	    }

	    @Override
	    public Capabilities getCapabilities() {
	        return getCapabilities(driver);
	    }

	    @Override
	    public boolean hasCapability(String capability) {
	        Capabilities cap = this.getCapabilities();
	        if (cap != null) {
	            return cap.getCapability(capability) != null;
	        } else {
	            return false;
	        }
	    }

	    @Override
	    public String getCurrentUrl() {
	        String url = defaultStr;
	        logger.trace("DefaultUIDriver.getCurrentUrl starts as " + defaultStr);
	        if (isAvailable())
	            logger.trace("DefaultUIDriver.getCurrentUrl setting...");
	        url = driver.getCurrentUrl();
	        logger.trace("DefaultUIDriver.getCurrentUrl set to '" + url + "'");
	        return url;
	    }

	    @Override
	    public String getPageSource() {
	        String pageSource = defaultStr;
	        if (isAvailable())
	            pageSource = driver.getPageSource();
	        return pageSource;
	    }

	    /**
	     * JavaScript call {@code return [element].innerHTML; }
	     * 
	     * <p>
	     * Returns the innerHTML of the provided UIElement as returned by the javascript method
	     * [element].innerHTML, if and only if the browser's javascript engine supports innerHTML.
	     * </p>
	     * 
	     * @param element
	     *            as the UIElement
	     * @return the innerHTML of the UIElement
	     */
	    @Override
	    public String getInnerHtmlOf(UIElement element) {
	        return (String) executeScript("return arguments[0].innerHTML;", element.getWebElement());
	    }

	    /**
	     * JavaScript call {@code return [element].outerHTML; }
	     * 
	     * <p>
	     * Returns the outerHTML of the provided UIElement as returned by the javascript method
	     * [element].outerHTML, if and only if the browser's javascript engine supports outerHTML.
	     * </p>
	     * 
	     * @param element
	     *            as the UIElement
	     * @return the outerHTML of the UIElement
	     */
	    @Override
	    public String getOuterHtmlOf(UIElement element) {
	        return (String) executeScript("return arguments[0].outerHTML;", element.getWebElement());
	    }

	    @Override
	    public String getTitle() {
	        String title = defaultStr;
	        if (isAvailable())
	            title = driver.getTitle();
	        return title;
	    }

	    @Override
	    public String getValueFromDisabledFieldByID(String id) {
	        String result = null;
	        result = (String) ((JavascriptExecutor) this).executeScript(
	                "return document.getElementById('" + id + "').value;",
	                "");
	        logger.debug("Got attribute from disabled field: id=\"" + id + "\", value=\"" + result + "\"");
	        return result;
	    }

	    @Override
	    public WebDriver getWebDriver() {
	        return driver;
	    }

	    @Override
	    public String getWindowHandle() {
	        String handle = defaultStr;
	        if (isAvailable())
	            handle = driver.getWindowHandle();
	        return handle;
	    }

	    @Override
	    public Set<String> getWindowHandles() {
	        Set<String> handles = null;
	        if (isAvailable())
	            handles = driver.getWindowHandles();
	        return handles;
	    }

	    @Override
	    public boolean isWindowTitleContains(String substring) {
	        String currentTitle = driver.getTitle();
	        logger.debug("Checking if page title '" + currentTitle + "' contains substring '" + substring + "'");
	        return currentTitle.contains(substring);
	    }

	    @Override
	    public Options manage() {
	        Options manage = null;
	        if (isAvailable())
	            manage = driver.manage();
	        return manage;
	    }

	    @Override
	    public void maximizeWindow() {
	        if (isAvailable()) {
	            driver.manage().window().setPosition(new Point(0, 0));
	            java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	            logger.debug("Maximizing window  new width = " + screenSize.getWidth() + " new height = " + screenSize
	                    .getHeight());
	            Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
	            driver.manage().window().setSize(dim);
	        }
	    }

	    @Override
	    public void moveBrowserToXYHeightWidth(int x, int y, int height, int width) {
	        String jscode = "window.resizeTo(" + height + "," + width + "); window.moveTo(" + x + "," + y + "); window.focus();";
	        try {
	            logger.debug("Moving browser to X = " + x + ", Y = " + y + ", HEIGHT =  " + height + ", WIDTH = " + width);
	            this.executeScript(jscode);
	        } catch (Exception e) {
	            logger.debug("Unable to move browser using javascript:\n" + jscode);
	        }
	    }

	    @Override
	    public Navigation navigate() {
	        Navigation navigate = null;
	        if (isAvailable())
	            navigate = driver.navigate();
	        return navigate;
	    }

	    @Override
	    public void quit() {
	        if (isAvailable()) {
	            try {
	                String url = driver.getCurrentUrl();
	              
	            } catch (Exception e) {
	                logger.debug("Failed to add current url to zap-proxy listener");
	            }
	            logger.debug("Quitting WebDriver");
	            driver.quit();
	            logger.trace("Quit WebDriver");
	        }

	    }

	    @Override
	    public void refresh() {
	        if (isAvailable()) {
	            driver.navigate().refresh();
	        }
	    }

	    @Override
	    public void scrollToPageBottom() {
	        if (isAvailable()) {
	            executeScript("window.scrollTo(0, document.body.scrollHeight)");
	        }
	    }

	    @Override
	    public TargetLocator switchTo() {
	        TargetLocator locator = null;
	        if (isAvailable())
	            locator = driver.switchTo();
	        return locator;
	    }

	    @Override
	    public boolean hasQuit() {
	        try {
	            assertBrowserExists();
	            return false;
	        } catch (SessionNotFoundException snfe) {
	            return true;
	        }
	    }

	    private void assertBrowserExists() {
	        Set<String> handles = driver.getWindowHandles();
	        logger.debug("Found " + handles.size() + " windows while checking if driver quit.");
	    }

	    @Override
	    public void back() {
	        if (isAvailable()) {
	            driver.navigate().back();
	        }

	    }

	    @Override
	    public void forward() {
	        if (isAvailable()) {
	            driver.navigate().forward();
	        }
	    }

	    @Override
	    public void to(String url) {
	        if (isAvailable()) {
	            driver.navigate().to(url);
	        }
	    }

	    @Override
	    public void to(URL url) {
	        if (isAvailable()) {
	            driver.navigate().to(url);
	        }
	    }

	    /**
	     * Wait for page to display {@code text}
	     * 
	     * @param text
	     * @deprecated use {@link #waitForTextToDisplay2(String)}
	     */
	    @Override
	    public void waitForTextToDisplay(String text) {
	        waitForTextToDisplay(text, config_wait_timeout);
	    }

	    /**
	     * Wait for page to display {@code text}
	     * 
	     * @param text
	     * @param timeout
	     * @deprecated use {@link #waitForTextToDisplay2(String, long)}
	     */
	    @Override
	    public void waitForTextToDisplay(String text, long timeout) {
	        logger.debug("Waiting for text: '" + text + "' to appear");
	        if (isAvailable()) {
	            ElementLocator locator = new ElementLocatorByDriver(this, ByUtil.getBy(
	                    UIType.Xpath,
	                    "//*[contains(text(),'" + text + "')]"));
	            UIElement tempEle = new DefaultUIElement(this, locator, text);
	            waitToBeDisplayed(tempEle, timeout);
	        }
	    }

	    @Override
	    public void waitForTextToDisplay2(String text) {
	        waitForTextToDisplay2(text, config_wait_timeout);
	    }

	    @Override
	    public void waitForTextToDisplay2(String text, long timeout) {
	        logger.debug("Waiting for text: '" + text + "' to appear");
	        if (isAvailable()) {
	            ElementLocator locator = new ElementLocatorByDriver(this, ByUtil.getBy(
	                    UIType.Xpath,
	                    "//*[text()[contains(.,'" + text + "')]]"));
	            UIElement tempEle = new DefaultUIElement(this, locator, text);
	            waitToBeDisplayed(tempEle, timeout);
	        }
	    }

	    /**
	     * Wait for text to be hidden
	     * 
	     * @param text
	     * @deprecated use {@link #waitForTextToHide2(String)}
	     */
	    @Override
	    public void waitForTextToHide(String text) {
	        waitForTextToHide(text, config_wait_timeout);
	    }

	    /**
	     * Wait for text to be hidden
	     * 
	     * @param text
	     * @param timeout
	     * @deprecated use {@link #waitForTextToHide2(String, long)}
	     */
	    @Override
	    public void waitForTextToHide(String text, long timeout) {
	        logger.debug("Waiting " + timeout + " ms for text '" + text + "' to disappear");
	        if (isAvailable()) {
	            ElementLocator locator = new ElementLocatorByDriver(this, ByUtil.getBy(
	                    UIType.Xpath,
	                    "//*[contains(text(),'" + text + "')]"));
	            UIElement tempEle = new DefaultUIElement(this, locator, text);
	            waitToBeHidden(tempEle, timeout);
	        }
	    }

	    @Override
	    public void waitForTextToHide2(String text) {
	        waitForTextToHide2(text, config_wait_timeout);
	    }

	    @Override
	    public void waitForTextToHide2(String text, long timeout) {
	        logger.debug("Waiting for text: '" + text + "' to appear");
	        if (isAvailable()) {
	            ElementLocator locator = new ElementLocatorByDriver(this, ByUtil.getBy(
	                    UIType.Xpath,
	                    "//*[text()[contains(.,'" + text + "')]]"));
	            UIElement tempEle = new DefaultUIElement(this, locator, text);
	            waitToBeHidden(tempEle, timeout);
	        }
	    }

	    @Override
	    public void waitToBeAbsent(final UIElement expected) {
	        waitToBeAbsent(expected, config_wait_timeout);
	    }

	    @Override
	    public void waitToBeAbsent(final UIElement expected, long timeout) {
	        if (expected != null) {
	            if (isAvailable()) {
	                expected.waitToBeAbsent(timeout);
	            }
	        }
	    }

	    @Override
	    public void waitToBeDisplayed(final UIElement expected) {
	        waitToBeDisplayed(expected, config_wait_timeout);
	    }

	    @Override
	    public void waitToBeDisplayed(final UIElement expected, long timeout) {
	        if (expected != null) {
	            if (isAvailable()) {
	                expected.waitToBeDisplayed(timeout);
	            }
	        }
	    }

	    @Override
	    public void waitToBeHidden(final UIElement expected) {
	        waitToBeHidden(expected, config_wait_timeout);
	    }

	    @Override
	    public void waitToBeHidden(final UIElement expected, long timeout) {
	        if (expected != null) {
	            if (isAvailable()) {
	                expected.waitToBeHidden(timeout);
	            }
	        }
	    }

	    @Override
	    public void waitToBePresent(final UIElement expected) {
	        waitToBePresent(expected, config_wait_timeout);
	    }

	    @Override
	    public void waitToBePresent(UIElement expected, long timeout) {
	        if (expected != null) {
	            if (isAvailable()) {
	                expected.waitToBePresent(timeout);
	            }
	        }
	    }

	    @Override
	    public boolean isAvailable() {
	        logger.trace("Checking if driver is available for driver == " + driver);
	        if (driver == null) {
	            return false;
	        }
	        boolean isAvailable = false;

	        try {
	            Set<String> handles = driver.getWindowHandles();
	            logger.trace("Number of window handles: " + handles.size());
	            isAvailable = true;
	        } catch (UnreachableBrowserException ube) {
	            logger.warn("Unable to get list of windows. Browser is probably closed. " + ube
	                    .getLocalizedMessage());
	            throw ube;
	        } catch (SessionNotFoundException snf) {
	            logger.warn("Browser can't be used after quit() was called");
	            throw snf;
	        }
	        return isAvailable;
	    }

	    @Override
	    public String[] waitForTextOnPage(final String[] expectedArray, long timeout) {
	        ExpectedCondition<Boolean> matchFound = new ExpectedCondition<Boolean>() {

	            public Boolean apply(WebDriver d) {
	                return (whichTextIsOnPage(expectedArray).length > 0);
	            }
	        };
	        WebDriverWait w = new WebDriverWait(driver, TimeUnit.SECONDS.convert(
	                timeout,
	                TimeUnit.MILLISECONDS));
	        w.until(matchFound);
	        return whichTextIsOnPage(expectedArray);
	    }

	    @Override
	    public String[] waitForTextOnPage(final String[] expectedArray) {
	        return waitForTextOnPage(expectedArray, config_wait_timeout);
	    }

	    @Override
	    public String[] whichTextIsOnPage(final String[] expectedArray) {
	        List<String> presentOnPage = new LinkedList<String>();
	        for (String expected : expectedArray) {
	            if (driver.getPageSource().contains(expected)) {
	                presentOnPage.add(expected);

	            }
	        }
	        String[] stringsOnPage = presentOnPage.toArray(new String[0]);
	        logger.debug("Found '" + Arrays.toString(stringsOnPage) + "' text on page");
	        return stringsOnPage;
	    }

	    //@Override
	    /*public UIWindowLocator getUIWindowLocator() {
	        if (windowLocator == null)
	            windowLocator = new WindowLocator(driver);
	        return windowLocator;
	    }*/

	    /**
	     * TODO always returns DefaultUIDriver <br>
	     * Returns the simple name of the underlying class as given in the source code. Returns an empty
	     * string if the underlying class is anonymous.
	     * 
	     * @return
	     */
	    @Override
	    public String getUIObjectType() {
	        return UIDriver.class.getSimpleName();
	    }

	    @Override
	    public boolean isTextVisible(String text) {
	        boolean result = false;
	        if (isAvailable()) {
	            ElementLocator locator = new ElementLocatorByDriver(this, ByUtil.getBy(
	                    UIType.Xpath,
	                    "//*[contains(text(),'" + text + "')]"));
	            UIElement tempEle = new DefaultUIElement(this, locator, text);
	            result = tempEle.isDisplayed();
	        }
	        logger.debug(result
	                           ? "This text '" + text + "' is visible"
	                           : "This text '" + text + "' is NOT visible");
	        return result;
	    }

	   /* @Override
	    public UIFrameLocator getUIFrameLocator() {
	        return new FrameLocator(this);
	    }
*/
	    @Override
	    public void waitForStallingElementToBeHidden(
	            UIElement stallingElement,
	            long timeToAppear,
	            long timeToBeHidden) {
	        try {
	            stallingElement.waitToBeDisplayed(timeToAppear);
	            logger.debug("stalling element appeared.  will wait for it to disappear.  element: " + stallingElement);
	        } catch (TimeoutException toe) {
	            logger.debug("stalling element did not appear.  continuing with test.");
	            return;
	        }
	        stallingElement.waitToBeHidden(timeToBeHidden);
	        logger.debug("stalling element disappear within timeout.  continuing with test.");

	    }

	    @Override
	    public void waitForTitleToMatch(String title, SearchScope scope) {
	        waitForTitleToMatch(title, scope, config_verify_interval);
	    }

	    @Override
	    public void waitForTitleToChange(final String previousTitle) {
	        waitForTitleToChange(previousTitle, config_wait_timeout);
	    }

	    @Override
	    public void waitForTitleToChange(final String previousTitle, long timeout) {

	        ExpectedCondition<Boolean> nextPage = new ExpectedCondition<Boolean>() {

	            public Boolean apply(WebDriver d) {
	                String newTitle = d.getTitle();
	                logger.debug("Waiting for page title to change, current page title = " + newTitle);
	                boolean titleChanged = !newTitle.equals(previousTitle);
	                return Boolean.valueOf(titleChanged);
	            }
	        };
	        try {
	            WebDriverWait wait = new WebDriverWait(getWebDriver(), TimeUnit.SECONDS.convert(
	                    timeout,
	                    TimeUnit.MILLISECONDS));
	            wait.until(nextPage);
	            logger.info("Page title has changed from '" + previousTitle + "' to '" + this
	                    .getTitle() + "'");
	        } catch (RuntimeException rte) {
	            throw new TimeoutException(
	                    "Timed out waiting for Page Title to change from '" + previousTitle + "'.Caught Exception:" + rte
	                            .getLocalizedMessage());
	        }
	    }

	    @Override
	    public boolean isTextPresentInTitle(String text) {
	        String currentTitle = this.getTitle();
	        if (currentTitle != null)
	            return currentTitle.contains(text);
	        return false;
	    }

	    @Override
	    public String getXPath(WebElement element) {
	        logger.trace("Calculating XPATH for element " + element);
	        String jscript = "function getPathTo(node) {" + "  var stack = [];" + "  while(node.parentNode !== null) {" + "    stack.unshift(node.tagName);" + "    node = node.parentNode;" + "  }" + "  return stack.join('/');" + "}" + "return getPathTo(arguments[0]);";
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        Object executeResult = null;
	        try {
	            executeResult = js.executeScript(jscript, element);
	        } catch (Exception e) {
	            logger.debug("Failed to get XPATH for element " + element);
	        }
	        return executeResult == null ? "" : executeResult.toString();
	    }

	    @Override
	    public void sendKeysToBrowser(CharSequence... keysToSend) {
	        Actions actions = new Actions(this.getWebDriver());
	        actions.sendKeys(keysToSend).build().perform();
	    }

	    @Override
	    public void waitForTitleToMatch(String title, SearchScope scope, long timeout) {
	        long limit = System.currentTimeMillis() + timeout;
	        String currentTitle = "";
	        while (System.currentTimeMillis() < limit) {
	            currentTitle = driver.getTitle();
	            if (currentTitle != null)
	                switch (scope) {
	                    case CONTAINS:
	                        if (currentTitle.contains(title)) {
	                            return;
	                        }
	                        break;
	                    case EQUALS:
	                        if (currentTitle.equals(title)) {
	                            return;
	                        }
	                        break;
	                }
	            sleep(config_verify_interval);
	        }
	        throw new TimeoutException(
	                "Expected title to " + scope.toString() + " '" + title + "' waited for " + timeout + " ms. Instead found '" + currentTitle + "' as current title.");

	    }

	    @Override
	    public Dimension getClientScreenSize() {
	        // TODO Auto-generated method stub
	        return null;
	    }

	    @Override
	    public Dimension getWindowSize() {
	        // TODO Auto-generated method stub
	        return null;
	    }

	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.pearson.test.eselenium.framework.core.UIDriver#getConsoleLog(java.util.logging.Level)
	     */
	    @Override
	    public LogEntries getConsoleLog(Level levelFilter) {
	        try {
	            logger.info("Capturing Javascript Console greater than warnings.");
	            this.logEntries.addAll(this.manage().logs().get(LogType.BROWSER).getAll());
	            return new LogEntries(new LogEntries(logEntries).filter(levelFilter));
	        } catch (Exception e) {
	            logger.info("Could not caputure JS Console Log.");
	            return new LogEntries(Collections.<LogEntry> emptyList());
	        }
	    }

	    /*
	     * (non-Javadoc)
	     * 
	     * @see com.pearson.test.eselenium.framework.core.UIDriver#isPhysicalIosDevice()
	     */
	    @Override
	    public boolean isPhysicalIosDevice() {
	        // TODO Auto-generated method stub
	        return false;
	    }
}
