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

public class CategoryServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jeoparody";
    private static final String DB_USER = "root";

    private static final String DB_PASSWORD = "gyattrizz37";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Include the HTML content from category.html
        request.getRequestDispatcher("/category.html").include(request, response);

        out.println("<html><head><title>Select Category</title></head><body>");

        // Get the language ID from the request, assuming it's passed as a parameter
        String languageIdParam = request.getParameter("languageId");
        if (languageIdParam == null || languageIdParam.isEmpty()) {
            out.println("Language ID is missing in the request.");
            out.println("</body></html>");
            return;
        }

        int languageId = Integer.parseInt(languageIdParam);

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create and execute the SQL query to fetch categories by language ID
            stmt = conn.createStatement();
            String sql = "SELECT * FROM categories WHERE language_id = " + languageId;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");

                // Create a button for each category
                out.println("<div class=\"button\" onclick=\"selectCategory(" + categoryId + ")\">" + categoryName + "</div>");
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
        out.println("<script>");
        out.println("function selectCategory(categoryId) {");
        out.println("  var languageId = " + languageId + ";"); // Get the languageId from the current page
        out.println("  // Redirect to the GameServlet with the selected category and language IDs as parameters");
        out.println("window.location.href = 'display?languageId=' + languageId + '&categoryId=' + categoryId;");
        out.println("}");
        out.println("</script>");

        out.println("</body></html>");
    }
}
