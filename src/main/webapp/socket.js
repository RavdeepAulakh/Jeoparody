

// Select the multiplayer buton by ID
let MP = document.getElementById('multiPlayer')


function initializeSocket() {

    // Create a socketURL depending on the users language/category selection
    // the '/game' at the end of the socketURL is the socket servlet endpoint
    const socketURL = "ws://localhost:8081/demo_war_exploded/game";
    console.log(socketURL);

    // Establish a websocket connection to the server endpoint
    const webSocket = new WebSocket(socketURL);

    // Function for handling incoming WebSocket messages
    const socketFeed = document.getElementById('socket-feed');
    webSocket.onmessage = function (e) {
        const message = e.data;
        const socketItem = document.createElement('p');

        // Append the message to the new P element
        socketItem.appendChild(document.createTextNode(message));

        // Append the p element with message text to the socket feed
        //socketFeed.appendChild(socketItem);
        socketFeed.insertBefore(socketItem, socketFeed.firstChild);
    }
}



// Add event listener for multiplayer button
    // When triggered, it will start the socket
MP.addEventListener('click', function() {
    initializeSocket();
});