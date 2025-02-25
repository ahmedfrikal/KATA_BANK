package ma.skypay.bank.account;

import java.util.List;

public class AccountPrinter {
    public static void printStatement(List<Transaction> transactions) {
        transactions.stream()
                .sorted((t1, t2) -> t2.getDate().compareTo(t1.getDate()))
                .forEach(transaction ->
                        System.out.println("date => " + transaction.getDate() +
                                " => amount " + (transaction.getAmount() < 0 ? "" : "+") + transaction.getAmount() +
                                " => balance " + transaction.getBalance()));
    }
}
