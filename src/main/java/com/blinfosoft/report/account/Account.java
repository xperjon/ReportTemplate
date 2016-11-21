package com.blinfosoft.report.account;

import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.number);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account{" + "number=" + number + '}';
    }
}
