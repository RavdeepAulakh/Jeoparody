
package com.example.demo;
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
//            System.out.println(username);
            try (InputStream inputStream = getServletContext().getResourceAsStream("/questions.html")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                PrintWriter out = response.getWriter();
                String line;
                boolean insideQuestionsDiv = false;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().equals("<div id=\"questions-list\">")) {
                        out.println(line);
                        insideQuestionsDiv = true;

                        // Generate the questions here
                        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeoparody", "root", "")) {
                            String query = "SELECT question_id, question_text FROM Questions";
                            try (PreparedStatement stmt = con.prepareStatement(query)) {
                                ResultSet rs = stmt.executeQuery();
                                while (rs.next()) {
                                    int questionId = rs.getInt("question_id");
                                    String questionText = rs.getString("question_text");
//                                    System.out.println(">>>>>" + questionId + questionText);
                                    out.println("<div class=\"question-item\">");
                                    out.println("<p>" + questionText + "</p>");
                                    out.println("<form method='post' action='list'>");
                                    out.println("<input type='hidden' name='delete_id' value='" + questionId + "'>");
                                    out.println("<input type='submit' value='Delete'>");
                                    out.println("</form>");
                                    out.println("</div>");
                                }
                            }
                        } catch (SQLException ex) {
                            while (ex != null) {
                                System.out.println("Message: " + ex.getMessage ());
                                System.out.println("SQLState: " + ex.getSQLState ());
                                System.out.println("ErrorCode: " + ex.getErrorCode ());
                                ex = ex.getNextException();
                                System.out.println("");
                            }
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


//        int deleteID = Integer.parseInt(request.getParameter("delete_id"));

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeoparody", "root", "131599Jalopy!");
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Questions WHERE question_id = ?")) {

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

