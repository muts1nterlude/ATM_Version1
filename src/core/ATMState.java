package core;

public class ATMState {
    private double cashAvailable;
    private static final double MAXIMUM_CASH_CAPACITY = 50000.0;

    public ATMState(double cashAvailable) {
        this.cashAvailable = cashAvailable;
    }

    public double getCashAvailable() { 
        return cashAvailable; 
    }

    public boolean dispenseCash(double amount) {
        if (amount > cashAvailable) {
            return false;
        }
        cashAvailable -= amount;
        return true;
    }

    public boolean addCash(double amount) {
        if (amount < 0 || (cashAvailable + amount) > MAXIMUM_CASH_CAPACITY) {
            return false;
        }
        cashAvailable += amount;
        return true;
    }

    public boolean addCashDeposit(double amount) {
        return addCash(amount);
    }
}
