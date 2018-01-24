package com.khospodarysko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Wait for all spinners to disappear.
 */
public class SpinnersTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SpinnersTest.class);

    @Test
    public void test() {
        driver.get("file://" + absoluteFilePath("spinners"));

        logger.info("{}", areSpinnersLoaded());
    }

    /**
     * @return true if all spinners have disappeared, false otherwise
     */
    public boolean areSpinnersLoaded() {
        return false;
    }
}
