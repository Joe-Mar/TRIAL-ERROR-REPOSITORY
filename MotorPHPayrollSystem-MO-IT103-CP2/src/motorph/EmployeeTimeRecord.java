/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */

package motorph;

import java.util.HashMap;
import java.util.Map;

public class EmployeeTimeRecord extends Employee {
/* 
 * OOP PRINCIPLE: INHERITANCE
 * EmployeeTimeRecord is a child class of Employee, inheriting its properties and methods.
 */
    private Map<String, Integer> workHoursMap = new HashMap<>(); 

    private int timeIn;
    private int timeOut;
    /* 
     * OOP PRINCIPLE: ENCAPSULATION
     * The private access modifier ensures that these attributes cannot be accessed directly from outside the class.
     */
    
    // GETTER METHOD for timeIn
    public int getTimeIn() {
        return timeIn; // returns the value of timeIn
    }

    // SETTER METHOD for timeIn 
    public void setTimeIn(int timeIn) {
        this.timeIn = timeIn; // assigns the passed value to timeIn
    }

    // GETTER METHOD for timeOut
    public int getTimeOut() {
        return timeOut; // returns the value of timeOut
    }

    // SETTER METHOD for timeOut
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut; // assigns the passed value to timeOut
    }
    
    public void addWorkHours(String date, int hours) {
        workHoursMap.put(date, workHoursMap.getOrDefault(date, 0) + hours);
    }

    public int getTotalHoursWorked(String startDate, String endDate) {
        int total = 0;
        for (Map.Entry<String, Integer> entry : workHoursMap.entrySet()) {
            String date = entry.getKey();
            if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0) {
                total += entry.getValue();
            }
        }
        return total;
    }
    /* 
     * OOP PRINCIPLE: ENCAPSULATION
     * Provides controlled access to private variables without exposing it directly.
     */
    
    // Adds worked hours for a given date
}

