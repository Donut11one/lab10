import java.util.HashMap;
import java.util.Map;

/**
 * Represents a bank that manages a collection of bank accounts.
 */
public class Bank
{
    /** Initial value for the total balance calculation. */
    private static final double INITIAL_TOTAL_BALANCE = 0.0;

    /** Error message for duplicate account IDs. */
    private static final String ACCOUNT_EXISTS_MESSAGE = "Account ID already exists";

    /** Error message for missing account retrievals. */
    private static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account not found";

    /** Map to store bank accounts by their unique ID. */
    private final Map<String, BankAccount> accounts;

    /**
     * Creates a new bank instance.
     */
    public Bank()
    {
        accounts = new HashMap<>();
    }

    /**
     * Adds a new account to the bank.
     *
     * @param account the bank account to add
     * @throws IllegalArgumentException if the account ID already exists in the bank
     */
    public void addAccount(final BankAccount account)
    {
        if (accounts.containsKey(account.getAccountId()))
        {
            throw new IllegalArgumentException(ACCOUNT_EXISTS_MESSAGE);
        }
        accounts.put(account.getAccountId(), account);
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param accountId the unique ID of the account to retrieve
     * @return the bank account associated with the ID
     * @throws IllegalArgumentException if no account is found with the specified ID
     */
    public BankAccount retrieveAccount(final String accountId)
    {
        if (!accounts.containsKey(accountId))
        {
            throw new IllegalArgumentException(ACCOUNT_NOT_FOUND_MESSAGE);
        }
        return accounts.get(accountId);
    }

    /**
     * Calculates the total balance of all accounts in the bank.
     *
     * @return the total balance in USD
     */
    public double totalBalanceUsd()
    {
        double total = INITIAL_TOTAL_BALANCE;
        for (final BankAccount account : accounts.values())
        {
            total += account.getBalanceUsd();
        }
        return total;
    }
}
