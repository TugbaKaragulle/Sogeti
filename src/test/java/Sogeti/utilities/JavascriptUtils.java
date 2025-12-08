package Sogeti.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Sogeti.utilities.Driver.getDriver;

public class JavascriptUtils {


    public static void clickElementByJS(By by) {
        WebElement element = getDriver().findElement(by);
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) getDriver());
        jsexecutor.executeScript("arguments[0].click();", element);
    }

    public static void changeBackgroundColorByJS(By by, String color) {
        WebElement element = getDriver().findElement(by);
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) getDriver());
        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void scrollToVisibleElement(WebDriver driver, By by) {
        WebElement element = getDriver().findElement(by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));

        // Scroll smoothly to the element
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});",
                element);
    }

    public static void seiteLangsamNachUntenScrollen(WebDriver driver, int schritte) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            for (int i = 0; i < schritte; i++) {
                // Her adımda 100 piksel aşağı kaydır
                js.executeScript("window.scrollBy(0, 100);");

                // Belirtilen milisaniye kadar bekle (Gecikmeli akış için)
                Thread.sleep(150);
            }
        } catch (InterruptedException e) {
            // Eğer Thread.sleep kesintiye uğrarsa, mevcut iş parçacığının kesintiye uğradığını ayarla
            Thread.currentThread().interrupt();
            System.err.println("Das Scrollen wurde unterbrochen: " + e.getMessage());
        }
    }


    // Kann als sicherer Fallback verwendet werden, wenn Actions.moveToElement() im Headless-Modus oder bei Hover-Problemen nicht funktioniert.
    public static void mouseOverByJS(By by) {
        WebElement element = getDriver().findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String mouseOverScript =
                "var evObj = document.createEvent('MouseEvents');" +
                        "evObj.initMouseEvent('mouseover', true, true, window, 1, 0,0,0,0, false,false,false,false,0,null);" +
                        "arguments[0].dispatchEvent(evObj);";
        js.executeScript(mouseOverScript, element);
    }
}




