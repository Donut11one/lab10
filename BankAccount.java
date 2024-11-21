/**
 * Represents a bank account with functionalities for deposits, withdrawals, and balance management.
 */
public class BankAccount
{
    /** Minimum allowed balance for the account. */
    private static final double MIN_BALANCE = 0.0;

    /** Minimum transaction amount allowed (e.g., for deposits or withdrawals). */
    private static final double MIN_TRANSACTION_AMOUNT = 0.01;

    /** Error message for insufficient funds. */
    private static final String INSUFFICIENT_FUNDS_MESSAGE = "Insufficient funds";

    /** Error message for invalid transaction amounts. */
    private static final String POSITIVE_AMOUNT_MESSAGE = "Transaction amount must be positive";

    /** Error message for account ID mismatches during transfers. */
    private static final String TARGET_ACCOUNT_ID_MISMATCH_MESSAGE = "Target account ID mismatch";

    /** Unique identifier for the bank account. */
    private final String accountId;

    /** Current balance in USD for the account. */
    private double balanceUsd;

    /**
     * Creates a new bank account with a specified ID and initial balance.
     *
     * @param accountId     the unique identifier for the account
     * @param initialBalance the initial balance for the account
     * @throws IllegalArgumentException if the initial balance is negative
     */
    public BankAccount(final String accountId, final double initialBalance)
    {
        if (initialBalance < MIN_BALANCE)
        {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.accountId = accountId;
        this.balanceUsd = initialBalance;
    }

    /**
     * Gets the unique identifier for this account.
     *
     * @return the account ID
     */
    public String getAccountId()
    {
        return accountId;
    }

    /**
     * Gets the current balance of this account in USD.
     *
     * @return the current balance
     */
    public double getBalanceUsd()
    {
        return balanceUsd;
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount the amount to deposit
     * @throws IllegalArgumentException if the deposit amount is less than the minimum transaction amount
     */
    public void deposit(final double amount)
    {
        if (amount < MIN_TRANSACTION_AMOUNT)
        {
            throw new IllegalArgumentException(POSITIVE_AMOUNT_MESSAGE);
        }
        balanceUsd += amount;
    }

    /**
     * Withdraws a specified amount from the account.
     *
     * @param amount the amount to withdraw
     * @throws IllegalArgumentException if the withdrawal amount is less than the minimum transaction amount
     *                                  or exceeds the available balance
     */
    public void withdraw(final double amount)
    {
        if (amount < MIN_TRANSACTION_AMOUNT)
        {
            throw new IllegalArgumentException(POSITIVE_AMOUNT_MESSAGE);
        }
        if (amount > balanceUsd)
        {
            throw new IllegalArgumentException(INSUFFICIENT_FUNDS_MESSAGE);
        }
        balanceUsd -= amount;
    }

    /**
     * Transfers a specified amount from this account to another account.
     *
     * @param targetAccount  the target bank account
     * @param targetAccountId the ID of the target account
     * @param amount         the amount to transfer
     * @throws IllegalArgumentException if the target account ID does not match or if the transfer fails
     */
    public void transferToBank(final BankAccount targetAccount, final String targetAccountId, final double amount)
    {
        if (!targetAccountId.equals(targetAccount.getAccountId()))
        {
            throw new IllegalArgumentException(TARGET_ACCOUNT_ID_MISMATCH_MESSAGE);
        }
        this.withdraw(amount);
        targetAccount.deposit(amount);
    }
}
