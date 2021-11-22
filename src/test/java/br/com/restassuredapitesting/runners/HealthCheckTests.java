package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.ping.tests.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.HealthCheckTests.class)
@Suite.SuiteClasses ({
        GetPingTest.class
})

public class HealthCheckTests {
}
