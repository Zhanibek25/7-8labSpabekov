import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources"}, monochrome = false,
        glue={"steps"}, tags = "@Case1")

public class runner {
}
