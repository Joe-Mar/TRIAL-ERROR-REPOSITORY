/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */

package motorph;

// IMPLEMENTS SalaryDeductions INTERFACE
public class PhilHealthDeduction implements SalaryDeductions {
/*
 * OOP PRINCIPLE: ABSTRACTION
 * Hides the actual formula details from other classes.
 */
    
    @Override
    public double calculate(double salary) {
        final double minimumContribution = 350.00;
        final double maximumContribution = 1500.00;
        
        // CALCULATE PHILHEALTH CONTRIBUTION (2.75% of monthly salary)
        double contribution = salary * 0.0275;
        
        // APPLY MINIMUM AND MAXIMUM LIMITS
        if (contribution < minimumContribution) {
            return minimumContribution;
        } else if (contribution > maximumContribution) {
            return maximumContribution;
        } else {
            return contribution;
        }
    }
    /*
     * OOP PRINCIPLE: POLYMORPHISM
     * Implements the calculate() method defined in SalaryDeductions interface.
     */
}

