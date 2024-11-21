import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankApplicationTests
{
    private Bank bank1;
    private Bank bank2;
    private BankAccount account1;
    private BankAccount account2;

    @BeforeEach
    void setUp()
    {
        bank1 = new Bank();
        bank2 = new Bank();
        account1 = new BankAccount("12345", 1000);
        account2 = new BankAccount("67890", 500);
        bank1.addAccount(account1);
        bank2.addAccount(account2);
    }

    @Test
    void depositIncreasesBalanceAndVerify()
    {
        account1.deposit(200);
        assertEquals(1200, account1.getBalanceUsd());
        account2.deposit(300);
        assertEquals(800, account2.getBalanceUsd());
    }

    @Test
    void withdrawDecreasesBalanceAndVerify()
    {
        account1.withdraw(200);
        assertEquals(800, account1.getBalanceUsd());
        account2.withdraw(100);
        assertEquals(400, account2.getBalanceUsd());
    }

    @Test
    void cannotWithdrawMoreThanBalanceAndHandleException()
    {
        IllegalArgumentException exception1 =
            assertThrows(IllegalArgumentException.class, () -> account1.withdraw(1200));
        assertEquals("Insufficient funds", exception1.getMessage());

        IllegalArgumentException exception2 =
            assertThrows(IllegalArgumentException.class, () -> account2.withdraw(600));
        assertEquals("Insufficient funds", exception2.getMessage());
    }

    @Test
    void addingAndRetrievingAccountFromBank()
    {
        BankAccount newAccount = new BankAccount("54321", 100);
        bank2.addAccount(newAccount);
        assertEquals(newAccount, bank2.retrieveAccount("54321"));

        BankAccount newAccount2 = new BankAccount("11111", 300);
        bank1.addAccount(newAccount2);
        assertEquals(newAccount2, bank1.retrieveAccount("11111"));
    }

    @Test
    void transferBetweenBankAccountsAndVerifyBalances()
    {
        account1.transferToBank(account2, "67890", 200);
        assertEquals(800, account1.getBalanceUsd());
        assertEquals(700, account2.getBalanceUsd());

        account2.transferToBank(account1, "12345", 100);
        assertEquals(900, account1.getBalanceUsd());
        assertEquals(600, account2.getBalanceUsd());
    }

    @Test
    void totalBalanceCalculationForBanks()
    {
        assertEquals(1000, bank1.totalBalanceUsd());
        assertEquals(500, bank2.totalBalanceUsd());
        bank1.addAccount(new BankAccount("33333", 200));
        assertEquals(1200, bank1.totalBalanceUsd());
    }

    @Test
    void handlingInvalidAccountRetrieval()
    {
        IllegalArgumentException exception1 =
            assertThrows(IllegalArgumentException.class, () -> bank1.retrieveAccount("99999"));
        assertEquals("Account not found", exception1.getMessage());

        IllegalArgumentException exception2 =
            assertThrows(IllegalArgumentException.class, () -> bank2.retrieveAccount("00000"));
        assertEquals("Account not found", exception2.getMessage());
    }

    //my own tests
    @Test
    void checkInitialBalanceCorrectness()
    {
        // Checking initial balance for account1 and account2 with more random numbers
        assertEquals(1000, account1.getBalanceUsd(), 0.01); // Randomized balance for account1
        assertEquals(500, account2.getBalanceUsd(), 0.01); // Randomized balance for account2
    }

    @Test
    void handleInvalidDepositAmount()
    {
        // Depositing an invalid (negative) amount should throw an exception
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> account1.deposit(-358.67)); // Random negative deposit amount
        assertEquals("Transaction amount must be positive", exception.getMessage());
    }

    @Test
    void handleInvalidWithdrawalAmount()
    {
        // Withdrawing an invalid (negative) amount should throw an exception
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> account2.withdraw(-72.40)); // Random negative withdrawal amount
        assertEquals("Transaction amount must be positive", exception.getMessage());
    }

    @Test
    void handleTransferToSameAccount()
    {
        // Trying to transfer to the same account should throw an exception
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> account1.transferToBank(account1, "2673", 478.30)); // Random transfer amount
        assertEquals("Target account ID mismatch", exception.getMessage());
    }

    @Test
    void summingBalancesFromMultipleAccountsInBank()
    {
        // Total balance across all accounts in bank1 with new random balances
        assertEquals(1000, bank1.totalBalanceUsd(), 0.01);  // Bank 1 total balance with random number

        // Total balance across all accounts in bank2 with new random balances
        assertEquals(500, bank2.totalBalanceUsd(), 0.01);   // Bank 2 total balance with random number

        // Add a new account to bank2 with random balance and recalculate total balance
        bank2.addAccount(new BankAccount("98765", 503.21)); // New account with a random balance
        assertEquals(1003.21, bank2.totalBalanceUsd(), 0.01); // New total balance after adding new account
    }

}
