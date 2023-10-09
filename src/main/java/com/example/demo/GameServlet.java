package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.JsonArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

public class GameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Retrieve language and category IDs from the request
        int languageId = Integer.parseInt(request.getParameter("languageId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        JsonArray questionsArray;
        JsonArray allOptionsArray;
        JsonArray correctAnswersArray;
        JsonArray imageLocations;

        IRepository repo = new Repository();
        Quiz quiz = new Quiz(categoryId, languageId);
        repo.getQuizQuestions(quiz);

        questionsArray = repo.getQuestionsArray();
        allOptionsArray = repo.getAllOptionsArray();
        correctAnswersArray = repo.getCorrectAnswersArray();
        imageLocations = repo.getImageLocations();

        JsonObject responseObject = new JsonObject();
        responseObject.add("questions", questionsArray);
        responseObject.add("optionsList", allOptionsArray);
        responseObject.add("correctAnswers", correctAnswersArray);
        responseObject.add("images", imageLocations);

        out.print(responseObject);
        out.flush();
    }
}
