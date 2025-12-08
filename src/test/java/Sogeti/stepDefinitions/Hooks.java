package Sogeti.stepDefinitions;

import Sogeti.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @After //Her senarya sonrasi calisir
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            // Capture a screenshot of the failed scenario
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            // Attach the screenshot to the report
            scenario.attach(screenshot, "image/png", "Screenshot of failed scenario"+scenario.getName());
        }
        Driver.closeDriver();
    }





}
