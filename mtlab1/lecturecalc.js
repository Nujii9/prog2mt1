document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("calculateButton").addEventListener("click", function () {
        let examScore = parseFloat(document.getElementById("examScore").value);
        let essay = parseFloat(document.getElementById("essay").value);
        let pwm = parseFloat(document.getElementById("pwm").value);
        let javaBasics = parseFloat(document.getElementById("javaBasics").value);
        let introJS = parseFloat(document.getElementById("introJS").value);
        let attendance = parseFloat(document.getElementById("attendance").value);

        if (isNaN(examScore) || isNaN(essay) || isNaN(pwm) || isNaN(javaBasics) || isNaN(introJS) || isNaN(attendance)) {
            alert("Invalid Input! Please enter numbers.");
            return;
        }

        let quizAverage = (essay + pwm + javaBasics + introJS) / 4;
        let classStanding = (0.6 * quizAverage) + (0.4 * attendance);
        let prelimGrade = (0.6 * examScore) + (0.4 * classStanding);
        let percentage = prelimGrade.toFixed(2);
        
        let gpa, grade;
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

        document.getElementById("percentage").innerText = percentage;
        document.getElementById("grade").innerText = grade;
        document.getElementById("gpa").innerText = gpa;
    });
});