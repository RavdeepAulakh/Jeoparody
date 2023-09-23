package com.example.demo;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;
import java.io.*;
import org.mindrot.jbcrypt.BCrypt; // Import the BCrypt library

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        InputStream inputStream = getServletContext().getResourceAsStream("/login.html");
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeoparody", "root", "");

            // Retrieve username and plaintext password from the HTTP request
            String username = request.getParameter("user_id");
            String enteredPassword = request.getParameter("password");

            // Retrieve the hashed password from the database
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM accounts WHERE username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String dbHashedPassword = resultSet.getString("password");
                // Check if the entered password matches the stored hashed password using BCrypt
                if (BCrypt.checkpw(enteredPassword, dbHashedPassword)) {
                    // Successful login
                    HttpSession session = request.getSession(true);
                    session.setAttribute("USER_ID", username);
                    Cookie userLoggedInCookie = new Cookie("userLoggedIn", "true");
                    response.addCookie(userLoggedInCookie);
                    response.sendRedirect("index.html"); // Redirect to the home page
                } else {
                    // Failed login
                    errMsg = "Invalid username or password.";
                    // You can handle failed login here (e.g., display an error message)
                }
            } else {
                // User not found
                errMsg = "User not found.";
                // You can handle this case as needed (e.g., display an error message)
            }

            preparedStatement.close();
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            // Handle exceptions
            ex.printStackTrace();
            errMsg = "An error occurred during login.";
            // You can handle errors here (e.g., display an error message)
        }

        // If login failed or an error occurred, display an error message
        PrintWriter out = response.getWriter();
        out.println("<html><body><p>" + errMsg + "</p></body></html>");
    }
}

