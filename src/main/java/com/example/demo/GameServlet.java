package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.json.Json;
import com.google.gson.JsonObject;

public class GameServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jeoparody";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        session.removeAttribute("questions");
        session.removeAttribute("optionsList");
        session.removeAttribute("correctAnswers");

        // Retrieve language and category IDs from the request
        int languageId = Integer.parseInt(request.getParameter("languageId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        // Retrieve questions and options based on language and category

        JsonArray questionsArray = new JsonArray();
        JsonArray allOptionsArray = new JsonArray(); // Parent array to hold all option arrays
        JsonArray correctAnswersArray = new JsonArray();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Fetch all questions and options for the given language and category
            String sql = "SELECT Q.question_id, Q.question_text, O.option_text, O.is_correct " +
                    "FROM Questions Q " +
                    "INNER JOIN Options O ON Q.question_id = O.question_id " +
                    "WHERE Q.language_id = ? AND Q.category_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, languageId);
            pstmt.setInt(2, categoryId);

            ResultSet rs = pstmt.executeQuery();

            int currentQuestionId = -1;
            JsonArray currentOptionsArray = null; // Using JsonArray for options

            while (rs.next()) {
                int questionId = rs.getInt("question_id");
                String questionText = rs.getString("question_text");
                String optionText = rs.getString("option_text");
                boolean isCorrect = rs.getBoolean("is_correct");

                if (currentQuestionId != questionId) {
                    if (currentOptionsArray != null) {
                        allOptionsArray.add(currentOptionsArray);
                    }

                    // New question, add it to the JsonArray
                    questionsArray.add(questionText);

                    // Create a new JsonArray for options
                    currentOptionsArray = new JsonArray();

                    // Update the current question ID
                    currentQuestionId = questionId;
                }

                // Add the option to the current question's options JsonArray
                currentOptionsArray.add(optionText);

                if (isCorrect) {
                    correctAnswersArray.add(currentOptionsArray.size() - 1);
                }
            }

            // After the loop, add the last optionsArray if it's not null
            if (currentOptionsArray != null) {
                allOptionsArray.add(currentOptionsArray);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

// Store the questions, options, and correct answers in the session
        session.setAttribute("questions", questionsArray);
        session.setAttribute("optionsList", allOptionsArray); // Store the parent array
        session.setAttribute("correctAnswers", correctAnswersArray);

        System.out.println(questionsArray);
        System.out.println(allOptionsArray);
        System.out.println(correctAnswersArray);

        JsonObject responseObject = new JsonObject();
        responseObject.add("questions", questionsArray);
        responseObject.add("optionsList", allOptionsArray);
        responseObject.add("correctAnswers", correctAnswersArray);

        out.print(responseObject.toString());
        out.flush();
    }
}
