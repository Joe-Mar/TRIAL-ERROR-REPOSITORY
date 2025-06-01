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

public class SSSDeductionTest {

    private SSSDeduction sss;

    @Before
    public void setUp() {
        sss = new SSSDeduction();
    }
    /*
     * @Before runs before each test.
     * It initializes the SSSDeduction instance.
     */

    @After
    public void tearDown() {
        sss = null;
    }
    /*
     * @After runs after each test.
     * It clears the SSSDeduction instance.
     */

    @Test
    public void testCalculateBelowThreshold() {
        double salary = 20000.00;
        double expected = salary * 0.045; // 4.5% of salary
        double actual = sss.calculate(salary);
        assertEquals(expected, actual, 0.001);
    }
    /*
     * Test for salary BELOW the threshold (25,000).
     * Should return 4.5% of the given salary.
     */

    @Test
    public void testCalculateAboveThreshold() {
        double salary = 30000.00;
        double expected = 25000 * 0.045; // capped at 25,000
        double actual = sss.calculate(salary);
        assertEquals(expected, actual, 0.001);
    }
    /*
     * Test for salary ABOVE the threshold (25,000).
     * Should return 4.5% of the fixed max threshold (25,000).
     */
}
