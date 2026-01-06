package otus.steps;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("scenarious")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "otus" )

public class RunnerTest {
}

