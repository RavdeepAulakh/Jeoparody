 // Define the quiz questions and answers
 const questions = [
    {
        question: "This U.S. president was known for his 'Great Society' programs in the 1960s.",
        options: ["Richard Nixon", "Gerald Ford", "Lyndon B. Johnson", "John F. Kennedy"],
        correctAnswer: "C"
    },
    {
        question: "This legendary boxer was originally named Cassius Clay.",
        options: ["Joe Frazier", "Sugar Ray Leonard", "Muhammad Ali", "George Foreman"],
        correctAnswer: "C"
    },
    {
        question: "He was the British Prime Minister throughout much of the 1980s and was known for his close relationship with President Reagan.",
        options: ["Tony Blair", "John Major", "Harold Wilson", "Margaret Thatcher"],
        correctAnswer: "D"
    },
    {
        question: "This pop icon became the 'Queen of Pop' during the 1980s.",
        options: ["Whitney Houston", "Janet Jackson", "Tina Turner", "Madonna"],
        correctAnswer: "D"
    },
    {
        question: "This South African leader was released from prison in 1990 after 27 years.",
        options: ["F. W. de Klerk", "Desmond Tutu", "Nelson Mandela", "Steve Biko"],
        correctAnswer: "C"
    },
    {
        question: "This former Beatle was tragically assassinated in 1980.",
        options: ["John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr"],
        correctAnswer: "A"
    },
    {
        question: "This actress and singer played Sandy in the movie 'Grease.'",
        options: ["Debbie Reynolds", "Liza Minnelli", "Olivia Newton-John", "Barbra Streisand"],
        correctAnswer: "C"
    },
    {
        question: "This U.S. president was in office during the Moon landing in 1969.",
        options: ["Richard Nixon", "Gerald Ford", "Lyndon B. Johnson", "John F. Kennedy"],
        correctAnswer: "A"
    },
    {
        question: "This director is known for the movie 'E.T. the Extra-Terrestrial' in the 1980s.",
        options: ["George Lucas", "Steven Spielberg", "James Cameron", "Ridley Scott"],
        correctAnswer: "B"
    }

];

let currentQuestion = 0;
let score = 0;

// Function to display the current question and options
function displayQuestion() {
    const questionElement = document.getElementById("question");
    const option1Element = document.getElementById("option1");
    const option2Element = document.getElementById("option2");
    const option3Element = document.getElementById("option3");
    const option4Element = document.getElementById("option4");

    const current = questions[currentQuestion];
    questionElement.textContent = current.question;
    option1Element.textContent = current.options[0];
    option2Element.textContent = current.options[1];
    option3Element.textContent = current.options[2];
    option4Element.textContent = current.options[3];
}

// Function to check the user's answer
function checkAnswer(selectedOption) {
    const current = questions[currentQuestion];
    if (selectedOption === current.correctAnswer) {
        score++;
    }

    currentQuestion++;

    // Check if all questions have been answered
    if (currentQuestion < questions.length) {
        displayQuestion();
    } else {
        // Display the final score
        const scoreElement = document.getElementById("score");
        scoreElement.textContent = `Score: ${score}/${questions.length}`;
    }
}

// Initial display of the first question
displayQuestion();