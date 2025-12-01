package Sogeti.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/failed_tests/failed_scenarios.txt",
        glue = "stepdefinitions",
        plugin = {"html:target/reports/report.html",
                "rerun:target/failed_tests/failed_scenarios.txt",
                "pretty"}
)
public class FailedRunner extends AbstractTestNGCucumberTests {
}
