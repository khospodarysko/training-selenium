package com.khospodarysko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class SpinnersTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SpinnersTest.class);

    @Test
    public void test() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/spinners.html");

        logger.info("{}", areSpinnersLoaded());
    }

    /**
     * @return true if all spinners have disappeared, false otherwise
     */
    public boolean areSpinnersLoaded() {
        return false;
    }
}
