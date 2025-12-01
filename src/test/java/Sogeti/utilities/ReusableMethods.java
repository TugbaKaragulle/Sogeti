package Sogeti.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static Sogeti.utilities.Driver.getDriver;


public class ReusableMethods {

    // CLICK METHODS------------------------------------------------------------------------------------------------------
    public static void clickElement(By by) {
        getDriver().findElement(by).click();
    }

    public static WebElement clickElementByWebDriverWait(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(by)));
    }

    public static void clickElementByJS(By by) {
        JavascriptUtils.clickElementByJS(getDriver().findElement(by));
    }

    // SENDKEYS METHODS------------------------------------------------------------------------------------------------------
    public static void sendKeys(By by, String data) {
        getDriver().findElement(by).sendKeys(data);
    }

    // ISDISPLAYED / ISVISIBLE  METHODS------------------------------------------------------------------------------------------------------
    public static boolean isDisplayed(By by) {
        return getDriver().findElement(by).isDisplayed();
    }

    public static WebElement    visibilityOfElement(By by){
        return getDriver().findElement(by);
    }
    public static List<WebElement>    visibilityOfElements(By by){
        return getDriver().findElements(by);
    }
    public static WebElement visibilityOfElementByWebDriverWait(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(by)));
    }
    public static List<WebElement> visibilityOfElementsByWebDriverWait(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    //ISENABLED METHODS------------------------------------------------------------------------------------------------------
    public static boolean isEnabled(By by) {
        return getDriver().findElement(by).isEnabled();
    }

    //ISSELECTED METHODS------------------------------------------------------------------------------------------------------
    public static boolean isSelected(By by) {
        return getDriver().findElement(by).isSelected();
    }

    //ISCLICKABLE METHODS------------------------------------------------------------------------------------------------------
    public static boolean isClickableByWebDriverWait(By by) {
        try {
            clickElementByWebDriverWait(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//URL CONTAINS METHOD       * Waits for the current URL to contain the given string.

    public static boolean urlContainsByWebDriverWait(String data) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.urlContains(data));
        } catch (TimeoutException e) {
            return false;
        }
    }

    //GET TEXT OF ELEMENT     * Returns trimmed text of a WebElement.
    public static String getTextOfElement(By by) {
        return getDriver().findElement(by).getText().trim();
    }

    public static String getTextOfElementByJS(By by) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (String) js.executeScript("return arguments[0].innerText;", getDriver().findElement(by));
    }

    //CONVERT ELEMENT TEXT INTO INTEGER    * Extracts and returns an integer from a WebElement's text.  * (e.g. "$1,234" → 1234)
    public static int convertElementTextIntoInteger(WebElement element) {
        String text = element.getText().replaceAll("[^0-9]", "");
        if (text.isEmpty()) {
            throw new NumberFormatException("Element text does not contain any numeric characters: " + element.getText());
        }
        return Integer.parseInt(text);
    }

    //THREAD.SLEEP METHOD
    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Sleep was interrupted");
        }
    }

    //FLUENT WAIT
    public static List<WebElement> waitForVisibilityofElementsByFleuntWait(By by){
        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        return wait.until(driver -> driver.findElements(by));
    }

    //REFRESH PAGE METHOD
    public static void refreshCurrentPage(){
        getDriver().navigate().refresh();
    }


    /**
     * Waits for the page title to contain the given string.
     */
    public static boolean waitForVisibilityOfTitle(String string) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.titleContains(string));
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, By by, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

}

