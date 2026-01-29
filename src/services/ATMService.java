package services;

import core.*;
import interfaces.Persistence;

/**
 * ATMService - Implements both CustomerService and TechnicianService.
 * Follows SOLID principles:
 * - S: Single Responsibility - manages ATM operations
 * - O: Open/Closed - easy to extend with new features
 * - L: Liskov Substitution - implements both service interfaces
 * - I: Interface Segregation - customers and technicians get only what they need
 * - D: Dependency Inversion - depends on Persistence interface, not concrete implementations
 */
public class ATMService implements CustomerService, TechnicianService {
    private Persistence persistence;
    private Account account;
    private ATMState atmState;

    public ATMService(Persistence persistence) {
        this.persistence = persistence;
        this.atmState = persistence.loadATMState();
    }

    public boolean authenticate(String card, String pin) {
        // REMOVED: Card expiration check (V2 feature)
        account = persistence.loadAccount(card);

        if (!account.getPin().equals(pin)) {
            System.out.println("Invalid PIN. Please try again.");
            return false;
        }

        System.out.println("Authentication successful! Welcome, " + card);
        return true;
    }

    public void checkBalance() {
        System.out.println("\n========== ACCOUNT INFORMATION ==========");
        System.out.println("Card Number: " + maskCardNumber(account.getCardNumber()));
        System.out.println("Current Balance: $" + String.format("%.2f", account.getBalance()));
        // REMOVED: Daily withdrawal limit display (V2 feature)
        // REMOVED: Card expiration display (V2 feature)
        System.out.println("=========================================\n");
    }

    public boolean withdraw(double amount) {
        try {
            if (amount <= 0) {
                System.out.println("ERROR: Withdrawal amount must be positive.");
                return false;
            }

            if (!atmState.dispenseCash(amount)) {
                System.out.println("ERROR: ATM has insufficient cash. Available: $" + 
                    String.format("%.2f", atmState.getCashAvailable()));
                atmState.addCash(amount); // Revert
                return false;
            }

            if (!account.withdraw(amount)) {
                atmState.addCash(amount); // Revert
                System.out.println("ERROR: Insufficient balance.");
                return false;
            }

            printReceipt("WITHDRAWAL", amount);
            persistence.saveAccount(account);
            persistence.saveATMState(atmState);
            System.out.println("SUCCESS: $" + String.format("%.2f", amount) + " withdrawn successfully.");
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: Withdrawal failed - " + e.getMessage());
            return false;
        }
    }

    public boolean deposit(double amount) {
        try {
            if (amount <= 0) {
                System.out.println("ERROR: Deposit amount must be positive.");
                return false;
            }

            if (!atmState.addCashDeposit(amount)) {
                System.out.println("ERROR: Cannot accept deposit at this time.");
                return false;
            }

            account.deposit(amount);
            printReceipt("DEPOSIT", amount);
            persistence.saveAccount(account);
            persistence.saveATMState(atmState);
            System.out.println("SUCCESS: $" + String.format("%.2f", amount) + " deposited successfully.");
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: Deposit failed - " + e.getMessage());
            return false;
        }
    }

    // REMOVED: transfer() - Transfer feature (V2 extended feature)
    // REMOVED: viewTransactionHistory() - Transaction history (V2 feature)
    // REMOVED: changePin() - PIN change feature (V2 feature)

    private void printReceipt(String transactionType, double amount) {
        System.out.println("\n========== RECEIPT ==========");
        System.out.println("Transaction: " + transactionType);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("New Balance: $" + String.format("%.2f", account.getBalance()));
        System.out.println("Time: " + java.time.LocalDateTime.now());
        System.out.println("==============================\n");
        // REMOVED: Paper tank tracking (V2 feature)
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() < 4) return "****";
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }

    // ============ TechnicianService Implementation ============
    // V1: Technician can only VIEW machine status (read-only)

    @Override
    public ATMStatus getMachineStatus() {
        return new ATMStatus(atmState.getCashAvailable(), true);
    }

    @Override
    public void viewMachineStatus() {
        ATMStatus status = getMachineStatus();
        System.out.println("\n" + status.getStatusReport() + "\n");
    }
}
