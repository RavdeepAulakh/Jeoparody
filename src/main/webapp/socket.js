// Fetching data from the server endpoint '/gameMode' (within the ModeServlet)
    // Previously sent an gameMode variable to the servlet
        // Now we're retrieving it with async functions as there is a slight delay when getting the value
let gameMode;
async function fetchMode() {
    try {
        const response = await fetch('/demo_war_exploded/gameMode');
        if(!response.ok) {
            throw new Error('failed to fetch');
        }

        const data = await response.json();
        gameMode = data.gameMode;
        console.log(gameMode)
        return gameMode
    } catch (error) {
        console.error("Catch error:" + error);
    }
}


// Made these variables global so that they can be accessed by game.js
    // if they're defined as constants within the function, they wont be accessible
let socketURL;
let webSocket;
let socketFeed;
let message;
let socketItem;
async function initializeSocket() {
    // Wait for the gameMode value from the fetchMode function
    gameMode = await fetchMode();
    console.log("socket func received data" + gameMode);

    if (gameMode == 'multiPlayer'){
        // Create a socketURL depending on the users language/category selection
    // the '/game' at the end of the socketURL is the socket servlet endpoint
         socketURL = "ws://localhost:8081/demo_war_exploded/game";

    // Establish a websocket connection to the server endpoint
         webSocket = new WebSocket(socketURL);

    // Function for handling incoming WebSocket messages
         socketFeed = document.getElementById('socket-feed');
        webSocket.onmessage = function (e) {
             message = e.data;
             socketItem = document.createElement('p');

            // Append the message to the new P element
            socketItem.appendChild(document.createTextNode(message));

            // Append the p element with message text to the socket feed
            //socketFeed.appendChild(socketItem);
            socketFeed.insertBefore(socketItem, socketFeed.firstChild);

        }
    }
}
initializeSocket();