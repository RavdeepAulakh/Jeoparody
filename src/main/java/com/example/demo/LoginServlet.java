package com.example.demo;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        InputStream inputStream = getServletContext().getResourceAsStream("/login.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            out.println(line);
        }

        reader.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String errMsg = "";

        // Retrieve username and plaintext password from the HTTP request
        String username = request.getParameter("user_id");
        String enteredPassword = request.getParameter("password");

        IRepository repo = new Repository();
        Quiz quiz = new Quiz(username, enteredPassword);

       boolean loggedin = repo.login(quiz);

        if(loggedin) {
            // Successful login
            HttpSession session = request.getSession(true);
            session.setAttribute("USER_ID", username);
            Cookie userLoggedInCookie = new Cookie("userLoggedIn", "true");
            response.addCookie(userLoggedInCookie);
            response.sendRedirect("index.html"); // Redirect to the home page
        }

        // If login failed or an error occurred, display an error message
        PrintWriter out = response.getWriter();
        out.println("<html><body><p>" + errMsg + "</p></body></html>");
    }
}

