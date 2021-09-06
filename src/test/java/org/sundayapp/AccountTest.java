package org.sundayapp;

import domain.Account;
import domain.Money;
import org.junit.jupiter.api.Test;
import org.sundayapp.domain.DefaultPrinter;
import org.sundayapp.domain.PrinterSlipAccount;
import org.sundayapp.exception.InsufficientFundsException;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.lang.String.format;
import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class AccountTest {

    @Test
    void should_deposit_money() {
        var account = new Account();
        var money = Money.of(1000L);

        account.deposit(money);

        assertThat(account.getBalance()).isEqualTo(money);
    }

    @Test
    void should_deposit_money_two_times() {
        var account = new Account();
        var money = Money.of(1000L);
        var moneySecondTime = Money.of(2000L);

        account.deposit(money);
        account.deposit(moneySecondTime);

        assertThat(account.getBalance()).isEqualTo(Money.of(3000L));
    }

    @Test
    void should_withdraw_money() {
        var account = new Account();
        var money = Money.of(500L);

        account.withdraw(money);

        assertThat(account.getBalance()).isEqualTo(Money.of(-500L));
    }

    @Test
    void should_transfer_amount_to_another_account() {
        var account = new Account();
        var mountToTransfer = Money.of(500L);
        account.deposit(Money.of(500L));

        var distinationAccount = new Account();
        account.transfer(mountToTransfer, distinationAccount);

        assertThat(account.getBalance()).isEqualTo(Money.Zero());
        assertThat(distinationAccount.getBalance()).isEqualTo(mountToTransfer);
    }

    @Test
    void should_throw_exception_if_not_sufficient_amount() {
        var account = new Account();
        var mountToTransfer = Money.of(500L);

        var distinationAccount = new Account();

        assertThatCode(() -> account.transfer(mountToTransfer, distinationAccount))
                .isInstanceOf(InsufficientFundsException.class);
    }

    @Test
    void should_print_balance_slip() {
        var account = new Account();
        var clock = Clock.fixed(Instant.now(), UTC);
        LocalDate date = LocalDate.now(clock);
        LocalTime time = LocalTime.now(clock);
        var result = format("%s, %s, %s", date, time, account.getBalance().toString());

        PrinterSlipAccount printSlipAccount = new DefaultPrinter(clock);
        assertThat(account.printOutSlip(printSlipAccount)).isEqualTo(result);
    }
}
