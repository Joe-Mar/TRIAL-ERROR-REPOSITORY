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

public class EmployeeModelFromFileTest {

    private EmployeeModelFromFile model;

    @Before
    public void setUp() {
        model = new EmployeeModelFromFile();
    }
    /*
     * @Before runs before each test.
     * It initializes the EmployeeModelFromFile instance.
     */
    
    @After
    public void tearDown() {
        model = null;
    }
    /*
     * @After runs after each test.
     * It clears the EmployeeModelFromFile instance.
     */
    
    @Test
    public void testEmployeeDataLoadedFromFile() {
        Employee[] employees = model.getEmployeeModelList(); // get loaded data
        assertNotNull(employees); // make sure it's not null
        assertEquals(34, employees.length); // confirm there are 34 records

        Employee first = employees[0]; // get first employee

        // Validate expected data from employee_data.csv (line 1)
        assertEquals("1", first.getEmpNo()); // check employee number
        assertEquals("Manuel III", first.getFirstName()); // check first name
        assertEquals("Garcia", first.getLastName()); // check last name
        assertEquals("10/11/1983", first.getBirthday()); // check birthday
    }
}


