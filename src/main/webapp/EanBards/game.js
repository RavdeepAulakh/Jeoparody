const questions = [
    {
        question: "This U.S. president was known for his 'Great Society' programs in the 1960s.",
        options: ["Richard Nixon", "Gerald Ford", "Lyndon B. Johnson", "John F. Kennedy"],
        correctAnswer: "Lyndon B. Johnson"
    },
    {
        question: "This legendary boxer was originally named Cassius Clay.",
        options: ["Joe Frazier", "Sugar Ray Leonard", "Muhammad Ali", "George Foreman"],
        correctAnswer: "Muhammad Ali"
    },
    {
        question: "He was the British Prime Minister throughout much of the 1980s and was known for his close relationship with President Reagan.",
        options: ["Tony Blair", "John Major", "Harold Wilson", "Margaret Thatcher"],
        correctAnswer: "Margaret Thatcher"
    },
    {
        question: "This pop icon became the 'Queen of Pop' during the 1980s.",
        options: ["Whitney Houston", "Janet Jackson", "Tina Turner", "Madonna"],
        correctAnswer: "Madonna"
    },
    {
        question: "This South African leader was released from prison in 1990 after 27 years.",
        options: ["F. W. de Klerk", "Desmond Tutu", "Nelson Mandela", "Steve Biko"],
        correctAnswer: "Nelson Mandela"
    },
    {
        question: "This former Beatle was tragically assassinated in 1980.",
        options: ["John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr"],
        correctAnswer: "John Lennon"
    },
    {
        question: "This actress and singer played Sandy in the movie 'Grease.'",
        options: ["Debbie Reynolds", "Liza Minnelli", "Olivia Newton-John", "Barbra Streisand"],
        correctAnswer: "Olivia Newton-John"
    },
    {
        question: "This U.S. president was in office during the Moon landing in 1969.",
        options: ["Richard Nixon", "Gerald Ford", "Lyndon B. Johnson", "John F. Kennedy"],
        correctAnswer: "Richard Nixon"
    },
    {
        question: "This director is known for the movie 'E.T. the Extra-Terrestrial' in the 1980s.",
        options: ["George Lucas", "Steven Spielberg", "James Cameron", "Ridley Scott"],
        correctAnswer: "Steven Spielberg"
    },
    {
        question: "This actor played the role of James Bond multiple times during the 1970s.",
        options: ["Sean Connery", "Pierce Brosnan", "Timothy Dalton", "Roger Moore"],
        correctAnswer: "Roger Moore"
    }
];


let currentQuestion = 0;
let score = 0;

// Function to shuffle an array using the Fisher-Yates algorithm
function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

// Function to randomize the questions and answer options
function randomizeQuestions() {
    // Shuffle the order of questions
    shuffleArray(questions);

    // Shuffle the order of options within each question
    for (const question of questions) {
        shuffleArray(question.options);
    }
}

// Call this function before starting the quiz to randomize the questions and options
randomizeQuestions();

let answeredQuestions = [];

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

    UpdateSquare();
}

function UpdateSquare() {
    const emptySquares = document.querySelectorAll(".empty-square");
    for (let i = 0; i < emptySquares.length; i++) {
        if (i === currentQuestion) {
            emptySquares[i].style.backgroundColor = "blue";
        } else if (answeredQuestions[i] === 'right') {
            emptySquares[i].style.backgroundColor = "green";
        } else if (answeredQuestions[i] === 'wrong') {
            emptySquares[i].style.backgroundColor = "red";
        } else {
            emptySquares[i].style.backgroundColor = "darkgray";
        }
    }
}

// Updated checkAnswer function
function checkAnswer(selectedOption) {
    const current = questions[currentQuestion];

    // Get the correct index after shuffling the options
    const correctIndex = current.options.indexOf(current.correctAnswer);

    if (selectedOption === correctIndex) {
        score++;
        answeredQuestions[currentQuestion] = 'right';
    } else {
        answeredQuestions[currentQuestion] = 'wrong';
    }

    document.getElementById("score").textContent = "Score: " + score;
    nextQuestion();
}


function nextQuestion() {
    if (currentQuestion < questions.length - 1) {
        currentQuestion++;
        displayQuestion();
    } else {
        // What happens when there are no more questions? Maybe show a results page?
    }
}

function prevQuestion() {
    if (currentQuestion > 0) {
        currentQuestion--;
        displayQuestion();
    }
}


// Initial display of the first question
displayQuestion();