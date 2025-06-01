/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */

package motorph;

// INTERFACE DEFINES A STANDARD METHOD THAT ALL DEDUCTION TYPES MUST IMPLEMENT
public interface SalaryDeductions {
    double calculate(double salary);
}
/*
 * OOP PRINCIPLE: ABSTRACTION
 * Hides the implementation details and only exposes what’s necessary.
 */
