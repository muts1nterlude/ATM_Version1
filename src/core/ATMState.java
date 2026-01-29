package core;

public class ATMState {
    private double cashAvailable;
    // REMOVED: firmwareVersion (V2 feature)
    // REMOVED: paperTank (V2 feature)
    // REMOVED: isOperational (V2 feature)
    // REMOVED: lastMaintenanceDate (V2 feature)
    // REMOVED: lastCashRefillDate (V2 feature)
    // REMOVED: totalTransactionsProcessed (V2 feature)
    // REMOVED: totalAmountDispensed (V2 feature)
    // REMOVED: eventLog (V2 feature)
    private static final double MAXIMUM_CASH_CAPACITY = 50000.0;

    public ATMState(double cashAvailable) {
        this.cashAvailable = cashAvailable;
        // Removed V2 initializations (firmware, paperTank, operational flag, etc.)
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

    // REMOVED: performMaintenance() - Technician feature (V2)
    // REMOVED: isLowOnCash() - Technician feature (V2)
    // REMOVED: isNearCapacity() - Technician feature (V2)
    // REMOVED: getPaperTank() - Technician feature (V2)
    // REMOVED: getFirmwareVersion() - Technician feature (V2)
    // REMOVED: updateFirmware() - Technician feature (V2)
    // REMOVED: isOperational() - Technician feature (V2)
    // REMOVED: setOperational() - Technician feature (V2)
    // REMOVED: logEvent() - Event logging (V2)
    // REMOVED: getEventLog() - Event logging (V2)
    // REMOVED: ATMEvent inner class - Event logging (V2)
    // REMOVED: getLastMaintenanceDate() - Technician feature (V2)
    // REMOVED: getLastCashRefillDate() - Technician feature (V2)
    // REMOVED: getTotalTransactionsProcessed() - Technician feature (V2)
    // REMOVED: getTotalAmountDispensed() - Technician feature (V2)
    // REMOVED: displayDiagnostics() - Technician feature (V2)
    // REMOVED: getCashPercentage() - Technician feature (V2)
    // REMOVED: displayEventLog() - Technician feature (V2)
}
