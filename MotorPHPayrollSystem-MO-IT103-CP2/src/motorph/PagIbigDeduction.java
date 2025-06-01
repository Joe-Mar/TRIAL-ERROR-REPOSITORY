/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */

package motorph;

// IMPLEMENTS SalaryDeductions INTERFACE
public class PagIbigDeduction implements SalaryDeductions {
/*
 * OOP PRINCIPLE: ABSTRACTION
 * Hides the actual formula details from other classes.
 */

    @Override
    public double calculate(double salary) {
        final double minimumCompensation = 1500;
        if (salary > minimumCompensation) {
            return salary * 0.02;
        }
        return salary * 0.01;
    }
    /*
     * OOP PRINCIPLE: POLYMORPHISM
     * Implements the calculate() method defined in SalaryDeductions interface.
     */

}
