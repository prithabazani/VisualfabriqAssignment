package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources",
		glue= {"stepDefinitions"},
		tags= "@runall",
	    plugin = { "pretty", "json:target/cucumber-reports/NextBirthdayValidation","html:target/cucumber-reports/CucumberHtml.html" },
		monochrome=true
		)
public class TestRunner {
  
}
