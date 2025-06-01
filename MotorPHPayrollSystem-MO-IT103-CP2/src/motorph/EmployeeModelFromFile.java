/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */

package motorph;

import java.io.File; // handle file reading
import java.io.FileNotFoundException; // handle errors when the file is missing
import java.util.Scanner; // read and parse data from the file

public class EmployeeModelFromFile extends EmployeeModel {
/* 
 * OOP PRINCIPLE: INHERITANCE
 * EmployeeModelFromFile extends EmployeeModel, inheriting its attributes and behavior.
 */

    // CONSTRUCTOR
    public EmployeeModelFromFile() {
        File file = new File("src\\resources\\employee-data.csv");
        getDataFromFile(file);
        if (employees == null) { 
            employees = new Employee[0]; //null check
        }
        /* 
         * OOP PRINCIPLE: ABSTRACTION
         * Calls getDataFromFile(), which handles the complexity of file reading.
         */
    }

    @Override
    public Employee[] getEmployeeModelList() {
        return employees;
    }
    /* 
     * OOP PRINCIPLE: POLYMORPHISM
     * getEmployeeModelList overrides the method to provide a specific implementation.
     */

    // READING DATA FROM FILE
    private void getDataFromFile(File textFile) {
        try {
            if (!textFile.exists()) { //check if file exists to avoid FileNotFoundException
                System.out.println("Error: CSV file not found!");
                employees = new Employee[0];
                return;
            }

            Scanner lineCounter = new Scanner(textFile);
            int lines = 0;
            while (lineCounter.hasNextLine()) { // determine the number of employees through the number of lines
                lineCounter.nextLine();
                lines++;
            }
            lineCounter.close();
    
            employees = new Employee[lines - 1];
    
            Scanner scannerToGetFile = new Scanner(textFile);
            
            if (scannerToGetFile.hasNextLine()) {
                scannerToGetFile.nextLine(); // skip line 1 (header) on csv file
            }
    
            int counter = 0;
            while (scannerToGetFile.hasNextLine()) {
                String data = scannerToGetFile.nextLine();
                Scanner scannerForParsing = new Scanner(data);
                parseDataFromScanner(scannerForParsing, counter);
                counter++;
            }
            scannerToGetFile.close();
    
        } catch (FileNotFoundException ex) {
            System.out.println("Error: File not found.");
            employees = new Employee[0]; // null check
        }
    }

    // PARSING CSV DATA
    private void parseDataFromScanner(Scanner scanner, int counter) {
        scanner.useDelimiter(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // custom delimiter: handles commas inside quotes
    
        if (!scanner.hasNext()) {
            System.out.println("Error: Empty line detected!");
            return;
        }
    
        Employee employee = new Employee();
        employee.setEmpNo(cleanString(scanner.next()));
        employee.setLastName(cleanString(scanner.next()));
        employee.setFirstName(cleanString(scanner.next()));
        employee.setBirthday(cleanString(scanner.next()));
        employee.setAddress(cleanString(scanner.next()));
        employee.setPhoneNo(cleanString(scanner.next()));
        employee.setSssNo(cleanString(scanner.next()));
        employee.setPhilHealthNo(cleanString(scanner.next()));
        employee.setTinNo(cleanString(scanner.next()));
        employee.setPagibigNo(cleanString(scanner.next()));
        employee.setStatus(cleanString(scanner.next()));
        employee.setPosition(cleanString(scanner.next()));
        employee.setSupervisor(cleanString(scanner.next()));
    
        employee.setBasicSalary(parseDoubleOrZero(scanner.next()));
        employee.setRiceSubsidy(parseDoubleOrZero(scanner.next()));
        employee.setPhoneAllowance(parseDoubleOrZero(scanner.next()));
        employee.setClothingAllowance(parseDoubleOrZero(scanner.next()));
        employee.setSemiMonthlyRate(parseDoubleOrZero(scanner.next()));
        employee.setHourlyRate(parseDoubleOrZero(scanner.next()));
    
        employees[counter] = employee;
    }
    
    // HELPER METHOD
    private String cleanString(String value) {
        return value.replaceAll("^\"|\"$", "").trim();
    }
    
    private double parseDoubleOrZero(String value) {
        if (value.equalsIgnoreCase("N/A") || value.trim().isEmpty()) {
            return 0.0; // converts N/A to 0.0
        }
        value = value.replaceAll("^\"|\"$", "").replace(",", ""); //remove quotes and commas
        return Double.parseDouble(value);
    }    
}    
