/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */

package motorph;

public abstract class EmployeeModel {
/* 
 * OOP PRINCIPLE: ABSTRACTION
 * EmployeeModel defines a blueprint for handling employee models but does not provide full implementations.
 */
    
    // DECLARING AN EMPLOYEE OBJECT ARRAY
    protected Employee[] employees;
    /* 
     * OOP PRINCIPLE: ENCAPSULATION
     * The protected access modifier ensures that these attributes cannot be accessed directly from outside the class,
     * but can be accessed by subclasses.
     */
    
    protected abstract Employee[] getEmployeeModelList();
    /* 
     * OOP PRINCIPLE: ABSTRACTION
     * This method does not have a body, forcing subclasses to implement their own version of getEmployeeModelList()
     */
}
