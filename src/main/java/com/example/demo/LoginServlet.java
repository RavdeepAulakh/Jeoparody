package com.example.demo;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;
import java.io.*;
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>\n" + "<head><title>" + "Login" + "</title></head>\n" + "<body>\n"
                + "<h1 align=\"center\">" + "Login" + "</h1>\n" + "<form action=\"login\" method=\"POST\">\n"
                + "Username: <input type=\"text\" name=\"user_id\">\n" + "<br />\n"
                + "Password: <input type=\"password\" name=\"password\" />\n" + "<br />\n"
                + "<input type=\"submit\" value=\"Sign in\" />\n" + "</form>\n"
                + "</form>\n" + "</body>\n</html\n");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String errMsg = "";
        Connection con = null;
        try {
            try { Class.forName("oracle.jdbc.OracleDriver"); } catch (Exception ex) { }
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle1");
            Statement stmt2 = con.createStatement();
            ResultSet rs = stmt2.executeQuery("select * from accounts");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println("   " + username + "  " + password);
            }
            stmt2.close();
            con.close();
            System.out.println("\n\n");
        } catch(SQLException ex) {
            errMsg = errMsg + "\n--- SQLException caught ---\n";
            while (ex != null) {
                errMsg += "Message: " + ex.getMessage ();
                errMsg += "SQLState: " + ex.getSQLState ();
                errMsg += "ErrorCode: " + ex.getErrorCode ();
                ex = ex.getNextException();
                errMsg += "";
            }
        }
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");


        String title = "Logged in as: ";
        String username = request.getParameter("user_id");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);
        session.setAttribute("USER_ID", username);
        response.setStatus(302);
        response.sendRedirect("main");
    }
}
