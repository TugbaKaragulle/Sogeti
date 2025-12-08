package Sogeti.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Sogeti.utilities.Driver.getDriver;

public class JavascriptUtils {


    public static void clickElementByJS(WebElement element) {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) getDriver());
        jsexecutor.executeScript("arguments[0].click();", element);
    }

    public static void safeClickWithJS(WebElement element) {
        JavascriptExecutor jsexecutor = (JavascriptExecutor) getDriver();
        try {
            // Scroll ile ortalanır
            jsexecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

            // Tıklama
            jsexecutor.executeScript("arguments[0].click();", element);

            // Loglama için güvenli getText
            String elementText = "";
            try {
                elementText = element.getText();
            } catch (StaleElementReferenceException e) {
                elementText = "[Element Text Unavailable - Stale]";
            }


        } catch (Exception e) {

        }
    }

    // JavaScript kullanarak sayfa başlığını alır.
    public static String getTitleByJS() {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) getDriver());
        String title = jsexecutor.executeScript("return document.title;").toString();
        return title;
    }

    // Sayfayı en alta doğru kaydırır.
    public static void scrollDownByJS() {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) getDriver());
        jsexecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    // Sayfayı en üste doğru kaydırır.
    public static void scrollAllUpByJS() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }

    // Belirtilen WebElement'e JavaScript kullanarak odaklanır, yani görünür hale getirir.
    public static void scrollIntoViewJS(WebElement element) {
        JavascriptExecutor jsexecutor = (JavascriptExecutor) getDriver();
        jsexecutor.executeScript("arguments[0].scrollIntoView();", element);

    }

    // Belirtilen WebElement'in arkaplan rengini değiştirir ve ardından eski rengine geri döner, bir tür "flash" efekti oluşturur.
    public static void changeBackgroundColorByJS(WebElement element, String color) {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) getDriver());
        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Belirtilen WebElement'in arkaplan rengini hızlı bir şekilde değiştirir, bir tür "flash" efekti oluşturur.
    public static void flash(WebElement element, String color) {
        String bgColor = element.getCssValue("backgroundcolor");
        for (int i = 0; i < 5; i++) {
            changeBackgroundColorByJS(element, color);
            changeBackgroundColorByJS(element, bgColor);
        }
    }

    // Belirtilen mesaj ile bir JavaScript uyarısı oluşturur.
    public static void generateAlert(String message) {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) getDriver());
        javascriptExecutor.executeScript("alert('" + message + "')");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Belirtilen JavaScript komutunu belirtilen WebElement üzerinde çalıştırır.
    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript(command, element);
    }

    // Belirtilen JavaScript komutunu çalıştırır.
    public static void executeJScommand(String command) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript(command);
    }

    // Belirtilen WebElement'in değerini JavaScript kullanarak ayarlar.
    public static void setValueByJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
    }

    // Belirtilen elementin değerini JavaScript kullanarak alır ve ekrana yazdırır.
    public static void getValueByJS(String idOfElement) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String value = js.executeScript("return document.getElementById('" + idOfElement + "').value").toString();
        System.out.println(value);
    }

    // Belirtilen WebElement'e belirtilen kenarlık stiliyle bir kenarlık ekler.
    public static void addBorderWithJS(WebElement element, String borderStyle) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].style.border='" + borderStyle + "'", element);
    }

    // Belirtilen WebElement'in değerini JavaScript kullanarak alır.
    public static Object getElementValueJS(WebElement element) {
        return ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].value", element);

    }

    // A function that scrolls the page up to the specified element.
    public static void scrollToVisibleElement(WebDriver driver, WebElement element) {
        // Wait until the element is visible
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

    // A function that scrolls to the top of the page.
    public static void scrollToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    // Kann als sicherer Fallback verwendet werden, wenn Actions.moveToElement() im Headless-Modus oder bei Hover-Problemen nicht funktioniert.
    public static void mouseOverByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String mouseOverScript =
                "var evObj = document.createEvent('MouseEvents');" +
                        "evObj.initMouseEvent('mouseover', true, true, window, 1, 0,0,0,0, false,false,false,false,0,null);" +
                        "arguments[0].dispatchEvent(evObj);";
        js.executeScript(mouseOverScript, element);
    }
}




