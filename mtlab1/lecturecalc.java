import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class lecturecalc {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prog 2 - Lec Prelim Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 350);
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
        JLabel gpaLabel = new JLabel("GPA: N/A", JLabel.CENTER);
        
        percentageLabel.setForeground(Color.WHITE);
        gradeLabel.setForeground(Color.WHITE);
        gpaLabel.setForeground(Color.WHITE);

        percentageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gpaLabel.setFont(new Font("Arial", Font.BOLD, 18));        

        resultPanel.add(resultLabel);
        resultPanel.add(percentageLabel);
        resultPanel.add(gradeLabel);
        resultPanel.add(gpaLabel);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 1));

        JTextField examScoreField = new JTextField();
        JTextField essayField = new JTextField();
        JTextField pwmField = new JTextField();
        JTextField javaBasicsField = new JTextField();
        JTextField introJSField = new JTextField();
        JTextField attendanceField = new JTextField();
        JButton calculateButton = new JButton("Show Result");

        inputPanel.add(new JLabel("Prelim Exam Score"));
        inputPanel.add(examScoreField);
        inputPanel.add(new JLabel("Essay Score"));
        inputPanel.add(essayField);
        inputPanel.add(new JLabel("PVM Score"));
        inputPanel.add(pwmField);
        inputPanel.add(new JLabel("Java Basics Score"));
        inputPanel.add(javaBasicsField);
        inputPanel.add(new JLabel("Intro to JS Score"));
        inputPanel.add(introJSField);
        inputPanel.add(new JLabel("Attendance (%)"));
        inputPanel.add(attendanceField);
        inputPanel.add(calculateButton);

        // Button Action Listener
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double examScore = Double.parseDouble(examScoreField.getText());
                    double essay = Double.parseDouble(essayField.getText());
                    double pwm = Double.parseDouble(pwmField.getText());
                    double javaBasics = Double.parseDouble(javaBasicsField.getText());
                    double introJS = Double.parseDouble(introJSField.getText());
                    double attendance = Double.parseDouble(attendanceField.getText());

                    double quizAverage = (essay + pwm + javaBasics + introJS) / 4;
                    double classStanding = (0.6 * quizAverage) + (0.4 * attendance);
                    double prelimGrade = (0.6 * examScore) + (0.4 * classStanding);
                    double percentage = Math.round(prelimGrade * 100.0) / 100.0;
                    
                    double gpa;
                    String grade;
                    if (percentage >= 99) { gpa = 1.0; grade = "Excellent"; }
                    else if (percentage >= 96) { gpa = 1.25; grade = "Superior"; }
                    else if (percentage >= 93) { gpa = 1.5; grade = "Very Good"; }
                    else if (percentage >= 90) { gpa = 1.75; grade = "Good"; }
                    else if (percentage >= 87) { gpa = 2.0; grade = "Meritorious"; }
                    else if (percentage >= 84) { gpa = 2.25; grade = "Very Satisfactory"; }
                    else if (percentage >= 81) { gpa = 2.5; grade = "Satisfactory"; }
                    else if (percentage >= 78) { gpa = 2.75; grade = "Fairly Satisfactory"; }
                    else if (percentage >= 75) { gpa = 3.0; grade = "Passing"; }
                    else { gpa = 5.0; grade = "Failure"; }

                    percentageLabel.setText("Percentage: " + percentage);
                    gradeLabel.setText("Grade: " + grade);
                    gpaLabel.setText("GPA: " + gpa);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input! Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(resultPanel);
        frame.add(inputPanel);
        frame.setVisible(true);
    }
}
