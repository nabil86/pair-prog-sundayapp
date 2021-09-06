package domain;

import org.sundayapp.domain.PrinterSlipAccount;
import org.sundayapp.exception.InsufficientFundsException;

import static domain.Money.Zero;

public class Account {

    private Money balance = Zero();

    public void deposit(Money money) {
        balance = balance.add(money);
    }

    public Money getBalance() {
        return balance;
    }

    public void withdraw(Money money) {
        balance = balance.substract(money);
    }

    public void transfer(Money mountToTransfer, Account distinationAccount) {
        assertSufficientAmount(mountToTransfer);
        withdraw(mountToTransfer);
        distinationAccount.deposit(mountToTransfer);
    }

    private void assertSufficientAmount(Money mountToTransfer) {
        Money amount = this.balance.substract(mountToTransfer);
        if (!amount.isGreaterOrEqualToZero()) {
            throw new InsufficientFundsException();
        }
    }

    public String printOutSlip(PrinterSlipAccount printer) {
        return printer.print(this);
    }
}
