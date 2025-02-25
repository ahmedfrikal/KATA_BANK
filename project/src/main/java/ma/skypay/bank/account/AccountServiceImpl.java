package ma.skypay.bank.account;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
@Getter
public class AccountServiceImpl implements AccountService {
    private int balance = 0;
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être positif.");
        }
        balance += amount;
        transactions.add(new Transaction(java.time.LocalDate.now(), amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être positif.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Fonds insuffisants.");
        }
        balance -= amount;
        transactions.add(new Transaction(java.time.LocalDate.now(), -amount, balance));
    }

    @Override
    public void printStatement() {
        AccountPrinter.printStatement(transactions);
    }
}
