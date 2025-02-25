package ma.skypay.bank;

import ma.skypay.bank.account.AccountServiceImpl;

public class Main {
    public static void main(String[] args) {
        AccountServiceImpl accountService = new AccountServiceImpl();

        // Effectuer des transactions
        accountService.deposit(1000);  // Dépôt de 1000
        accountService.deposit(2000);  // Dépôt de 2000
        accountService.withdraw(500);  // Retrait de 500

        // Imprimer le relevé de compte
        accountService.printStatement();
    }
}