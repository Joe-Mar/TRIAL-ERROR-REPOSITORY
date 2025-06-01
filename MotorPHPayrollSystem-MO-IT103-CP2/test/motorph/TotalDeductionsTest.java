/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */

package motorph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TotalDeductionsTest {

    private TotalDeductions totalDeductions;

    @Before
    public void setUp() {
        totalDeductions = new TotalDeductions();
    }
    /*
     * @Before runs before each test.
     * It initializes the TotalDeductions instance.
     */

    @After
    public void tearDown() {
        totalDeductions = null;
    }
    /*
     * @After runs after each test.
     * It clears the WithholdingTax instance.
     */

    @Test
    public void testCalculateTotalDeductions() {
        double salary = 30000.00;

        // EXPECTED DEDUCTION COMPUTATIONS (same formulas as in the original classes):
        double sss = 25000 * 0.045; // capped
        double pagibig = salary * 0.02;
        double philhealth = salary * 0.0275;
        if (philhealth < 350) philhealth = 350;
        else if (philhealth > 1500) philhealth = 1500;
        double tax = (salary - 20833) * 0.15;; // from WithholdingTax bracket 3

        double expectedTotal = sss + pagibig + philhealth + tax;
        double actualTotal = totalDeductions.calculateTotalDeductions(salary);

        assertEquals(expectedTotal, actualTotal, 0.01);
    }
    /*
     * Test for total deduction on a known salary.
     * Verifies if the total is equal to the sum of all individual deductions.
     */
}


