function calculateGrade() {
    // Get user inputs
    let java1 = parseFloat(document.getElementById("java1").value) || 0;
    let java2 = parseFloat(document.getElementById("java2").value) || 0;
    let js1 = parseFloat(document.getElementById("js1").value) || 0;
    let js2 = parseFloat(document.getElementById("js2").value) || 0;
    let mp1 = parseFloat(document.getElementById("mp1").value) || 0;
    let mp2 = parseFloat(document.getElementById("mp2").value) || 0;
    let mp3 = parseFloat(document.getElementById("mp3").value) || 0;
    let mp3doc = parseFloat(document.getElementById("mp3doc").value) || 0;
    let attendance = parseFloat(document.getElementById("attendance").value) || 100;

    // Prelim Exam Calculation
    let prelimExam = (0.2 * java1) + (0.3 * java2) + (0.2 * js1) + (0.3 * js2);

    // Lab Work Calculation
    let labWork = (mp1 + mp2 + mp3 + mp3doc) / 4;

    // Prelim Class Standing Calculation
    let prelimClassStanding = (0.6 * labWork) + (0.4 * attendance);

    // Prelim Grade Calculation
    let prelimGrade = (0.6 * prelimExam) + (0.4 * prelimClassStanding);
    let percentage = prelimGrade.toFixed(2);

    // Grade and GPA Conversion
    let grade, gpa;
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
    document.getElementById("percentage").textContent = "Percentage: " + percentage;
    document.getElementById("grade").textContent = "Grade: " + grade;
    document.getElementById("gpa").textContent = "GPA: " + gpa;
}