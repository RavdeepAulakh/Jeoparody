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

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GameServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jeoparody";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        session.removeAttribute("questions");
        session.removeAttribute("optionsList");
        session.removeAttribute("correctAnswers");

        // Retrieve language and category IDs from the request
        int languageId = Integer.parseInt(request.getParameter("languageId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        // Retrieve questions and options based on language and category
        List<String> questions = new ArrayList<>();
        List<List<String>> optionsList = new ArrayList<>();
        List<Integer> correctAnswers = new ArrayList<>();

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
            List<String> currentOptions = new ArrayList<>();

            while (rs.next()) {
                int questionId = rs.getInt("question_id");
                String questionText = rs.getString("question_text");
                String optionText = rs.getString("option_text");
                boolean isCorrect = rs.getBoolean("is_correct");

                System.out.println(questionId);
                System.out.println(questionText);
                System.out.println(optionText);
                System.out.println(isCorrect);

                if (currentQuestionId != questionId) {
                    // New question, add it to the list
                    questions.add(questionText);

                    // Create a new list for options
                    currentOptions = new ArrayList<>();
                    optionsList.add(currentOptions);

                    // Update the current question ID
                    currentQuestionId = questionId;
                }

                // Add the option to the current question's options list
                currentOptions.add(optionText);

                if (isCorrect) {
                    // Store the index of the correct answer relative to the current options list
                    correctAnswers.add(currentOptions.size() - 1);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Store the questions, options, and correct answers in the session
        session.setAttribute("questions", questions);
        session.setAttribute("optionsList", optionsList);
        session.setAttribute("correctAnswers", correctAnswers);

        System.out.println(questions);
        System.out.println(optionsList);
        System.out.println(correctAnswers);

        // Serve the game HTML page
        request.getRequestDispatcher("/game.html").include(request, response);
    }
}
