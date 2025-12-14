package Sogeti.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "Sogeti.stepDefinitions",
        tags = "@Bewerbung",
        plugin = {
                "html:target/reports/html_report.html",
                "json:target/reports/json_report.json",
                "rerun:target/failed_tests/failed_scenarios.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "pretty"},
        publish = true,
        dryRun = false
)
public class Runner extends AbstractTestNGCucumberTests {


}