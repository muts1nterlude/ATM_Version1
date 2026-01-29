import services.*;
import persistence.JsonHandler;

/**
 * Main - V1 ATM System Entry Point (View-Only Mode)
 * Demonstrates SOLID principles:
 * - Depends on interfaces (CustomerService, TechnicianService) not concrete implementations
 * - Easy to extend for future versions by adding new service implementations
 * - Clear separation between customer and technician operations
 */
public class Main {
    private static ATMService atm;

    public static void main(String[] args) {
        try {
            // Initialize using Dependency Injection (SOLID: Dependency Inversion)
            JsonHandler persistence = new JsonHandler();
            atm = new ATMService(persistence);

            displayWelcomeScreen();
            
            // V1: CUSTOMER VIEW - Read-only balance display
            demonstrateCustomerView();
            
            // V1: TECHNICIAN VIEW - Read-only machine status
            demonstrateTechnicianView();
            
            System.out.println("\n========== SESSION ENDED ==========");
            System.out.println("Thank you for viewing the ATM System V1 (View-Only Mode).");
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void displayWelcomeScreen() {
        System.out.println("\n");
        System.out.println("====================================");
        System.out.println("    ATM SYSTEM V1 - VIEW ONLY       ");
        System.out.println("     SOLID Principles Demo         ");
        System.out.println("====================================");
        System.out.println("\nThis is a read-only demonstration.");
        System.out.println("No user input or modifications allowed.\n");
    }

    /**
     * Customer operations - Read-only balance viewing only
     * Uses CustomerService interface (SOLID: Interface Segregation)
     */
    private static void demonstrateCustomerView() {
        System.out.println("\n========== CUSTOMER SERVICE (READ-ONLY) ==========");
        System.out.println("Auto-authenticating customer with demo credentials...\n");
        
        // Auto-authenticate with demo account
        if (atm.authenticate("1234567890", "1234")) {
            System.out.println("✓ Authentication successful!\n");
            
            // Cast to CustomerService interface (SOLID: Dependency Inversion)
            CustomerService customerService = (CustomerService) atm;
            customerService.checkBalance();
            
            System.out.println("Note: In V1, customers can only VIEW their balance.");
            System.out.println("No transactions (withdraw/deposit) are available.");
        } else {
            System.out.println("✗ Authentication failed.\n");
        }
    }

    /**
     * Technician operations - Machine status viewing only
     * Uses TechnicianService interface (SOLID: Interface Segregation)
     */
    private static void demonstrateTechnicianView() {
        System.out.println("\n========== TECHNICIAN SERVICE (READ-ONLY) ==========");
        System.out.println("Technician accessing machine status...\n");
        
        // Cast to TechnicianService interface (SOLID: Dependency Inversion)
        TechnicianService technicianService = (TechnicianService) atm;
        technicianService.viewMachineStatus();
        
        System.out.println("Note: In V1, technicians can only VIEW machine status.");
        System.out.println("No maintenance operations are allowed.");
    }



    // REMOVED: Interactive input methods (view-only system)
    // REMOVED: technicianLogin() - V2 feature
    // REMOVED: showTechnicianMenu() - V2 feature
    // REMOVED: handleTransfer() - V2 feature
    // REMOVED: handlePinChange() - V2 feature
}
