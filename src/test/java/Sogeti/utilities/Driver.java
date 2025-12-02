package Sogeti.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;

import java.time.Duration;

public class Driver {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static ThreadLocal<String> browserThread = new ThreadLocal<>();

    private Driver() {
    }// new keyword'u ile bu classtan obje oluşturulmasının önüne geçilir.

    public static WebDriver getDriver() {

        // If no WebDriver instance is assigned to the current thread, create a new one
        if (driverThread.get() == null) {
            String browser = browserThread.get();
            if (browser == null) {
                browser = ConfigReader.getProperty("browser"); // Fallback: properties dosyasından
            }
            switch (browser) {
                case "edge":
                    driverThread.set(new EdgeDriver());
                    break;
                case "chrome":
                    driverThread.set(new ChromeDriver());
                    break;
                case "firefox":
                    driverThread.set(new FirefoxDriver());
                    break;
                case "headless":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new"); // yeni headless mod
                    options.addArguments("--window-size=1920,1080"); // ekran boyutu belirle
                    options.addArguments("--disable-gpu"); // bazı sistemlerde gerekli
                    options.addArguments("--no-sandbox"); // Jenkins Linux/CI için gerekebilir
                    driverThread.set(new ChromeDriver(options));
                    break;
                default:
                    driverThread.set(new ChromeDriver());
            }

            // WebDriver configuration common for all instances
            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        // Return the WebDriver instance specific to the current thread
        return driverThread.get();
    }
    public static void setupBrowser(ITestContext context) {
        String browser = context.getCurrentXmlTest().getParameter("browser");
        Driver.setBrowser(browser); // Driver'a parametreyi set et
    }

    /**
     * Sets the browser name for the current test thread.
     *
     * @param browserName browser name (e.g., "chrome", "firefox", "edge", "headless")
     */
    public static void setBrowser(String browserName) {
        browserThread.set(browserName);
    }

    /**
     * Quits and removes the WebDriver instance for the current thread to clean up resources.
     */
    public static void closeDriver() {

        // Quit and remove WebDriver instance for the current thread
        if (driverThread.get() != null) {
            try {
                Thread.sleep(3000); // Optional sleep, can be removed if not needed
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            driverThread.get().quit();
            driverThread.remove(); // Remove instance to prevent memory leaks
            browserThread.remove();
        }
    }

}