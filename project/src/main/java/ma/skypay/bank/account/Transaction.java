package ma.skypay.bank.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class Transaction {
    private final LocalDate date;
    private final int amount;
    private final int balance;
}
