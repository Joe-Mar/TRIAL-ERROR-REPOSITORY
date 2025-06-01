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

public class SalaryOnHoursWorkedTest {

    private SalaryOnHoursWorked salaryCalc;

    @Before
    public void setUp() {
        salaryCalc = new SalaryOnHoursWorked();
    }
    /*
     * @Before runs before each test.
     * It initializes the SalaryOnHoursWorked instance.
     */

    @After
    public void tearDown() {
        salaryCalc = null;
    }
    /*
     * @After runs after each test.
     * It clears the SalaryOnHoursWorked instance.
     */

    @Test
    public void testGetSalaryOnHoursWorked() {
        double hourlyRate = 200.00;
        double expected = hourlyRate * 160;
        double actual = salaryCalc.getSalaryOnHoursWorked(hourlyRate);
        assertEquals(expected, actual, 0.001);
    }
    /*
     * Test for hourly rate multiplied by 160 hours.
     * Expected result: hourlyRate × 160
     */
    
}

