package com.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;


@MultipartConfig
public class InputQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("USER_ID") != null) {
            String username = (String) session.getAttribute("USER_ID");
            try (InputStream inputStream = getServletContext().getResourceAsStream("/submit.html")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                PrintWriter out = response.getWriter();
                {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        out.println(line);
                    }
                }
                reader.close();
            }
        } else {
            response.sendRedirect("login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
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

            try (OutputStream fileOut = new FileOutputStream(filePath)) {
                InputStream fileContent = filePart.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    fileOut.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(500);
                out.print("{\"status\": \"error\", \"message\": \"Failed to save the image.\"}");
                out.flush();
                return;
            }

            if (question.equals("")) question = "No Question";

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

            IRepository repo = new Repository();
            Quiz quiz = new Quiz(catID, languageID, question, fileName, option1, option2, option3, option4, option1Correct, option2Correct, option3Correct, option4Correct);
            repo.create(quiz);

            response.setStatus(200);
            out.print("{\"status\": \"success\", \"message\": \"Question submitted successfully!\"}");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(400);
            out.print("{\"status\": \"error\", \"message\": \"Failed to submit question.\"}");
            out.flush();
        }
    }
}

