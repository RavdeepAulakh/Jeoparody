package com.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.*;
import java.sql.*;
import java.text.*;

@MultipartConfig
public class InputQuestionServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("USER_ID") != null) {
            // The session is valid, and the user is logged in
            String username = (String) session.getAttribute("USER_ID");
            // Your code for handling the authenticated user here
            try (InputStream inputStream = getServletContext().getResourceAsStream("/submit.html")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                PrintWriter out = response.getWriter(); {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        out.println(line);
                    }
                }
                reader.close();
            }
        } else {
            // No valid session exists or the user is not logged in
            // You can handle this case as needed (e.g., redirect to the login page)
            response.sendRedirect("login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("image");
        String imageCaption = request.getParameter("imageCaption");
        String category = request.getParameter("categories");
        String language = request.getParameter("languages");
        String question = request.getParameter("question");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String option4 = request.getParameter("option4");
        boolean option1Correct = Boolean.parseBoolean(request.getParameter("option1_correct"));
        boolean option2Correct = Boolean.parseBoolean(request.getParameter("option2_correct"));
        boolean option3Correct = Boolean.parseBoolean(request.getParameter("option3_correct"));
        boolean option4Correct = Boolean.parseBoolean(request.getParameter("option4_correct"));
        String uploadDirectory = getServletContext().getRealPath("/") + "images/";
        String fileName = filePart.getSubmittedFileName();
        String filePath = uploadDirectory + fileName;
        try (OutputStream out = new FileOutputStream(filePath)) {
            InputStream fileContent = filePart.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // Handle the exception (e.g., log or display an error message)
            e.printStackTrace();
        }
        if(question.equals("")) question = "No Question";
        System.out.println(">>>>>" + imageCaption + category + question + option1 + option2 + option3 + option4 + fileName);

        try (Connection con = DatabaseConnection.getConnection()) {
            int catID = 1;
            int languageID = 1;
            switch (category) {
                case "famouspeople":
                    catID = 1;
                    break;
                case "history":
                    catID = 2;
                    break;
                case "carmodels":
                    catID = 3;
                    break;
                case "music":
                    catID = 4;
                    break;
                case "fashion":
                    catID = 5;
                    break;
            }
            switch (language) {
                case "english":
                    languageID = 1;
                    break;
                case "spanish":
                    languageID = 2;
                    break;
                case "french":
                    languageID = 3;
                    break;
            }

            try (PreparedStatement preparedStatement = con.prepareStatement(SQLCommands.getSQLpreparedStatement(), Statement.RETURN_GENERATED_KEYS)) {
                //            UUID uuid = UUID.randomUUID();
                // if image is null (meaning its url or neither)
                // if url is null (meaning its image)
                // else set image and url to null
                preparedStatement.setInt(1, catID);
                preparedStatement.setInt(2, languageID);
                preparedStatement.setString(3, question);
                preparedStatement.setString(4, "/images/" + fileName);
//            preparedStatement.setBinaryStream(7, filePart.getInputStream());
                int row = preparedStatement.executeUpdate();
                int questionID = -1;
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    questionID = rs.getInt(1);
                }
                preparedStatement.close();

                if (questionID != -1) {
                    String[] optionText = { option1, option2, option3, option4 };
                    boolean[] optionValues = {option1Correct, option2Correct, option3Correct, option4Correct};
                    for (int i = 0; i < optionText.length; i++) {
                        PreparedStatement optionStatement = con.prepareStatement(SQLCommands.getSQLOptionStatement());
                        optionStatement.setInt(1, questionID);
                        optionStatement.setString(2,optionText[i]);
                        optionStatement.setBoolean(3, optionValues[i]);
                        row = optionStatement.executeUpdate();
                        optionStatement.close();
                    }
                    response.sendRedirect("list");
                }
                //con.close();
            } catch(SQLException ex) {
                while (ex != null) {
                    System.out.println("Message: " + ex.getMessage ());
                    System.out.println("SQLState: " + ex.getSQLState ());
                    System.out.println("ErrorCode: " + ex.getErrorCode ());
                    ex = ex.getNextException();
                    System.out.println("");
                }
            }

        } catch(SQLException ex) {
            while (ex != null) {
                System.out.println("Message: " + ex.getMessage ());
                System.out.println("SQLState: " + ex.getSQLState ());
                System.out.println("ErrorCode: " + ex.getErrorCode ());
                ex = ex.getNextException();
                System.out.println("");
            }
        }
    }
}
