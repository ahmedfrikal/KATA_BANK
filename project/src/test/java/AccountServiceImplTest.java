import ma.skypay.bank.account.AccountPrinter;
import ma.skypay.bank.account.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceImplTest {
    private AccountServiceImpl accountService;
    private AccountPrinter accountPrinter;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl();
        accountPrinter = Mockito.mock(AccountPrinter.class);
    }

    @Test
    void testDeposit() {
        accountService.deposit(1000);
        assertEquals(1000, accountService.getBalance(), "Le solde après dépôt doit être de 1000");
        assertEquals(1, accountService.getTransactions().size(), "Il doit y avoir une transaction après le dépôt");
        assertEquals(1000, accountService.getTransactions().get(0).getAmount(), "Le montant de la transaction doit être de 1000");
    }

    @Test
    void testWithdraw() {
        accountService.deposit(2000);
        accountService.withdraw(500);
        assertEquals(1500, accountService.getBalance(), "Le solde après retrait doit être de 1500");
        assertEquals(2, accountService.getTransactions().size(), "Il doit y avoir deux transactions après le retrait");
        assertEquals(-500, accountService.getTransactions().get(1).getAmount(), "Le montant de la transaction de retrait doit être de -500");
    }

    @Test
    void testWithdrawInsufficientFunds() {
        accountService.deposit(1000);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            accountService.withdraw(1500);
        }, "Le retrait doit échouer si le solde est insuffisant");

        assertEquals("Fonds insuffisants.", thrown.getMessage(), "Le message d'exception doit être 'Fonds insuffisants.'");
    }

    @Test
    void testDepositNegativeAmount() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            accountService.deposit(-500);
        }, "Le dépôt doit échouer si le montant est négatif");

        assertEquals("Le montant du dépôt doit être positif.", thrown.getMessage(), "Le message d'exception doit être 'Le montant du dépôt doit être positif.'");
    }

    @Test
    void testWithdrawNegativeAmount() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            accountService.withdraw(-500);
        }, "Le retrait doit échouer si le montant est négatif");

        assertEquals("Le montant du retrait doit être positif.", thrown.getMessage(), "Le message d'exception doit être 'Le montant du retrait doit être positif.'");
    }

    @Test
    void testPrintStatement() {
        accountService.deposit(1000);
        accountService.deposit(2000);
        accountService.withdraw(500);
        Mockito.doNothing().when(accountPrinter).printStatement(accountService.getTransactions());
        accountService.printStatement();
        Mockito.verify(accountPrinter).printStatement(accountService.getTransactions());
    }

}
