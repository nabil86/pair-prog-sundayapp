package org.sundayapp.domain;

import domain.Account;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

public class DefaultPrinter implements PrinterSlipAccount {
    private Clock clock;

    public DefaultPrinter(Clock clock) {
        this.clock = clock;
    }

    @Override
    public String print(Account account) {
        return String.format("%s, %s, %s", LocalDate.now(clock), LocalTime.now(clock), account.getBalance().toString());
    }
}
