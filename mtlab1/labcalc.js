function calculateGrade() {
    const fields = ["java1", "java2", "js1", "js2", "mp1", "mp2", "mp3", "mp3Docu"];
    let invalidInputs = false;

    let scores = fields.map(field => {
      const input = document.getElementById(field);
      const score = parseFloat(input.value) || 0;
      if (score < 0 || score > 100) {
        input.classList.add('error');
        invalidInputs = true;
      } else {
        input.classList.remove('error');
      }
      return score;
    });

    if (invalidInputs) {
      alert("Invalid input detected. Scores must be between 0 and 100.");
      return;
    }

    // Calculate grades
    let prelimExam = (0.2 * scores[0]) + (0.3 * scores[1]) + (0.2 * scores[2]) + (0.3 * scores[3]);
    let labWork = (scores[4] + scores[5] + scores[6] + scores[7]) / 4;

    let attended = Array.from({ length: 5 }, (_, i) => document.getElementById(`att${i + 1}`).checked).filter(Boolean).length;
    let absences = 5 - attended;
    let attendance = 100 - (10 * absences);

    let classStanding = (0.6 * labWork) + (0.4 * attendance);
    let prelimGrade = (0.6 * prelimExam) + (0.4 * classStanding);

    let gpa, grade;
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

    document.getElementById("percentage").innerText = `Percentage: ${prelimGrade.toFixed(2)}`;
    document.getElementById("grade").innerText = `Grade: ${grade}`;
    document.getElementById("gpa").innerText = `GPA: ${gpa}`;
  }

  function showFormula() {
    document.getElementById("formulaBox").style.display = "block";
  }

  function resetFields() {
    location.reload();
  }