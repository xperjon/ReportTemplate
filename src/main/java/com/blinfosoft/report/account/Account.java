package com.blinfosoft.report.account;

import java.util.Optional;

/**
 *
 * @author jel
 */
public class Account {

    private Double balance;
    private final Integer number;

    public Account(Integer number) {
        this.number = number;
    }

    public Account(Integer number, Double balance) {
        this.number = number;
        this.balance = balance;
    }

    public Optional<Double> getBalance() {
        return shouldSignReverse() ? Optional.ofNullable(-balance) : Optional.ofNullable(balance);
    }

    private boolean shouldSignReverse() {
        return number >= 3000;
    }

}
