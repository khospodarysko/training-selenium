package com.khospodarysko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class LoggerTest {
    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void testLogger() {
        if (logger.isTraceEnabled()) {
            logger.trace("trace is enabled {}", BigInfo.to(1));
        }

        System.out.println("-------------------- ");

        logger.trace("trace {}", BigInfo.to(2));

        BigInfo bigInfo = new BigInfo();

        logger.trace("trace {}", bigInfo);
    }
}

class BigInfo {
    public static String to(Integer i) {
        System.out.println("to " + i);
        return String.valueOf(i) + " is integer";
    }

    public BigInfo() {
        System.out.println("constr");
    }

    @Override
    public String toString() {
        return "toString()";
    }
}
