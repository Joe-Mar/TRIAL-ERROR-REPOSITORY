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

public class WithholdingTaxTest {

    private WithholdingTax tax;

    @Before
    public void setUp() {
        tax = new WithholdingTax();
    }
    /*
     * @Before runs before each test.
     * It initializes the WithholdingTax instance.
     */
    
    @After
    public void tearDown() {
        tax = null;
    }
    /*
     * @After runs after each test.
     * It clears the WithholdingTax instance.
     */

    /**
     * Test for salary in the 1st bracket (up to 20,833).
     * Should return 0 tax.
     */
    @Test
    public void testBracket1() {
        double salary = 20000.00;
        double expected = 0.0;
        double actual = tax.calculate(salary);
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testBracket2() {
        double salary = 25000.00;
        double expected = (salary - 20833) * 0.15;
        double actual = tax.calculate(salary);
        assertEquals(expected, actual, 0.001);
    }
    /*
     * Test for salary in the 2nd bracket (20,834 - 33,332).
     * Formula: 15% of excess over 20,833
     */

    @Test
    public void testBracket3() {
        double salary = 50000.00;
        double expected = 1875 + (salary - 33332) * 0.20;
        double actual = tax.calculate(salary);
        assertEquals(expected, actual, 0.001);
    }
    /*
     * Test for salary in the 3rd bracket (33,333 - 66,666).
     * Formula: 1875 + 20% of excess over 33,332
     */

    @Test
    public void testBracket4() {
        double salary = 100000.00;
        double expected = 8541.80 + (salary - 66666) * 0.25;
        double actual = tax.calculate(salary);
        assertEquals(expected, actual, 0.001);
    }
    /*
     * Test for salary in the 4th bracket (66,667 - 166,666).
     * Formula: 8541.80 + 25% of excess over 66,666
     */

    @Test
    public void testBracket5() {
        double salary = 500000.00;
        double expected = 33541.80 + (salary - 166666) * 0.30;
        double actual = tax.calculate(salary);
        assertEquals(expected, actual, 0.001);
    }
    /*
     * Test for salary in the 5th bracket (166,667 - 666,666).
     * Formula: 33541.80 + 30% of excess over 166,666
     */

    @Test
    public void testBracket6() {
        double salary = 1000000.00;
        double expected = 183541.80 + (salary - 666666) * 0.35;
        double actual = tax.calculate(salary);
        assertEquals(expected, actual, 0.001);
    }
    /*
     * Test for salary in the 6th bracket (above 666,666).
     * Formula: 183541.80 + 35% of excess over 666,666
     */
}


