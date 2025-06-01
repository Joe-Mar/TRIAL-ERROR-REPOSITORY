/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */

package motorph;

import java.util.Scanner; // handle user input

public class MotorPH {

    private static EmployeeModel employeeModel;
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        employeeModel = new EmployeeModelFromFile();
        displayMainMenu();
    }
    /* 
     * OOP PRINCIPLE: POLYMORPHISM
     * employeeModel is declared as EmployeeModel, but it holds an instance of EmployeeModelFromFile (a subclass).
     */

    // MAIN MENU DISPLAY
    private static void displayMainMenu() {
        System.out.println("\n============================================================");
        System.out.println("               MOTORPH PAYROLL MANAGEMENT SYSTEM            ");
        System.out.println("============================================================");
        System.out.println("[1] Employee Directory");
        System.out.println("[2] Compute Net Salary");
        System.out.println("[3] Compute Salary Based on Work Hours");
        System.out.println("[4] Exit");
        System.out.println("============================================================");
        System.out.print("Select an option: ");

        String option = input.nextLine().trim();
        System.out.println("============================================================");

        processOption(option);
    }
    
    // SWITCH STATEMENT
    private static void processOption(String option) {
        switch (option) { // determine which method to execute based on the user's choice
            case "1":
                displayEmployeeDirectory();
                break;
            case "2":
                computeNetSalary();
                break;
            case "3":
                computeSalaryBasedOnHours();
                break;
            case "4":
                System.out.println("\nThank you for using MotorPH Payroll Management System!");
                System.out.println("============================================================");
                System.exit(0);
                break;
            default:
                System.out.println("\nInvalid option. Please try again.");
                System.out.println("============================================================");
                displayMainMenu();
                break;
        }
    }
    
    // EMPLOYEE DIRECTORY
    private static void displayEmployeeDirectory() {
        Employee[] employees = employeeModel.getEmployeeModelList();

        if (employees == null || employees.length == 0) {
            System.out.println("\n============================================================");
            System.out.println("                      EMPLOYEE DIRECTORY                    ");
            System.out.println("============================================================");
            System.out.println("Error: No employee records found.");
            System.out.println("============================================================");
            return;
        }

        System.out.println("\n============================================================");
        System.out.println("                      EMPLOYEE DIRECTORY                    ");
        System.out.println("============================================================");

        for (Employee employee : employees) {
            if (employee == null) {
                System.out.println("Error: An employee record is missing.");
                continue;
            }

            System.out.println("Employee No  : " + employee.getEmpNo());
            System.out.println("Name         : " + employee.getLastName() + ", " + employee.getFirstName());
            System.out.println("Birthday     : " + employee.getBirthday());
            System.out.println("------------------------------------------------------------");
        }
    }

    // COMPUTING NET SALARY
    private static void computeNetSalary() {
        Employee[] employees = employeeModel.getEmployeeModelList();
        TotalDeductions deductions = new TotalDeductions();
    
        System.out.println("\n============================================================");
        System.out.println("                          NET SALARY                        ");
        System.out.println("============================================================");
    
        for (Employee employee : employees) {
            double netSalary = employee.getBasicSalary() - deductions.calculateTotalDeductions(employee.getBasicSalary());
    
            System.out.println("Employee No  : " + employee.getEmpNo());
            System.out.println("Name         : " + employee.getLastName() + ", " + employee.getFirstName());
            System.out.println("Basic Salary : " + employee.getBasicSalary());
            System.out.println("Net Salary   : " + netSalary);
            System.out.println("------------------------------------------------------------");
        }
    }

    // COMPUTING SALARY BASED ON WORK HOURS
    private static void computeSalaryBasedOnHours() {
        Employee[] employees = employeeModel.getEmployeeModelList();
        SalaryOnHoursWorked salaryCalculator = new SalaryOnHoursWorked();

        System.out.println("\n============================================================");
        System.out.println("               SALARY BASED ON HOURS WORKED                 ");
        System.out.println("============================================================");

        for (Employee employee : employees) {
            double salaryOnHours = salaryCalculator.getSalaryOnHoursWorked(employee.getHourlyRate());

            System.out.println("Employee No            : " + employee.getEmpNo());
            System.out.println("Name                   : " + employee.getLastName() + ", " + employee.getFirstName());
            System.out.println("Basic Salary           : " + employee.getBasicSalary());
            System.out.println("Salary on Hours Worked : " + salaryOnHours);
            System.out.println("------------------------------------------------------------");
        }
    }
}