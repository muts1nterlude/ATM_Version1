package core;

public class Account {
    private String cardNumber;
    private String pin;
    private double balance;
    // REMOVED: cardExpirationDate (V2 feature)
    // REMOVED: dailyWithdrawalLimit (V2 feature)
    // REMOVED: dailyWithdrawalUsed (V2 feature)
    // REMOVED: lastWithdrawalDate (V2 feature)
    // REMOVED: transactionHistory (V2 feature)
    // REMOVED: WITHDRAWAL_FEE (V2 feature)
    // REMOVED: TRANSFER_FEE (V2 feature)
    // REMOVED: DEFAULT_DAILY_LIMIT (V2 feature)

    public Account(String cardNumber, String pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        // Removed initialization of V2 features
    }

    public String getCardNumber() { return cardNumber; }
    public String getPin() { return pin; }
    public double getBalance() { return balance; }
    
    // REMOVED: getCardExpirationDate() - V2 feature
    // REMOVED: getTransactionHistory() - V2 feature
    // REMOVED: getDailyWithdrawalUsed() - V2 feature
    // REMOVED: getDailyWithdrawalLimit() - V2 feature
    // REMOVED: isCardExpired() - V2 feature

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        // REMOVED: Transaction logging (V2 feature)
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        // REMOVED: Daily limit checking (V2 feature)
        // REMOVED: Withdrawal fee logic (V2 feature)

        if (amount > balance) {
            return false;
        }

        balance -= amount;
        // REMOVED: Transaction logging (V2 feature)
        return true;
    }

    // REMOVED: transfer() - Transfer feature (V2 extended feature)
    // REMOVED: receiveTransfer() - Transfer feature (V2 extended feature)
    // REMOVED: addTransaction() - Transaction history (V2 feature)
    // REMOVED: printTransactionHistory() - Transaction history (V2 feature)
    // REMOVED: Transaction inner class - Transaction history (V2 feature)
}
