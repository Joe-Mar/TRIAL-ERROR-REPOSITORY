/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */

package motorph;

// IMPLEMENTS SalaryDeductions INTERFACE
public class WithholdingTax implements SalaryDeductions {
/*
 * OOP PRINCIPLE: ABSTRACTION
 * Hides the actual formula details from other classes.
 */
    
   @Override
    public double calculate(double salary) {
        // Implement tax calculation logic based on salary brackets
        if (salary <= 20833) {
            return 0; // No tax for income up to 250,000 annually
        } else if (salary <= 33332) {
            return (salary - 20833) * 0.15; // 15% of excess over 250,000
        } else if (salary <= 66666) {
            return 1875 + (salary - 33332) * 0.20; // 22,500 + 20% of excess over 400,000
        } else if (salary <= 166666) {
            return 8541.80 + (salary - 66666) * 0.25; // 102,500 + 25% of excess over 800,000
        } else if (salary <= 666666) {
            return 33541.80 + (salary - 166666) * 0.30; // 402,500 + 30% of excess over 2M
        } else {
            return 183541.80 + (salary - 666666) * 0.35; // 2.2M + 35% of excess over 8M
        }
    }
    /*
     * OOP PRINCIPLE: POLYMORPHISM
     * Implements the calculate() method defined in SalaryDeductions interface.
     */
}

