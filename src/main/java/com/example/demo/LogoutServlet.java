package com.example.demo;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        Cookie userLoggedInCookie = new Cookie("userLoggedIn", "false");
        userLoggedInCookie.setMaxAge(0); // Remove the cookie
        response.addCookie(userLoggedInCookie);


        response.sendRedirect("index.html");
    }
}

