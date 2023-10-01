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
