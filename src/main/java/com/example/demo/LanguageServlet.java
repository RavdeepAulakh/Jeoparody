package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LanguageServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Include the HTML content from language.html
        request.getRequestDispatcher("/language.html").include(request, response);


        List<String> languagesInfos = SQLCommands.getLanguages();

        for (String languagesInfo : languagesInfos) {
            String[] parts = languagesInfo.split(",");
            if (parts.length == 2) {
                int language_id = Integer.parseInt(parts[0]);
                String language_name = parts[1];

                    // Create a button for each category
                out.println("<div class=\"button\" onclick=\"selectLanguage(" + language_id + ")\">" + language_name + "</div>");
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
