/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */

package motorph;

import java.util.ArrayList;
import java.util.List;

public class TotalDeductions extends SalaryDeductionsModel {

    private List<SalaryDeductions> deductions;
    /* 
     * OOP PRINCIPLE: ENCAPSULATION
     * The private access modifier ensures that these attributes cannot be accessed directly from outside the class.
     */
    
    public TotalDeductions() {
        deductions = new ArrayList<>();
        deductions.add(new SSSDeduction());
        deductions.add(new WithholdingTax());
        deductions.add(new PagIbigDeduction());
        deductions.add(new PhilHealthDeduction());
    }

    @Override
    public double calculateTotalDeductions(double salary) {
        double total = 0.0;
        for (SalaryDeductions deduction : deductions) {
            if (deduction != null) {
                total += deduction.calculate(salary);
            }
        }
        return total;
    }
    /*
     * OOP PRINCIPLE: POLYMORPHISM
     * Implements the calculate() method defined in SalaryDeductions interface.
     */
}
