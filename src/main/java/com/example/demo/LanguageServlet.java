package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LanguageServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jeoparody";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Include the HTML content from language.html
        request.getRequestDispatcher("/language.html").include(request, response);

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create and execute the SQL query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Languages";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("language_id");
                String name = rs.getString("language_name");

                // Create a button for each language
                out.println("<div class=\"button\" onclick=\"selectLanguage(" + id + ")\">" + name + "</div>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources in the reverse order of their creation
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // JavaScript function to handle button click
//        out.println("<script src=\"language.js\"></script>");
        out.println("<script>");
        out.println("function selectLanguage(languageId) {");
        out.println("  // Redirect to the CategoryServlet with the selected language ID as a parameter");
        out.println("  window.location.href = 'categories?languageId=' + languageId;");
        out.println("}");
        out.println("</script>");
    }
}
