/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */

package motorph;

// IMPLEMENTS SalaryDeductions INTERFACE
public class SSSDeduction implements SalaryDeductions {
/*
 * OOP PRINCIPLE: ABSTRACTION
 * Hides the actual formula details from other classes.
 */
    
    @Override
    public double calculate(double salary) {
        final int threshold = 25000;
        if (Math.round(salary) < threshold) {
            return salary * 0.045;
        }
        return threshold * 0.045;
    }
    /*
     * OOP PRINCIPLE: POLYMORPHISM
     * Implements the calculate() method defined in SalaryDeductions interface.
     */
}
