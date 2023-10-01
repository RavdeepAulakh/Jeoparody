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
Dony by: Gathrean Dela Cruz and Bardia Timouri

### Files
- `game.js`, `game.html`, `game.css`
    - Front-end Client
- `LanguageServlet` and `CategoryServlet` 
    - First and second step before starting the game, respectively
    - Redirects to GameServlet when both language ID and category ID as sent as parameters
- `GameServlet`
    1. Receives language and cateogry IDs, and fetches appropriates questions from database
    2. Organize data into JSON arrays and sends the JSON response back to client
    3. Handles AJAX requests for the quiz and provide data
    4. Then presents the questions and options to the user in the game frontend
 
# Contributions List
- Aulakh, Ravdeep
    - []
- Dela Cruz, Gathrean (Ean)
    - Game Logic and Frontend
- Park, Hyuk
    - []
- Ondrik, Will
    - Web Sockets
- Solkoski, Laurie
    - []
- Timouri, Bardia
    - Game Logic and Frontend

