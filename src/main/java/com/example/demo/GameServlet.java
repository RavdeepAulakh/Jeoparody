package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.JsonArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import java.util.stream.Stream;

public class GameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Retrieve language and category IDs from the request
        int languageId = Integer.parseInt(request.getParameter("languageId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        /**
         * Stream API is used to iteratively add each array to the responseObject JsonObject.
         * Instead of creating separate arrays and then adding them to the responseObject,
         * instead use the Stream API to construct JSON response = implements functional programming way.
         */
//        JsonArray questionsArray;
//        JsonArray allOptionsArray;
//        JsonArray correctAnswersArray;
//        JsonArray imageLocations;

        IRepository repo = new Repository();
        Quiz quiz = new Quiz(categoryId, languageId);
        repo.getQuizQuestions(quiz);

        /**
         * Use of Stream and Functional Programming to compose
         * the JSON response.
         */
        JsonObject responseObject = Stream.of(
                        new Object[][] {
                                { "questions", repo.getQuestionsArray() },
                                { "optionsList", repo.getAllOptionsArray() },
                                { "correctAnswers", repo.getCorrectAnswersArray() },
                                { "images", repo.getImageLocations() }
                        })
                .collect(JsonObject::new,
                        (jObj, objArr) -> jObj.add((String) objArr[0], (JsonArray) objArr[1]),
                        (jObj1, jObj2) -> {});

//
//        questionsArray = repo.getQuestionsArray();
//        allOptionsArray = repo.getAllOptionsArray();
//        correctAnswersArray = repo.getCorrectAnswersArray();
//        imageLocations = repo.getImageLocations();
////
//        JsonObject responseObject = new JsonObject();
//        responseObject.add("questions", repo.getQuestionsArray());
//        responseObject.add("optionsList", repo.getAllOptionsArray());
//        responseObject.add("correctAnswers", repo.getCorrectAnswersArray());
//        responseObject.add("images", repo.getImageLocations());

        out.print(responseObject);
        out.flush();
    }
}
