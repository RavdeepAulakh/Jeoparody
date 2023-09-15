package com.example.demo;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;
import java.io.*;
import org.mindrot.jbcrypt.BCrypt;

public class SignupServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        InputStream inputStream = getServletContext().getResourceAsStream("/signup.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            out.println(line);
        }

        reader.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String errMsg = "";
        Connection con = null;

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception ex) { }
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeoparody", "root", "hockey04");

            // Retrieve username, password, and staff code from the HTTP request
            String username = request.getParameter("user_id");
            String password = request.getParameter("password");
            String staffCode = request.getParameter("staff_code");

            // Check if the staff code is correct
            if ("12345".equals(staffCode)) {
                // Staff code is correct, proceed with account creation
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                System.out.println("Hashed Password " + hashedPassword);
                System.out.println("Staff Code: " + staffCode);

                // Create an SQL INSERT statement to add the new user
                String insertSQL = "INSERT INTO accounts (username, password) VALUES (?, ?)";

                // Use PreparedStatement to safely insert the values into the database
                PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, hashedPassword);

                // Execute the INSERT statement to add the new user
                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    // User added successfully
                    System.out.println("User " + username + " signed up successfully!");
                } else {
                    // User signup failed
                    System.out.println("Failed to sign up user " + username);
                }

                preparedStatement.close();
            } else {
                // Staff code is incorrect
                errMsg = "Invalid staff code. Account creation is not allowed.";
            }

            con.close();
        } catch (SQLException ex) {
            errMsg = errMsg + "\n--- SQLException caught ---\n";
            while (ex != null) {
                errMsg += "Message: " + ex.getMessage();
                errMsg += "SQLState: " + ex.getSQLState();
                errMsg += "ErrorCode: " + ex.getErrorCode();
                ex = ex.getNextException();
                errMsg += "";
            }
        }

        // Redirect to a success or failure page (you can customize this)
        if (errMsg.isEmpty()) {
            response.sendRedirect("login");
        } else {
            response.sendRedirect("signup");
        }
    }
}
