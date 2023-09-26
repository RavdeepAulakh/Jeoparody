window.onload = function () {
    // You can use XMLHttpRequest or fetch API, here using fetch
    fetch("/score", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById("scoreDisplay").textContent = "Your score is " + data.score + "/" + data.totalQuestions;
        })
        .catch(error => {
            console.error('Error fetching the score:', error);
            document.getElementById("scoreDisplay").textContent = "Error fetching the score.";
        });
}
