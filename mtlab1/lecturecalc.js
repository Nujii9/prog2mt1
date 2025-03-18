function calculateGrade() {
    const examScore = parseFloat(document.getElementById("examScore").value);
    const essay = parseFloat(document.getElementById("essay").value);
    const pvm = parseFloat(document.getElementById("pvm").value);
    const javaBasics = parseFloat(document.getElementById("javaBasics").value);
    const introJS = parseFloat(document.getElementById("introJS").value);

    // Validate Input
    const inputs = [examScore, essay, pvm, javaBasics, introJS];
    const maxValues = [100, 100, 60, 40, 40];
    const fields = ['examScore', 'essay', 'pvm', 'javaBasics', 'introJS'];

    let hasError = false;
    inputs.forEach((value, index) => {
      const inputField = document.getElementById(fields[index]);
      if (isNaN(value) || value < 0 || value > maxValues[index]) {
        inputField.classList.add('error');
        hasError = true;
      } else {
        inputField.classList.remove('error');
      }
    });

    if (hasError) {
      alert("Invalid input detected! Please ensure all scores are within their respective limits.");
      return;
    }

    // Attendance
    const attended = document.querySelectorAll('.attendance:checked').length;
    const absences = 5 - attended;
    const attendanceScore = 100 - (10 * absences);

    // Normalize and Calculate
    const normalizedPVM = (pvm / 60) * 100;
    const normalizedJavaBasics = (javaBasics / 40) * 100;
    const normalizedIntroJS = (introJS / 40) * 100;
    const quizAverage = (essay + normalizedPVM + normalizedJavaBasics + normalizedIntroJS) / 4;

    const classStanding = (0.6 * quizAverage) + (0.4 * attendanceScore);
    const finalGrade = (0.6 * examScore) + (0.4 * classStanding);

    // Determine Grade and GPA
    const gradeMapping = [
      [99, 1.0, "Excellent"],
      [96, 1.25, "Superior"],
      [93, 1.5, "Very Good"],
      [90, 1.75, "Good"],
      [87, 2.0, "Meritorious"],
      [84, 2.25, "Very Satisfactory"],
      [81, 2.5, "Satisfactory"],
      [78, 2.75, "Fairly Satisfactory"],
      [75, 3.0, "Passing"],
      [0, 5.0, "Failure"]
    ];

    const [gpa, grade] = gradeMapping.find(([min]) => finalGrade >= min).slice(1);

    // Display Results
    document.getElementById("percentage").textContent = `Percentage: ${finalGrade.toFixed(2)}%`;
    document.getElementById("grade").textContent = `Grade: ${grade}`;
    document.getElementById("gpa").textContent = `GPA: ${gpa}`;
  }

  function toggleFormula() {
    const formulaBox = document.getElementById("formulaBox");
    formulaBox.style.display = formulaBox.style.display === "block" ? "none" : "block";
  }

  function resetFields() {
    location.reload();
  }