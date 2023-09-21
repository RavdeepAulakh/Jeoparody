
// Set up variables to insert the languageId/categoryId into the url string

// Create a socketURL depending on the users language/category selection
const socketURL = "ws://localhost:8081/demo_war_exploded/game";
console.log(socketURL);

// Establish a websocket connection to the server endpoint
const webSocket = new WebSocket(socketURL);

// Function for handling incoming WebSocket messages
const socketFeed = document.getElementById('socket-feed');
webSocket.onmessage = function(e) {
    const message = e.data;
    const socketItem = document.createElement('p');

    // Append the message to the new P element
    socketItem.appendChild(document.createTextNode(message));

    // Append the p element with message text to the socket feed
    socketFeed.appendChild(socketItem);
}