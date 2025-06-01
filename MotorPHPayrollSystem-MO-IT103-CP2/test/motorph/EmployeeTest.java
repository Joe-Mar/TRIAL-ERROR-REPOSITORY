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

public class EmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee();
    }
    /*
     * @Before runs before each test.
     * It initializes the Employee instance.
     */
    
    @After
    public void tearDown() {
        employee = null;
    }
    /*
     * @After runs after each test.
     * It clears the Employee instance.
     */

    @Test
    public void testEmpNo() {
        String expected = "1"; // expected employee number
        employee.setEmpNo(expected); // set employee number
        assertEquals(expected, employee.getEmpNo()); // check if getter returns correct value
    }

    @Test
    public void testName() {
        String first = "Manuel III"; // sample first name
        String last = "Garcia"; // sample last name
        employee.setFirstName(first); // set first name
        employee.setLastName(last); // set last name
        assertEquals(first, employee.getFirstName()); // check first name
        assertEquals(last, employee.getLastName()); // check last name
    }

    @Test
    public void testBasicSalary() {
        double expected = 90000.00; // sample salary
        employee.setBasicSalary(expected); // set salary
        assertEquals(expected, employee.getBasicSalary(), 0.001); // check salary with margin
    }

    @Test
    public void testPosition() {
        String position = "Chief Executive Officer"; // sample job position
        employee.setPosition(position); // set position
        assertEquals(position, employee.getPosition()); // check position
    }

    @Test
    public void testStatusAndBirthday() {
        String status = "Regular"; // sample status
        String birthday = "10/11/1983"; // sample birthday
        employee.setStatus(status); // set status
        employee.setBirthday(birthday); // set birthday
        assertEquals(status, employee.getStatus()); // check status
        assertEquals(birthday, employee.getBirthday()); // check birthday
    }
}


