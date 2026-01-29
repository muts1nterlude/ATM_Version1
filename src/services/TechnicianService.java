package services;

import core.ATMStatus;

/**
 * TechnicianService - Defines technician operations for V1.
 * Follows Interface Segregation Principle - technician only gets what they need.
 * Read-only operations only - no modifications allowed.
 * Can be extended later with more technician features without affecting customer operations.
 */
public interface TechnicianService {
    /**
     * Get current machine status (read-only)
     */
    ATMStatus getMachineStatus();
    
    /**
     * Display status report for technician viewing
     */
    void viewMachineStatus();
}
