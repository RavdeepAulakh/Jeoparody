const defaultImage = "old-tv-1.gif";
const questions = [
  {
    question: "This legendary boxer was originally named Cassius Clay.",
    options: [
      "Joe Frazier",
      "Sugar Ray Leonard",
      "Muhammad Ali",
      "George Foreman",
    ],
    correctAnswer: "Muhammad Ali",
    image: "Gallery/FPQ1.jpg",
  },
  {
    question: "This actress starred in the movie 'Breakfast at Tiffany's.'",
    options: [
      "Marilyn Monroe",
      "Audrey Hepburn",
      "Elizabeth Taylor",
      "Grace Kelly",
    ],
    correctAnswer: "Audrey Hepburn",
    image: "Gallery/FPQ2.jpg",
  },
  {
    question: "This actress and singer played Sandy in the movie 'Grease.'",
    options: [
      "Debbie Reynolds",
      "Liza Minnelli",
      "Olivia Newton-John",
      "Barbra Streisand",
    ],
    correctAnswer: "Olivia Newton-John",
    image: "Gallery/FPQ3.jpg",
  },
  {
    question:
      "This director is known for the movie 'E.T. the Extra-Terrestrial' in the 1980s.",
    options: [
      "George Lucas",
      "Steven Spielberg",
      "James Cameron",
      "Ridley Scott",
    ],
    correctAnswer: "Steven Spielberg",
    image: "Gallery/FPQ4.jpg",
  },
  {
    question: "He was known as the 'King of Pop.'",
    options: ["Elvis Presley", "Michael Jackson", "Prince", "Freddie Mercury"],
    correctAnswer: "Michael Jackson",
    image: "Gallery/FPQ5.jpg",
  },
  {
    question: "This actress played Princess Leia in the 'Star Wars' series.",
    options: [
      "Natalie Portman",
      "Carrie Fisher",
      "Sigourney Weaver",
      "Linda Hamilton",
    ],
    correctAnswer: "Carrie Fisher",
    image: "Gallery/FPQ6.jpg",
  },
  {
    question:
      "This legendary guitarist played the national anthem at Woodstock in 1969.",
    options: ["Eric Clapton", "Jimmy Page", "Jimi Hendrix", "Keith Richards"],
    correctAnswer: "Jimi Hendrix",
    image: "Gallery/FPQ7.jpg",
  },
  {
    question:
      "He became famous for playing the role of 'The Terminator' in the 1984 movie.",
    options: [
      "Jean-Claude Van Damme",
      "Bruce Willis",
      "Sylvester Stallone",
      "Arnold Schwarzenegger",
    ],
    correctAnswer: "Arnold Schwarzenegger",
    image: "Gallery/FPQ8.jpg",
  },
  {
    question: "This pop icon became the 'Queen of Pop' during the 1980s.",
    options: ["Whitney Houston", "Janet Jackson", "Tina Turner", "Madonna"],
    correctAnswer: "Madonna",
    image: "Gallery/FPQ9.jpg",
  },
  {
    question:
      "This South African leader was released from prison in 1990 after 27 years.",
    options: ["F. W. de Klerk", "Desmond Tutu", "Nelson Mandela", "Steve Biko"],
    correctAnswer: "Nelson Mandela",
    image: "Gallery/FPQ10.jpg",
  },
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

  // Reset colors
  option1Element.style.color = "white";
  option2Element.style.color = "white";
  option3Element.style.color = "white";
  option4Element.style.color = "white";

  // Reset clickability of options
  option1Element.style.pointerEvents = "auto";
  option2Element.style.pointerEvents = "auto";
  option3Element.style.pointerEvents = "auto";
  option4Element.style.pointerEvents = "auto";

  const current = questions[currentQuestion];
  questionElement.textContent = current.question;
  option1Element.textContent = current.options[0];
  option2Element.textContent = current.options[1];
  option3Element.textContent = current.options[2];
  option4Element.textContent = current.options[3];

  UpdateSquare();
  updateImage();
}

function updateImage() {
  const imageElement = document.getElementById("media");

  if (answeredQuestions[currentQuestion]) {
    imageElement.src = questions[currentQuestion].image;
  } else {
    imageElement.src = defaultImage;
  }
}

function generateSquares() {
  const squaresContainer = document.querySelector(".empty-squares");

  // Clear the squaresContainer first to ensure there are no squares
  squaresContainer.innerHTML = "";

  for (let i = 0; i < questions.length; i++) {
    const squareElement = document.createElement("div");
    squareElement.classList.add("empty-square");
    squareElement.id = "square" + (i + 1);
    squaresContainer.appendChild(squareElement);
  }
}

function UpdateSquare() {
  const emptySquares = document.querySelectorAll(".empty-square");
  for (let i = 0; i < emptySquares.length; i++) {
    if (i === currentQuestion) {
      emptySquares[i].style.backgroundColor = "blue";
    } else if (answeredQuestions[i] === "right") {
      emptySquares[i].style.backgroundColor = "green";
    } else if (answeredQuestions[i] === "wrong") {
      emptySquares[i].style.backgroundColor = "red";
    } else {
      emptySquares[i].style.backgroundColor = "darkgray";
    }
  }
}

// Updated checkAnswer function
function checkAnswer(selectedOption) {
  const current = questions[currentQuestion];
  const correctIndex = current.options.indexOf(current.correctAnswer);

  // Disable all options after one is clicked
  for (let i = 1; i <= 4; i++) {
    document.getElementById("option" + i).style.pointerEvents = "none";
  }

  if (selectedOption === correctIndex) {
    score++;
    answeredQuestions[currentQuestion] = "right";
    document.getElementById("option" + (selectedOption + 1)).style.color =
      "green";
  } else {
    answeredQuestions[currentQuestion] = "wrong";
    document.getElementById("option" + (selectedOption + 1)).style.color =
      "red";
    document.getElementById("option" + (correctIndex + 1)).style.color =
      "green";
  }

  document.getElementById("score").textContent = "Score: " + score;
  updateImage();
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

generateSquares();
// Initial display of the first question
displayQuestion();
