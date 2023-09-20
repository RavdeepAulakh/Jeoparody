//SOCKET CODE
let message = '';

const defaultImage = "old-tv-1.gif";
let questions = [];
let optionsList = [];
let correctAnswers = [];
let currentQuestion = 0;
let score = 0;
let answeredQuestions = [];

let urlParams = new URLSearchParams(window.location.search);
let languageId = urlParams.get('languageId');
let categoryId = urlParams.get('categoryId');

function fetchDataFromServlet(languageId, categoryId) {
  fetch(`/demo_war_exploded/game?languageId=${languageId}&categoryId=${categoryId}`)
      .then(response => response.json())
      .then(data => {
        // Use the fetched data here. For instance:
        questions = data.questions;
        optionsList = data.optionsList;
        correctAnswers = data.correctAnswers;

        // Call the function to start the quiz after fetching the data
        displayQuestion();
        generateSquares();
      })
      .catch(error => {
        console.error("Error fetching data from GameServlet:", error);
      });
}



function displayQuestion() {
  if (
      questions[currentQuestion] &&
      optionsList[currentQuestion] &&
      correctAnswers[currentQuestion]
  ) {
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
    questionElement.textContent = current;
    option1Element.textContent = optionsList[currentQuestion][0];
    option2Element.textContent = optionsList[currentQuestion][1];
    option3Element.textContent = optionsList[currentQuestion][2];
    option4Element.textContent = optionsList[currentQuestion][3];

    updateImage();
  }
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

function checkAnswer(selectedOption) {
  if (
      questions[currentQuestion] &&
      correctAnswers[currentQuestion] !== undefined
  ) {
    const current = questions[currentQuestion];
    const correctIndex = correctAnswers[currentQuestion];

    // Disable all options after one is clicked
    for (let i = 1; i <= 4; i++) {
      document.getElementById("option" + i).style.pointerEvents = "none";
    }

    if (selectedOption === correctIndex) {
      score++;
      answeredQuestions[currentQuestion] = "right";
      document.getElementById("option" + (selectedOption + 1)).style.color =
          "green";
      //SOCKET CODE
      message = "Correct answer."
      webSocket.send(message);
    } else {
      answeredQuestions[currentQuestion] = "wrong";
      document.getElementById("option" + (selectedOption + 1)).style.color =
          "red";
      document.getElementById("option" + (correctIndex + 1)).style.color =
          "green";
      //SOCKET CODE
      message = "Incorrect answer."
      webSocket.send(message);

    }

    document.getElementById("score").textContent = "Score: " + score;
    updateImage();
  }
}

function nextQuestion() {
  if (currentQuestion < questions.length - 1) {
    currentQuestion++;
    displayQuestion();
    UpdateSquare();
  } else {
    // What happens when there are no more questions? Maybe show a results page?
  }
}

function prevQuestion() {
  if (currentQuestion > 0) {
    currentQuestion--;
    displayQuestion();
    UpdateSquare();
  }
}

fetchDataFromServlet(languageId, categoryId);
