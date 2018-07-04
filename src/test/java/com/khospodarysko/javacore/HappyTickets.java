package com.khospodarysko.javacore;

import java.util.ArrayList;
import java.util.List;

public class HappyTickets {
}

// ---------------------------------------------------------------------------------------------------------------------
// How many tickets are generated in N seconds -------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------

class Scratch {
    public static void main(String[] args) {
        int seconds = 2;

        TicketsInSeconds staticFilter = new StaticFilter(seconds);
        TicketsInSeconds dynamicFilter = new DynamicFilter(seconds);

        List<Integer> tickets = staticFilter.happyTickets();
    }
}

abstract class TicketsInSeconds {
    protected int seconds;
    protected List<Integer> happyTickets = new ArrayList<>();

    public TicketsInSeconds(int seconds) {
        this.seconds = seconds;
    }

    public List<Integer> happyTickets() {
        filterHappyTickets();
        return happyTickets;
    }

    public boolean isHappy(Integer number) {
        return false;
    }

    public abstract void filterHappyTickets();
}

class StaticFilter extends TicketsInSeconds {
    public StaticFilter(int seconds) {
        super(seconds);
    }

    @Override
    public void filterHappyTickets() {
        for (Integer i : generateList()) {
            if (isHappy(i)) {
                happyTickets.add(i);
            }
        }
    }

    private Integer generateNumber() {
        // TODO generate random number
        return 22;
    }

    private List<Integer> generateList() {
        List<Integer> numbers = new ArrayList<>();

        int timeout = seconds * 1_000_000_000;

        long start = System.nanoTime();
        while ((System.nanoTime() - start) < timeout) {
            numbers.add(generateNumber());
        }

        return numbers;
    }
}

class DynamicFilter extends TicketsInSeconds {
    public DynamicFilter(int seconds) {
        super(seconds);
    }

    @Override
    public void filterHappyTickets() {
        int timeout = seconds * 1_000_000_000;

        long start = System.nanoTime();
        while ((System.nanoTime() - start) < timeout) {
            int number = generateNumber();
            if (isHappy(number)) {
                happyTickets.add(number);
            }
        }
    }

    private Integer generateNumber() {
        // TODO generate random number
        return 22;
    }
}

// ---------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
