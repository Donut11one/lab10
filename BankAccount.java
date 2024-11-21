

public class BankAccount {
    private static final double MIN_BALANCE = 0.0;
    private static final double MIN_TRANSACTION_AMOUNT = 0.01;
    private static final String INSUFFICIENT_FUNDS_MESSAGE = "Insufficient funds";
    private static final String POSITIVE_AMOUNT_MESSAGE = "Transaction amount must be positive";
    private static final String TARGET_ACCOUNT_ID_MISMATCH_MESSAGE = "Target account ID mismatch";

    private final String accountId;
    private double balanceUsd;

    public BankAccount(final String accountId, final double initialBalance) 
    {
        if (initialBalance < MIN_BALANCE) 
        {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.accountId = accountId;
        this.balanceUsd = initialBalance;
    }

    public String getAccountId() 
    {
        return accountId;
    }

    public double getBalanceUsd() 
    {
        return balanceUsd;
    }

    public void deposit(final double amount) 
    {
        if (amount < MIN_TRANSACTION_AMOUNT) 
        {
            throw new IllegalArgumentException(POSITIVE_AMOUNT_MESSAGE);
        }
        balanceUsd += amount;
    }

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
