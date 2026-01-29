package core;

/**
 * ATMStatus - Encapsulates the complete status of the ATM machine.
 * Follows Single Responsibility Principle by managing only machine status information.
 * Can be extended later with additional status fields without modifying business logic.
 */
public class ATMStatus {
    private double cashAvailable;
    private boolean isOperational;
    private String lastStatusCheck;
    
    public ATMStatus(double cashAvailable, boolean isOperational) {
        this.cashAvailable = cashAvailable;
        this.isOperational = isOperational;
        this.lastStatusCheck = java.time.LocalDateTime.now().toString();
    }
    
    // Getters for technician status viewing (read-only)
    public double getCashAvailable() {
        return cashAvailable;
    }
    
    public boolean isOperational() {
        return isOperational;
    }
    
    public String getLastStatusCheck() {
        return lastStatusCheck;
    }
    
    // Display method for technician view
    public String getStatusReport() {
        return String.format(
            "=== ATM STATUS REPORT ===\n" +
            "Operational: %s\n" +
            "Cash Available: $%.2f\n" +
            "Last Check: %s\n" +
            "========================",
            isOperational ? "YES" : "NO",
            cashAvailable,
            lastStatusCheck
        );
    }
}
