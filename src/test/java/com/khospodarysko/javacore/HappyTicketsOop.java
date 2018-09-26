package com.khospodarysko.javacore;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HappyTicketsOop {
    public static void main(String[] args) {
        System.setProperty("generator", "apache");
        RandomGenerator randomGenerator = RandomHelperFactory.get();

        List<MyNumber> all = new ArrayList<>();

        long timeout = System.currentTimeMillis() + 2000;
        while (System.currentTimeMillis() < timeout) {
            all.add(new MyNumber(randomGenerator));
        }

        List<MyNumber> happy = all.stream().filter(MyNumber::isHappy).collect(Collectors.toList());

        System.out.println(happy.stream().filter(n -> n.startsWith("0")).collect(Collectors.toList()));
    }
}

class MyNumber {
    private String number;

    public MyNumber(RandomGenerator generator) {
        number = generator.generate();
    }

    public boolean isHappy() {
        List<String> digits = Arrays.asList(number.split(""));
        int full = digits.size();
        int half = digits.size() / 2;

        int leftSum = digits.subList(0, half).stream().mapToInt(Integer::parseInt).sum();
        int rightSum = digits.subList(half, full).stream().mapToInt(Integer::parseInt).sum();

        return leftSum == rightSum;
    }

    public boolean startsWith(String s) {
        return number.startsWith(s);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}

class RandomHelperFactory {
    public static RandomGenerator get() {
        String generator = System.getProperty("generator");

        switch (generator) {
            case "java":
                return new JavaRandomGenerator();
            case "apache":
                return new ApacheRandomGenerator();
            default:
                throw new RuntimeException("Generator " + generator + " not supported");
        }
    }
}

interface RandomGenerator {
    String generate();
}

class JavaRandomGenerator implements RandomGenerator {
    @Override
    public String generate() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100_000, 999_999));
    }
}

class ApacheRandomGenerator implements RandomGenerator {
    @Override
    public String generate() {
        return RandomStringUtils.randomNumeric(6);
    }
}
