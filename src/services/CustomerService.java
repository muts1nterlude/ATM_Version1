package services;

/**
 * CustomerService - Defines customer operations for V1.
 * Follows Interface Segregation Principle - customers get only view operations in V1.
 * In future versions, can add withdraw/deposit by implementing this interface.
 */
public interface CustomerService {
    /**
     * Authenticate customer (V1: read-only verification)
     */
    boolean authenticate(String cardNumber, String pin);
    
    /**
     * Display customer balance (read-only)
     */
    void checkBalance();
}
