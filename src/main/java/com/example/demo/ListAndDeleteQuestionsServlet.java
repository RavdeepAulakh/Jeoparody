
package com.example.demo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.*;
import java.sql.*;

public class ListAndDeleteQuestionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("USER_ID") != null) {
            // The session is valid, and the user is logged in
            String username = (String) session.getAttribute("USER_ID");
            try (InputStream inputStream = getServletContext().getResourceAsStream("/questions.html")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                PrintWriter out = response.getWriter();
                String line;
                boolean insideQuestionsDiv = false;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().equals("<div id=\"questions-list\">")) {
                        out.println(line);
                        insideQuestionsDiv = true;

                        Repository repo = new Repository();
                        repo.update();
                        JsonArray questionsAndIds = repo.getQuestionsArray();

                        for (JsonElement element : questionsAndIds) {
                            JsonObject questionObject = element.getAsJsonObject();
                            int questionId = questionObject.get("questionId").getAsInt();
                            String questionText = questionObject.get("questionText").getAsString();
                            out.println("<div class=\"question-item\">");
                            out.println("<p>" + questionText + "</p>");
                            out.println("<form method='post' action='list'>");
                            out.println("<input type='hidden' name='delete_id' value='" + questionId + "'>");
                            out.println("<input type='submit' value='Delete'>");
                            out.println("</form>");
                            out.println("</div>");
                        }
                    } else if (insideQuestionsDiv && line.trim().equals("</div>")) {
                        insideQuestionsDiv = false;
                    }

                    if (!insideQuestionsDiv) {
                        out.println(line);
                    }
                }

                reader.close();
            }

        } else {
            // No valid session exists or the user is not logged in
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deleteIdStr = request.getParameter("delete_id");
        if (deleteIdStr == null || deleteIdStr.isEmpty()) {
            System.out.println(deleteIdStr);
            // Handle the error, maybe redirect to an error page or show an error message
            return;
        }
        int deleteID = Integer.parseInt(deleteIdStr);


        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLCommands.getSQLDeleteFromQuestions())) {

            preparedStatement.setInt(1, deleteID);
            preparedStatement.executeUpdate();

        } catch(SQLException ex) {
            handleSQLException(ex);
        }

        // Redirect back to the same page to refresh the list
        response.sendRedirect("list"); // Replace 'path_to_this_servlet' with the actual path
    }

    private void handleSQLException(SQLException ex) {
        while (ex != null) {
            System.out.println("Message: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            ex = ex.getNextException();
            System.out.println("");
        }
    }
}

