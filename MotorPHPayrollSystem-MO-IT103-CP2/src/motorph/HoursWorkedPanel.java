/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package motorph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class HoursWorkedPanel extends javax.swing.JFrame {
            private Map<String, EmployeeTimeRecord> employees = new HashMap<>();

              public HoursWorkedPanel() {
              initComponents();
              loadEmployeesFromCSV("src\\resources\\employee-data.csv");
              loadAttendanceFromCSV("src\\resources\\attendance_data.csv"); 
              populateDateRanges();
          }

          private double parseDoubleSafe(String value) {
              try {
                  // Remove quotes and commas before parsing
                  value = value.replace("\"", "").replace(",", "").trim();
                  return Double.parseDouble(value);
              } catch (NumberFormatException e) {
                  System.err.println("Invalid number: [" + value + "]");
                  return 0.0;
              }
          }
    
            private void loadEmployeesFromCSV(String filename) {
                        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                br.readLine(); // skip header

                while ((line = br.readLine()) != null) {
                    List<String> data = splitCSVLine(line);
                    if (data.size() < 19) continue; // skip invalid rows

                    EmployeeTimeRecord emp = new EmployeeTimeRecord();
                    emp.setEmpNo(data.get(0));
                    emp.setLastName(data.get(1));
                    emp.setFirstName(data.get(2));
                    emp.setBirthday(data.get(3));
                    emp.setAddress(data.get(4));
                    emp.setPhoneNo(data.get(5));
                    emp.setSssNo(data.get(6));
                    emp.setPhilHealthNo(data.get(7));
                    emp.setTinNo(data.get(8));
                    emp.setPagibigNo(data.get(9));
                    emp.setStatus(data.get(10));
                    emp.setPosition(data.get(11));
                    emp.setSupervisor(data.get(12));
                    emp.setBasicSalary(parseDoubleSafe(data.get(13)));
                    emp.setRiceSubsidy(parseDoubleSafe(data.get(14)));
                    emp.setPhoneAllowance(parseDoubleSafe(data.get(15)));
                    emp.setClothingAllowance(parseDoubleSafe(data.get(16)));
                    emp.setSemiMonthlyRate(parseDoubleSafe(data.get(17)));
                    emp.setHourlyRate(parseDoubleSafe(data.get(18)));

                    employees.put(emp.getEmpNo(), emp);

                    // Debug log
                    System.out.println("Loaded: " + emp.getEmpNo() + " | " + emp.getFirstName() + " " + emp.getLastName()
                            + " | Salary: " + emp.getBasicSalary() + " | Hourly Rate: " + emp.getHourlyRate());
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading employee CSV: " + ex.getMessage());
            }
        }
    
                private List<String> splitCSVLine(String line) {
                List<String> result = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                boolean inQuotes = false;

                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);

                    if (c == '\"') {
                        inQuotes = !inQuotes; // toggle state
                    } else if (c == ',' && !inQuotes) {
                        result.add(sb.toString().trim());
                        sb.setLength(0);
                    } else {
                        sb.append(c);
                    }
                }

                result.add(sb.toString().trim()); // add last field
                return result;
            }

            private void loadAttendanceFromCSV(String filename) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                br.readLine(); // skip header

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");

                    if (data.length < 6) continue; // basic validation

                    String empNo = data[0].trim();
                    String date = formatDate(data[3].trim()); // convert to yyyy-MM-dd
                    String timeInStr = data[4].trim();
                    String timeOutStr = data[5].trim();

                    int timeIn = parseHour(timeInStr);
                    int timeOut = parseHour(timeOutStr);
                    int hoursWorked = timeOut - timeIn;

                    EmployeeTimeRecord emp = employees.get(empNo);
                    if (emp != null && hoursWorked > 0) {
                        emp.addWorkHours(date, hoursWorked);
                        System.out.println("Parsing: " + empNo + " | " + date + " | " + timeIn + " to " + timeOut + " = " + hoursWorked);
                    }
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading attendance CSV: " + ex.getMessage());
            }
        }

        private int parseHour(String time) {
                try {
                    String[] parts = time.split(":");
                    int hour = Integer.parseInt(parts[0]);
                    int minute = Integer.parseInt(parts[1]);
                    return (minute >= 30) ? hour + 1 : hour; // round up if >= 30 min
                } catch (Exception e) {
                    System.err.println("Failed to parse time: [" + time + "]");
                    return 0;
                }
            }

        private String formatDate(String rawDate) {
                try {
                    Date date = new SimpleDateFormat("MM/dd/yyyy").parse(rawDate);
                    return new SimpleDateFormat("yyyy-MM-dd").format(date);
                } catch (ParseException e) {
                    System.err.println("Invalid date format: " + rawDate);
                    return rawDate; // fallback
                }
            }

    private void populateDateRanges() {
        String[] ranges = {
            "2024-06-01 to 2024-06-15", "2024-06-16 to 2024-06-30",
            "2024-07-01 to 2024-07-15", "2024-07-16 to 2024-07-31",
            "2024-08-01 to 2024-08-15", "2024-08-16 to 2024-08-31",
            "2024-09-01 to 2024-09-15", "2024-09-16 to 2024-09-30",
            "2024-10-01 to 2024-10-15", "2024-10-16 to 2024-10-31",
            "2024-11-01 to 2024-11-15", "2024-11-16 to 2024-11-30",
            "2024-12-01 to 2024-12-15", "2024-12-16 to 2024-12-31"
        };
        for (String range : ranges) {
            jcbDateRange.addItem(range);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelEmpNo2 = new javax.swing.JLabel();
        textFieldEmpNo2 = new javax.swing.JTextField();
        buttonSubmit2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        labelEmpNo3 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        labelBasicSalary = new javax.swing.JLabel();
        labelNetSalary = new javax.swing.JLabel();
        labelColon1 = new javax.swing.JLabel();
        labelColon2 = new javax.swing.JLabel();
        labelColon3 = new javax.swing.JLabel();
        labelColon4 = new javax.swing.JLabel();
        labelEmpNo4 = new javax.swing.JLabel();
        labelColon5 = new javax.swing.JLabel();
        labelName1 = new javax.swing.JLabel();
        labelColon7 = new javax.swing.JLabel();
        labelBasicSalary1 = new javax.swing.JLabel();
        labelColon8 = new javax.swing.JLabel();
        labelColon9 = new javax.swing.JLabel();
        labelNetSalary1 = new javax.swing.JLabel();
        lblEmployeeNO = new javax.swing.JLabel();
        txtFieldID = new javax.swing.JTextField();
        btnFetchEmployee = new javax.swing.JButton();
        lblHoursWorked = new javax.swing.JLabel();
        btnCalculateSalary = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labelEmpNO = new javax.swing.JLabel();
        lblEmployeeID = new javax.swing.JLabel();
        labelName2 = new javax.swing.JLabel();
        lblNAME = new javax.swing.JLabel();
        labelBasicSalary2 = new javax.swing.JLabel();
        lblBasicSalary = new javax.swing.JLabel();
        lblSalaryOnHoursWorked = new javax.swing.JLabel();
        labelNetSalary2 = new javax.swing.JLabel();
        jcbDateRange = new javax.swing.JComboBox<>();

        labelEmpNo2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo2.setText("Employee NO.:");

        textFieldEmpNo2.setBackground(new java.awt.Color(204, 204, 204));
        textFieldEmpNo2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        buttonSubmit2.setBackground(new java.awt.Color(0, 51, 0));
        buttonSubmit2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        buttonSubmit2.setForeground(new java.awt.Color(255, 255, 255));
        buttonSubmit2.setText("Submit");

        labelEmpNo3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo3.setText("Employee NO.");

        labelName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelName.setText("Name");

        labelBasicSalary.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelBasicSalary.setText("Basic Salary");

        labelNetSalary.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelNetSalary.setText("Net Salary");

        labelColon1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon1.setText(":");

        labelColon2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon2.setText(":");

        labelColon3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon3.setText(":");

        labelColon4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon4.setText(":");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelEmpNo2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldEmpNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonSubmit2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEmpNo3)
                                    .addComponent(labelName)
                                    .addComponent(labelBasicSalary)
                                    .addComponent(labelNetSalary))
                                .addGap(97, 97, 97)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelColon4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelColon3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelColon2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelColon1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 154, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmpNo2)
                    .addComponent(textFieldEmpNo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSubmit2))
                .addGap(45, 45, 45)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmpNo3)
                    .addComponent(labelColon1))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(labelColon2))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBasicSalary)
                    .addComponent(labelColon3))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNetSalary)
                    .addComponent(labelColon4))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        labelEmpNo4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo4.setText("Employee NO.");

        labelColon5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon5.setText(":");

        labelName1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelName1.setText("Name");

        labelColon7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon7.setText(":");

        labelBasicSalary1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelBasicSalary1.setText("Basic Salary");

        labelColon8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon8.setText(":");

        labelColon9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon9.setText(":");

        labelNetSalary1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelNetSalary1.setText("Net Salary");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblEmployeeNO.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblEmployeeNO.setText("Employee NO.:");

        txtFieldID.setBackground(new java.awt.Color(204, 204, 204));
        txtFieldID.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        btnFetchEmployee.setBackground(new java.awt.Color(0, 51, 0));
        btnFetchEmployee.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnFetchEmployee.setForeground(new java.awt.Color(255, 255, 255));
        btnFetchEmployee.setText("Fetch Employee");
        btnFetchEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFetchEmployeeActionPerformed(evt);
            }
        });

        lblHoursWorked.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblHoursWorked.setText("Hours Worked:");

        btnCalculateSalary.setBackground(new java.awt.Color(0, 51, 0));
        btnCalculateSalary.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnCalculateSalary.setForeground(new java.awt.Color(255, 255, 255));
        btnCalculateSalary.setText("Calculate Salary");
        btnCalculateSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateSalaryActionPerformed(evt);
            }
        });

        labelEmpNO.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNO.setText("Employee NO.");

        lblEmployeeID.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblEmployeeID.setText(":");

        labelName2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelName2.setText("Name");

        lblNAME.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNAME.setText(":");

        labelBasicSalary2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelBasicSalary2.setText("Basic Salary");

        lblBasicSalary.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblBasicSalary.setText(":");

        lblSalaryOnHoursWorked.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblSalaryOnHoursWorked.setText(":");

        labelNetSalary2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelNetSalary2.setText("Salary on Hours Worked");

        jcbDateRange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelName2)
                            .addComponent(labelBasicSalary2)
                            .addComponent(labelNetSalary2))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNAME, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSalaryOnHoursWorked, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblHoursWorked)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jcbDateRange, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnCalculateSalary))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblEmployeeNO)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnFetchEmployee)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelEmpNO)
                        .addGap(97, 97, 97)
                        .addComponent(lblEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmployeeNO)
                    .addComponent(txtFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFetchEmployee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoursWorked)
                    .addComponent(btnCalculateSalary)
                    .addComponent(jcbDateRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmpNO)
                    .addComponent(lblEmployeeID))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName2)
                    .addComponent(lblNAME))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBasicSalary2)
                    .addComponent(lblBasicSalary))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNetSalary2)
                    .addComponent(lblSalaryOnHoursWorked))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFetchEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFetchEmployeeActionPerformed
        String empNo = txtFieldID.getText().trim();
    EmployeeTimeRecord emp = employees.get(empNo);

    if (emp != null) {
        lblEmployeeID.setText(emp.getEmpNo());
        lblNAME.setText(emp.getFirstName() + " " + emp.getLastName());

        double basicSalary = emp.getBasicSalary();
        System.out.println("Basic Salary: " + basicSalary); // Debug print

        lblBasicSalary.setText(String.format("%.2f", basicSalary));

        // Clear other labels in case previously computed
        lblHoursWorked.setText("");  
        lblSalaryOnHoursWorked.setText("");
        labelNetSalary.setText("");
    } else {
        JOptionPane.showMessageDialog(this, "Employee not found.");
    }
    }//GEN-LAST:event_btnFetchEmployeeActionPerformed

    private void btnCalculateSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateSalaryActionPerformed
        String empNo = txtFieldID.getText().trim();
    EmployeeTimeRecord emp = employees.get(empNo);
    
    if (emp == null) {
        JOptionPane.showMessageDialog(this, "Please fetch an employee first.");
        return;
    }

    String[] range = ((String) jcbDateRange.getSelectedItem()).split(" to ");
    int totalHours = emp.getTotalHoursWorked(range[0], range[1]);
    double hourlyRate = emp.getHourlyRate();
    double salary = totalHours * hourlyRate;
    
    System.out.println("Basic Salary: " + emp.getBasicSalary());
    System.out.println("Hourly Rate: " + emp.getHourlyRate ());
    System.out.println("Hours: " + totalHours);
    System.out.println("Salary on Hours Worked: " + salary);
    
            if (lblBasicSalary == null) {
            System.out.println("lblBasicSalary is null!");
        }
    lblHoursWorked.setText(String.valueOf(totalHours));        
    lblBasicSalary.setText(String.format("%.2f", emp.getBasicSalary()));
    lblSalaryOnHoursWorked.setText(String.format("%.2f", salary));

    double total = salary + emp.getRiceSubsidy() + emp.getPhoneAllowance() + emp.getClothingAllowance();
    labelNetSalary.setText(String.format("%.2f", total));
    
    System.out.println("Calculating for ID: " + empNo);
        if (emp == null) {
            System.out.println("Employee not found in map.");
        } else {
            System.out.println("Employee found: " + emp.getFirstName() + " " + emp.getLastName());
            System.out.println("Basic Salary: " + emp.getBasicSalary());
            System.out.println("Hourly Rate: " + emp.getHourlyRate());
        }

    }//GEN-LAST:event_btnCalculateSalaryActionPerformed
                       

    /**
     * @param args the command line arguments
     */
        public static void main(String args[]) {
            java.awt.EventQueue.invokeLater(() -> new HoursWorkedPanel().setVisible(true));
        }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculateSalary;
    private javax.swing.JButton btnFetchEmployee;
    private javax.swing.JButton buttonSubmit2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jcbDateRange;
    private javax.swing.JLabel labelBasicSalary;
    private javax.swing.JLabel labelBasicSalary1;
    private javax.swing.JLabel labelBasicSalary2;
    private javax.swing.JLabel labelColon1;
    private javax.swing.JLabel labelColon2;
    private javax.swing.JLabel labelColon3;
    private javax.swing.JLabel labelColon4;
    private javax.swing.JLabel labelColon5;
    private javax.swing.JLabel labelColon7;
    private javax.swing.JLabel labelColon8;
    private javax.swing.JLabel labelColon9;
    private javax.swing.JLabel labelEmpNO;
    private javax.swing.JLabel labelEmpNo2;
    private javax.swing.JLabel labelEmpNo3;
    private javax.swing.JLabel labelEmpNo4;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelName1;
    private javax.swing.JLabel labelName2;
    private javax.swing.JLabel labelNetSalary;
    private javax.swing.JLabel labelNetSalary1;
    private javax.swing.JLabel labelNetSalary2;
    private javax.swing.JLabel lblBasicSalary;
    private javax.swing.JLabel lblEmployeeID;
    private javax.swing.JLabel lblEmployeeNO;
    private javax.swing.JLabel lblHoursWorked;
    private javax.swing.JLabel lblNAME;
    private javax.swing.JLabel lblSalaryOnHoursWorked;
    private javax.swing.JTextField textFieldEmpNo2;
    private javax.swing.JTextField txtFieldID;
    // End of variables declaration//GEN-END:variables
}
