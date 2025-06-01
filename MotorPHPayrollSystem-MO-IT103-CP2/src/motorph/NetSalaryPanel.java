/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package motorph;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class NetSalaryPanel extends javax.swing.JPanel {
    private static EmployeeModel employeeModel;

    public NetSalaryPanel() {
        initComponents();
        addSubmitAction();
    }

    private void addSubmitAction() {
        buttonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                getEmployeeInfo(textFieldEmpNo.getText().trim());
            }
        });
    }

    private void getEmployeeInfo(String empNumber) {
        Employee[] employees = employeeModel.getEmployeeModelList();
        TotalDeductions deductions = new TotalDeductions();
        boolean found = false;

        for (Employee employee : employees) {
            if (employee != null && employee.getEmpNo().equalsIgnoreCase(empNumber)) {
                double basicSalary = employee.getBasicSalary();
                double netSalary = basicSalary - deductions.calculateTotalDeductions(basicSalary);

                labelColon1.setText(employee.getEmpNo());
                labelColon2.setText(employee.getLastName() + ", " + employee.getFirstName());
                labelColon3.setText(String.format("%.2f", basicSalary));
                labelColon4.setText(String.format("%.2f", netSalary));
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this,
                "Employee with Employee Number " + empNumber + " not found.",
                "Employee Not Found",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelEmpNo = new javax.swing.JLabel();
        textFieldEmpNo = new javax.swing.JTextField();
        buttonSubmit = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labelEmpNo2 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        labelBasicSalary = new javax.swing.JLabel();
        labelNetSalary = new javax.swing.JLabel();
        labelColon1 = new javax.swing.JLabel();
        labelColon2 = new javax.swing.JLabel();
        labelColon3 = new javax.swing.JLabel();
        labelColon4 = new javax.swing.JLabel();

        labelEmpNo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo.setForeground(new java.awt.Color(0, 0, 0));
        labelEmpNo.setText("Employee NO.:");

        textFieldEmpNo.setBackground(new java.awt.Color(204, 204, 204));
        textFieldEmpNo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        textFieldEmpNo.setForeground(new java.awt.Color(0, 0, 0));

        buttonSubmit.setBackground(new java.awt.Color(0, 51, 0));
        buttonSubmit.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        buttonSubmit.setForeground(new java.awt.Color(255, 255, 255));
        buttonSubmit.setText("Submit");

        labelEmpNo2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo2.setForeground(new java.awt.Color(0, 0, 0));
        labelEmpNo2.setText("Employee NO.");

        labelName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelName.setForeground(new java.awt.Color(0, 0, 0));
        labelName.setText("Name");

        labelBasicSalary.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelBasicSalary.setForeground(new java.awt.Color(0, 0, 0));
        labelBasicSalary.setText("Basic Salary");

        labelNetSalary.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelNetSalary.setForeground(new java.awt.Color(0, 0, 0));
        labelNetSalary.setText("Net Salary");

        labelColon1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon1.setForeground(new java.awt.Color(0, 0, 0));
        labelColon1.setText(":");

        labelColon2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon2.setForeground(new java.awt.Color(0, 0, 0));
        labelColon2.setText(":");

        labelColon3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon3.setForeground(new java.awt.Color(0, 0, 0));
        labelColon3.setText(":");

        labelColon4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelColon4.setForeground(new java.awt.Color(0, 0, 0));
        labelColon4.setText(":");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelEmpNo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldEmpNo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonSubmit))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEmpNo2)
                                    .addComponent(labelName)
                                    .addComponent(labelBasicSalary)
                                    .addComponent(labelNetSalary))
                                .addGap(97, 97, 97)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelColon4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelColon3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelColon2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelColon1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 154, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmpNo)
                    .addComponent(textFieldEmpNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSubmit))
                .addGap(45, 45, 45)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmpNo2)
                    .addComponent(labelColon1))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(labelColon2))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBasicSalary)
                    .addComponent(labelColon3))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNetSalary)
                    .addComponent(labelColon4))
                .addContainerGap(123, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) {                                             
        getEmployeeInfo(textFieldEmpNo.getText());
    }                                            

    // GET EMPLOYEE INFO
    public static void main(String args[]) {
        employeeModel = new EmployeeModelFromFile(); // Make sure this class is correctly implemented

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Net Salary Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new NetSalaryPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSubmit;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelBasicSalary;
    private javax.swing.JLabel labelColon1;
    private javax.swing.JLabel labelColon2;
    private javax.swing.JLabel labelColon3;
    private javax.swing.JLabel labelColon4;
    private javax.swing.JLabel labelEmpNo;
    private javax.swing.JLabel labelEmpNo2;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelNetSalary;
    private javax.swing.JTextField textFieldEmpNo;
    // End of variables declaration//GEN-END:variables
}
