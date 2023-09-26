package com.example.demo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class ScoreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JsonObject responseObject = new JsonObject();

        HttpSession session = request.getSession();
        Integer userScore = (Integer) session.getAttribute("userScore");
        JsonArray questionsArray = (JsonArray) session.getAttribute("questions");

        if (userScore != null && questionsArray != null) {
            responseObject.addProperty("score", userScore);
            responseObject.addProperty("totalQuestions", questionsArray.size());
        } else {
            responseObject.addProperty("error", "Score or Questions are not available.");
        }

        out.print(responseObject.toString());
        out.flush();
    }
}
