package com.example.demo;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class SignupServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        InputStream inputStream = getServletContext().getResourceAsStream("/signup.html");
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

        // Retrieve username, password, and staff code from the HTTP request
        String username = request.getParameter("user_id");
        String password = request.getParameter("password");

        IRepository repo = new Repository();
        Quiz quiz = new Quiz(username, password, 0);

        boolean signup = repo.signup(quiz);

        // Redirect to a success or failure page (you can customize this)
        if (signup) {
            response.sendRedirect("login");
        } else {
            response.sendRedirect("signup");
        }
    }
}
