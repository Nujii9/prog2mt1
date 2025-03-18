import java.awt.*;
import javax.swing.*;

public class LabPrelimCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prelim Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 750);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(40, 44, 52));

        // Panel for displaying results
        JPanel resultPanel = new JPanel(new GridLayout(5, 1));
        resultPanel.setBackground(new Color(60, 63, 65));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel resultLabel = new JLabel("Result", JLabel.CENTER);
        JLabel percentageLabel = new JLabel("Percentage: 0", JLabel.CENTER);
        JLabel gradeLabel = new JLabel("Grade: N/A", JLabel.CENTER);
        JLabel gpaLabel = new JLabel("GPA: N/A", JLabel.CENTER);
        JLabel formulaLabel = new JLabel("<html>Formula: (60% Exam) + (40% Class Standing)<br>Class Standing = (60% Lab Work + 40% Attendance)<br>Attendance = 100% - (10% × Absences)</html>", JLabel.CENTER);

        Font resultFont = new Font("Arial", Font.BOLD, 24);
        percentageLabel.setFont(resultFont);
        gradeLabel.setFont(resultFont);
        gpaLabel.setFont(resultFont);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 28));
        formulaLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        resultLabel.setForeground(Color.WHITE);
        percentageLabel.setForeground(Color.WHITE);
        gradeLabel.setForeground(Color.WHITE);
        gpaLabel.setForeground(Color.WHITE);
        formulaLabel.setForeground(Color.WHITE);

        resultPanel.add(resultLabel);
        resultPanel.add(percentageLabel);
        resultPanel.add(gradeLabel);
        resultPanel.add(gpaLabel);
        resultPanel.add(formulaLabel);

        // Panel for inputs
        JPanel inputPanel = new JPanel(new GridLayout(11, 2, 15, 15));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(50, 54, 60));

        JTextField java1Field = new JTextField();
        JTextField java2Field = new JTextField();
        JTextField js1Field = new JTextField();
        JTextField js2Field = new JTextField();
        JTextField mp1Field = new JTextField();
        JTextField mp2Field = new JTextField();
        JTextField mp3Field = new JTextField();
        JTextField mp3DocuField = new JTextField();

        JLabel attendanceLabel = new JLabel("Attendance:");
        attendanceLabel.setForeground(Color.WHITE);
        attendanceLabel.setFont(new Font("Arial", Font.BOLD, 18));

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
            checkBox.setFont(new Font("Arial", Font.BOLD, 16));
            checkBox.setForeground(Color.WHITE);
            checkBox.setBackground(new Color(50, 54, 60));
            attendancePanel.add(checkBox);
        }

        JButton calculateButton = new JButton("Show Result");
        JButton resetButton = new JButton("Reset");

        Dimension buttonSize = new Dimension(200, 60);
        calculateButton.setPreferredSize(buttonSize);
        resetButton.setPreferredSize(buttonSize);

        for (JButton button : new JButton[]{calculateButton, resetButton}) {
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBackground(new Color(100, 181, 246));
            button.setForeground(Color.BLACK);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            button.setFocusPainted(false);
        }

        // Labels
        Font labelFont = new Font("Arial", Font.BOLD, 18);

        JLabel[] labels = {
            new JLabel("Java 1:", JLabel.RIGHT),
            new JLabel("Java 2:", JLabel.RIGHT),
            new JLabel("JS 1:", JLabel.RIGHT),
            new JLabel("JS 2:", JLabel.RIGHT),
            new JLabel("MP1:", JLabel.RIGHT),
            new JLabel("MP2:", JLabel.RIGHT),
            new JLabel("MP3:", JLabel.RIGHT),
            new JLabel("MP3 Docu:", JLabel.RIGHT)
        };

        for (JLabel label : labels) {
            label.setForeground(Color.WHITE);
            label.setFont(labelFont);
        }

        inputPanel.add(labels[0]); inputPanel.add(java1Field);
        inputPanel.add(labels[1]); inputPanel.add(java2Field);
        inputPanel.add(labels[2]); inputPanel.add(js1Field);
        inputPanel.add(labels[3]); inputPanel.add(js2Field);
        inputPanel.add(labels[4]); inputPanel.add(mp1Field);
        inputPanel.add(labels[5]); inputPanel.add(mp2Field);
        inputPanel.add(labels[6]); inputPanel.add(mp3Field);
        inputPanel.add(labels[7]); inputPanel.add(mp3DocuField);
        inputPanel.add(attendanceLabel); inputPanel.add(attendancePanel);
        inputPanel.add(calculateButton); inputPanel.add(resetButton);

// Calculate button functionality
calculateButton.addActionListener(e -> {
    try {
        double java1 = Double.parseDouble(java1Field.getText());
        double java2 = Double.parseDouble(java2Field.getText());
        double js1 = Double.parseDouble(js1Field.getText());
        double js2 = Double.parseDouble(js2Field.getText());
        double mp1 = Double.parseDouble(mp1Field.getText());
        double mp2 = Double.parseDouble(mp2Field.getText());
        double mp3 = Double.parseDouble(mp3Field.getText());
        double mp3Docu = Double.parseDouble(mp3DocuField.getText());

        // Validate that scores are between 0 and 100
        if (java1 > 100 || java2 > 100 || js1 > 100 || js2 > 100 ||
            mp1 > 100 || mp2 > 100 || mp3 > 100 || mp3Docu > 100 ||
            java1 < 0 || java2 < 0 || js1 < 0 || js2 < 0 ||
            mp1 < 0 || mp2 < 0 || mp3 < 0 || mp3Docu < 0) {
            JOptionPane.showMessageDialog(frame, "Scores must be between 0 and 100!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution
        }

        double prelimExam = (0.2 * java1) + (0.3 * java2) + (0.2 * js1) + (0.3 * js2);
        double labWork = (mp1 + mp2 + mp3 + mp3Docu) / 4;

        int attended = 0;
        for (JCheckBox checkBox : attendanceBoxes) {
            if (checkBox.isSelected()) attended++;
        }
        int absences = 5 - attended;

        if (absences >= 3) {
            percentageLabel.setText("Percentage: 0");
            gradeLabel.setText("Grade: Failed (Due to Absences)");
            gpaLabel.setText("GPA: 5.0");
            return;
        }
        double attendance = 100 - (10 * (5 - attended));
        double classStanding = (0.6 * labWork) + (0.4 * attendance);
        double prelimGrade = (0.6 * prelimExam) + (0.4 * classStanding);

        // Convert to GWA
        double gpa;
        String grade;
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

        percentageLabel.setText("Percentage: " + Math.round(prelimGrade * 100.0) / 100.0);
        gradeLabel.setText("Grade: " + grade);
        gpaLabel.setText("GPA: " + gpa);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(frame, "Enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
    }
});


        frame.add(resultPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
