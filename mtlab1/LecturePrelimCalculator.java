import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LecturePrelimCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prelim Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(40, 44, 52));

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(4, 1));
        resultPanel.setBackground(new Color(60, 63, 65));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel resultLabel = new JLabel("Result", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 25));
        resultLabel.setForeground(Color.WHITE);

        JLabel percentageLabel = new JLabel("Percentage: 0", JLabel.CENTER);
        JLabel gradeLabel = new JLabel("Grade: N/A", JLabel.CENTER);
        JLabel gpaLabel = new JLabel("GPA: N/A", JLabel.CENTER);

        percentageLabel.setForeground(Color.WHITE);
        gradeLabel.setForeground(Color.WHITE);
        gpaLabel.setForeground(Color.WHITE);

        resultPanel.add(resultLabel);
        resultPanel.add(percentageLabel);
        resultPanel.add(gradeLabel);
        resultPanel.add(gpaLabel);

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        inputPanel.setBackground(new Color(50, 54, 60));

        JTextField examScoreField = new JTextField();
        JTextField essayField = new JTextField();
        JTextField pvmField = new JTextField();
        JTextField javaBasicsField = new JTextField();
        JTextField introJSField = new JTextField();

        JLabel attendanceLabel = new JLabel("Attendance Dates:");
        attendanceLabel.setForeground(Color.WHITE);
        JPanel attendancePanel = new JPanel(new GridLayout(1, 5, 5, 5));
        attendancePanel.setBackground(new Color(50, 54, 60));

        JCheckBox[] attendanceBoxes = {
            new JCheckBox("Jan 20"),
            new JCheckBox("Jan 27"),
            new JCheckBox("Feb 3"),
            new JCheckBox("Feb 10"),
            new JCheckBox("Feb 17")
        };

        for (JCheckBox checkBox : attendanceBoxes) {
            checkBox.setForeground(Color.WHITE);
            checkBox.setBackground(new Color(50, 54, 60));
            attendancePanel.add(checkBox);
        }

        JButton calculateButton = new JButton("Show Result");
        JButton resetButton = new JButton("Reset");

        for (JButton button : new JButton[]{calculateButton, resetButton}) {
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBackground(new Color(100, 181, 246));
            button.setForeground(Color.BLACK);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            button.setFocusPainted(false);
        }

        inputPanel.add(new JLabel("Prelim Exam Score:", JLabel.RIGHT)).setForeground(Color.WHITE);
        inputPanel.add(examScoreField);
        inputPanel.add(new JLabel("Essay Score:", JLabel.RIGHT)).setForeground(Color.WHITE);
        inputPanel.add(essayField);
        inputPanel.add(new JLabel("PVM Score (max 60):", JLabel.RIGHT)).setForeground(Color.WHITE);
        inputPanel.add(pvmField);
        inputPanel.add(new JLabel("Java Basics Score (max 40):", JLabel.RIGHT)).setForeground(Color.WHITE);
        inputPanel.add(javaBasicsField);
        inputPanel.add(new JLabel("Intro to JS Score (max 40):", JLabel.RIGHT)).setForeground(Color.WHITE);
        inputPanel.add(introJSField);
        inputPanel.add(attendanceLabel);
        inputPanel.add(attendancePanel);
        inputPanel.add(calculateButton);
        inputPanel.add(resetButton);

        JPanel formulaPanel = new JPanel();
        formulaPanel.setBackground(new Color(50, 54, 60));
        JLabel formulaLabel = new JLabel("<html><div style='text-align: center; color: white;'><b>Formula:</b><br>" +
                "Prelim Grade = (0.6 × Prelim Exam) + (0.4 × Prelim Class Standing)<br>" +
                "Prelim Class Standing = (0.6 × Prelim Quizzes) + (0.4 × Attendance)<br>" +
                "Prelim Quizzes = (Essay + Scaled PVM + Scaled Java Basics + Scaled Intro to JS) ÷ 4<br>" +
                "Attendance = 100% - (10% × Absences)</div></html>", JLabel.CENTER);
        formulaPanel.add(formulaLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double examScore = Double.parseDouble(examScoreField.getText());
                    double essay = Double.parseDouble(essayField.getText());
                    double pvm = Double.parseDouble(pvmField.getText());
                    double javaBasics = Double.parseDouble(javaBasicsField.getText());
                    double introJS = Double.parseDouble(introJSField.getText());

                    if (examScore > 100 || essay > 100 || pvm > 60 || javaBasics > 40 || introJS > 40) {
                        JOptionPane.showMessageDialog(frame, "Scores exceed the maximum limit!", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int attended = 0;
                    for (JCheckBox checkBox : attendanceBoxes) {
                        if (checkBox.isSelected()) attended++;
                    }
                    int absences = 5 - attended;

                    if (absences >= 3) {
                        percentageLabel.setText("Percentage: 0");
                        gradeLabel.setText("Grade: Failed (Due to Absences)");
                        gpaLabel.setText("GPA: 5.0");
                        resultPanel.setBackground(new Color(255, 69, 0));
                        return;
                    }

                    double attendancePercentage = 100 - (10 * absences);
                    double scaledPVM = (pvm / 60) * 100;
                    double scaledJavaBasics = (javaBasics / 40) * 100;
                    double scaledIntroJS = (introJS / 40) * 100;

                    double quizAverage = (essay + scaledPVM + scaledJavaBasics + scaledIntroJS) / 4;
                    double classStanding = (0.6 * quizAverage) + (0.4 * attendancePercentage);
                    double prelimGrade = (0.6 * examScore) + (0.4 * classStanding);

                    double percentage = Math.min(100, Math.round(prelimGrade * 100.0) / 100.0);
                    double gpa;
                    String grade;
                    if (percentage >= 99) { gpa = 1.0; grade = "Excellent"; }
                    else if (percentage >= 96) { gpa = 1.25; grade = "Superior"; }
                    else if (percentage >= 93) { gpa = 1.5; grade = "Very Good"; }
                    else if (percentage >= 90) { gpa = 1.75; grade = "Good"; }
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

        frame.add(resultPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(formulaPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
