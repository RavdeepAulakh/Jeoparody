# Prerequisites 
<b>Tomcat</b>
<b>Mysql and Mysql workbench (optional)
# How to Run The Code

1. Pull the project from GitHub
2. On intellij create a new run configuration for tomcat
![img.png](readmeImages/img.png)
3. First create a new Scheme called jeoparody
4. Run the JEOPARODY_DATABASE.sql file in your workbench to create the database needed
5. Add your mysql password and username into the servlets (Category, Game, InputQuestions, Language, Login, Signup)
6. Run the tomcat server and a window with the homepage of the application should
automatically be opened

#
# Web Sockets
Done by: Will Ondrik

## Socket code locations:
### Files:
- `socket.js` (front-end socket connection)
- `SocketServlet.java` (back-end socket configurations)
- `mode.js` (posts the selected game type to ModeServlet.java)
- `ModeServlet.java` (stores the game type and informs the front-end)
- `game.js` (sends socket messages to the SocketServlet)
    - lines 1-3
    - `checkAnswer` function
        - lines 139-141
        - lines 148-150
        - lines 153-156
        - lines 160-162
     
# Game Logic
Done by: Gathrean Dela Cruz and Bardia Timouri

### Files
- `game.js`, `game.html`, `game.css`, `index.html`, `index.css`, `index.js`
    - Created front end for home page (Bardia)
    - Front-end Client (Ean)
    - Functions to show the user what question they are on, got wrong, got right (Bardia)
    - Functions to go next, and prev (Ean)
    - Functions to keep track of the score (Ean)
    - Function to update images and questions and options from the JSON array (Bardia)
    - Made use of AJAX to dynamically update questions to the (Bardia)
    - Used JS fetch function to retrieve questions and images in array format from GameServlet (Bardia)
- `LanguageServlet` and `CategoryServlet` 
    - First and second step before starting the game, respectively (Ean)
    - Redirects to GameServlet when both language ID and category ID as sent as parameters (Bardia)
- `GameServlet`
    1. Receives language and cateogry IDs, and fetches appropriates questions from database (Bardia)
    2. Organize data into JSON arrays and sends the JSON response back to client (Ean)
    3. Handles AJAX requests for the quiz and provide data (Bardia)
    4. Then presents the questions and options to the user in the game frontend (Ean)
- 'DisplayServlet'
    - Used doGet to display the game screen (Bardia)
 
# Contributions List
- Aulakh, Ravdeep
    - []
- Dela Cruz, Gathrean (Ean)
    - Game Logic and Frontend
    - Allowing the frontend to recieve questions from the backend
    - Game design
- Park, Hyuk
    - []
- Ondrik, Will
    - Web Sockets
- Solkoski, Laurie
    - []
- Timouri, Bardia
    - Game Logic and Frontend
    - Allowing the frontend to recieve questions from the backend
    - Allowing the frontend to display images gathered from backend
    - Gathering questions and answers in english
    - Mid fidelity wireframe for home screen
    - Game design
      

