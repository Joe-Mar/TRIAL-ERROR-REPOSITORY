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

public class PagIbigDeductionTest {

    private PagIbigDeduction pagIbig;

    @Before
    public void setUp() {
        pagIbig = new PagIbigDeduction();
    }
    /*
     * @Before method runs before each test.
     * It initializes a new instance of PagIbigDeduction.
     */

    @After
    public void tearDown() {
        pagIbig = null;
    }
    /*
     * @After method runs after each test.
     * It clears the instance of PagIbigDeduction.
     */

    @Test
    public void testCalculateBelowThreshold() {
        double salary = 1400.00;
        double expected = salary * 0.01; // 1% deduction
        double actual = pagIbig.calculate(salary);
        assertEquals(expected, actual, 0.001); // Allow small margin of error
    }
    /*
     * Test for salaries BELOW the threshold (1500).
     * Expected deduction: 1% of salary
     */

    @Test
    public void testCalculateAboveThreshold() {
        double salary = 2000.00;
        double expected = salary * 0.02; // 2% deduction
        double actual = pagIbig.calculate(salary);
        assertEquals(expected, actual, 0.001); // Allow small margin of error
    }
    /*
     * Test for salaries ABOVE the threshold (1500).
     * Expected deduction: 2% of salary
     */
}


