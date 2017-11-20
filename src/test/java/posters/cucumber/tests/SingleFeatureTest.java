package posters.cucumber.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/posters/cucumber/features/RegisterFromUserMenu.feature", glue = "posters", monochrome = true, plugin =
{
  "pretty", // console output
  "html:target/cucumber-report/", // html report
})
public class SingleFeatureTest
{
}