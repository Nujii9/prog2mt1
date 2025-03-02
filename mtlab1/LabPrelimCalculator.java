import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LabPrelimCalculator {
    public static void main(String[] args) {
        // Create JFrame
        JFrame frame = new JFrame("Prog 2 - Lab Prelim Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 450);
        frame.setLayout(new GridLayout(1, 2));

        // Result Panel
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(4, 1));
        resultPanel.setBackground(new Color(123, 123, 123));

        JLabel resultLabel = new JLabel("Result", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 22));
        resultLabel.setForeground(Color.WHITE);

        JLabel percentageLabel = new JLabel("Percentage: 0", JLabel.CENTER);
        JLabel gradeLabel = new JLabel("Grade: N/A", JLabel.CENTER);
        JLabel gpaLabel = new JLabel("GWA: N/A", JLabel.CENTER);

        // Font size
        percentageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gpaLabel.setFont(new Font("Arial", Font.BOLD, 18));

        percentageLabel.setForeground(Color.WHITE);
        gradeLabel.setForeground(Color.WHITE);
        gpaLabel.setForeground(Color.WHITE);

        resultPanel.add(resultLabel);
        resultPanel.add(percentageLabel);
        resultPanel.add(gradeLabel);
        resultPanel.add(gpaLabel);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 1));

        JTextField java1Field = new JTextField();
        JTextField java2Field = new JTextField();
        JTextField js1Field = new JTextField();
        JTextField js2Field = new JTextField();
        JTextField mp1Field = new JTextField();
        JTextField mp2Field = new JTextField();
        JTextField mp3Field = new JTextField();
        JTextField mp3DocField = new JTextField();
        JTextField attendanceField = new JTextField();

        inputPanel.add(new JLabel("Java 1 Score:"));
        inputPanel.add(java1Field);
        inputPanel.add(new JLabel("Java 2 Score:"));
        inputPanel.add(java2Field);
        inputPanel.add(new JLabel("JS 1 Score:"));
        inputPanel.add(js1Field);
        inputPanel.add(new JLabel("JS 2 Score:"));
        inputPanel.add(js2Field);
        inputPanel.add(new JLabel("MP1 Score:"));
        inputPanel.add(mp1Field);
        inputPanel.add(new JLabel("MP2 Score:"));
        inputPanel.add(mp2Field);
        inputPanel.add(new JLabel("MP3 Score:"));
        inputPanel.add(mp3Field);
        inputPanel.add(new JLabel("MP3 Doc Score:"));
        inputPanel.add(mp3DocField);
        inputPanel.add(new JLabel("Attendance (%):"));
        inputPanel.add(attendanceField);

        JButton calculateButton = new JButton("Show Result");
        inputPanel.add(calculateButton);

        // ActionListener for Calculation
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double java1 = Double.parseDouble(java1Field.getText());
                    double java2 = Double.parseDouble(java2Field.getText());
                    double js1 = Double.parseDouble(js1Field.getText());
                    double js2 = Double.parseDouble(js2Field.getText());
                    double mp1 = Double.parseDouble(mp1Field.getText());
                    double mp2 = Double.parseDouble(mp2Field.getText());
                    double mp3 = Double.parseDouble(mp3Field.getText());
                    double mp3Doc = Double.parseDouble(mp3DocField.getText());
                    double attendance = Double.parseDouble(attendanceField.getText());

                    // Prelim Exam Calculation
                    double prelimExam = (0.2 * java1) + (0.3 * java2) + (0.2 * js1) + (0.3 * js2);

                    // Lab Work Calculation
                    double labWork = (mp1 + mp2 + mp3 + mp3Doc) / 4;

                    // Prelim Class Standing Calculation
                    double prelimClassStanding = (0.6 * labWork) + (0.4 * attendance);

                    // Prelim Grade Calculation
                    double prelimGrade = (0.6 * prelimExam) + (0.4 * prelimClassStanding);
                    String percentage = String.format("%.2f", prelimGrade);

                    // GPA and Grade Conversion
                    String grade;
                    double gpa;
                    if (prelimGrade >= 99) { gpa = 1.0; grade = "Excellent"; }
                    else if (prelimGrade >= 96) { gpa = 1.25; grade = "Superior"; }
                    else if (prelimGrade >= 93) { gpa = 1.5; grade = "Very Good"; }
                    else if (prelimGrade >= 90) { gpa = 1.75; grade = "Good"; }
                    else if (prelimGrade >= 87) { gpa = 2.0; grade = "Meritorious"; }
                    else if (prelimGrade >= 84) { gpa = 2.25; grade = "Very Satisfactory"; }
                    else if (prelimGrade >= 81) { gpa = 2.5; grade = "Satisfactory"; }
                    else if (prelimGrade >= 78) { gpa = 2.75; grade = "Fairly Satisfactory"; }
                    else if (prelimGrade >= 75) { gpa = 3.0; grade = "Passing"; }
                    else { gpa = 5.0; grade = "Failure"; }

                    // Display Results
                    percentageLabel.setText("Percentage: " + percentage);
                    gradeLabel.setText("Grade: " + grade);
                    gpaLabel.setText("GWA: " + gpa);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input! Please enter valid numbers.");
                }
            }
        });

        // Add panels to frame
        frame.add(resultPanel);
        frame.add(inputPanel);

        // Make visible
        frame.setVisible(true);
    }
}
