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

public class PhilHealthDeductionTest {

    private PhilHealthDeduction philHealth;

    @Before
    public void setUp() {
        philHealth = new PhilHealthDeduction();
    }
    /*
     * @Before runs before each test.
     * It initializes the PhilHealthDeduction instance.
     */

    @After
    public void tearDown() {
        philHealth = null;
    }
    /*
     * @After runs after each test.
     * It clears the PhilHealthDeduction instance.
     */

    @Test
    public void testCalculateBelowMinimum() {
        double salary = 10000.00;
        double expected = 350.00; // minimum contribution
        double actual = philHealth.calculate(salary);
        assertEquals(expected, actual, 0.001);
    }
    /*
     * Test for salary resulting in contribution below the minimum (350).
     * Should return exactly 350.
     */

    @Test
    public void testCalculateNormalRange() {
        double salary = 50000.00;
        double expected = salary * 0.0275;
        double actual = philHealth.calculate(salary);
        assertEquals(expected, actual, 0.001);
    }
    /*
     * Test for salary within the valid range (between min and max cap).
     * Should return 2.75% of the salary.
     */

    @Test
    public void testCalculateAboveMaximum() {
        double salary = 100000.00;
        double expected = 1500.00; // maximum contribution
        double actual = philHealth.calculate(salary);
        assertEquals(expected, actual, 0.001);
    }
    /*
     * Test for salary resulting in contribution above the maximum (1500).
     * Should return exactly 1500.
     */
}

