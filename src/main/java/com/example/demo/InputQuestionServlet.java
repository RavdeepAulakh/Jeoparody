package com.example.demo;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.sql.*;
import java.io.*;
import java.time.LocalDate;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.StringBuilder;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.text.*;
import java.nio.*;

@MultipartConfig
public class InputQuestionServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("USER_ID") != null) {
            // The session is valid, and the user is logged in
            String username = (String) session.getAttribute("USER_ID");
            // Your code for handling the authenticated user here
        } else {
            // No valid session exists or the user is not logged in
            // You can handle this case as needed (e.g., redirect to the login page)
            response.sendRedirect("login");
        }
        InputStream inputStream = getServletContext().getResourceAsStream("/submit.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            out.println(line);
        }

        reader.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("image");
        String imageCaption = request.getParameter("imageCaption");
        String category = request.getParameter("categories");
        String question = request.getParameter("question");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String option4 = request.getParameter("option4");
        boolean option1Correct = Boolean.parseBoolean(request.getParameter("option1_correct"));
        boolean option2Correct = Boolean.parseBoolean(request.getParameter("option2_correct"));
        boolean option3Correct = Boolean.parseBoolean(request.getParameter("option3_correct"));
        boolean option4Correct = Boolean.parseBoolean(request.getParameter("option4_correct"));
        String video = request.getParameter("video");
        String audio = request.getParameter("audio");
        String fileName = filePart.getSubmittedFileName();
        if(question.equals("")) question = "No Question";
        System.out.println(">>>>>" + imageCaption + category + question + option1 + option2 + option3 + option4 + fileName);
        Connection con = null;
        try {Class.forName("com.mysql.cj.jdbc.Driver"); } catch (Exception ex) {
            System.out.println("Message: " + ex.getMessage ());
            return;
        }
        try {
            int catID = 1;
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeoparody", "root", "131599Jalopy!");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Questions (category_id, question_text, video_url, audio_url, image_location_data, image_caption) VALUES (?,?,?,?,?,?)");
//            UUID uuid = UUID.randomUUID();
            // if image is null (meaning its url or neither)
            // if url is null (meaning its image)
            // else set image and url to null
            preparedStatement.setInt(1, catID);
            preparedStatement.setString(2, question);
            preparedStatement.setString(3, video);
            preparedStatement.setString(4, audio);
            preparedStatement.setString(5, "image/" + fileName);
            preparedStatement.setString(6, imageCaption);
//            preparedStatement.setBinaryStream(7, filePart.getInputStream());
            int row = preparedStatement.executeUpdate();
            preparedStatement.close();
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
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeoparody", "root", "131599Jalopy!");
            String[] optionText = { option1, option2, option3, option4 };
            boolean[] optionValues = {option1Correct, option2Correct, option3Correct, option4Correct};
            for (int i = 0; i < optionText.length; i++) {
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Options (option_text, is_correct) VALUES (?,?)");
                preparedStatement.setString(1,optionText[i]);
                preparedStatement.setBoolean(2, optionValues[i]);
                int row = preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            //con.close();
        } catch(SQLException ex2) {
            while (ex2 != null) {
                System.out.println("Message: " + ex2.getMessage ());
                System.out.println("SQLState: " + ex2.getSQLState ());
                System.out.println("ErrorCode: " + ex2.getErrorCode ());
                ex2 = ex2.getNextException();
                System.out.println("");
            }
        }
//        byte bArr[] = null;
//        UUID sid = null;
//        try {
//            Statement stmt2 = con.createStatement( );
//            ResultSet rs = stmt2.executeQuery("SELECT id, question, contentpath, content FROM trivias");
//            rs.next();
//            byte[] raw = rs.getBytes(1);
//            sid  = asUuid(raw);
//            question = rs.getString(2);
//            String contentPath = rs.getString(3);
//            Blob b = rs.getBlob(4);
//            bArr = b.getBytes(1, (int) b.length());
//            stmt2.close();
//            con.close();
//        } catch(SQLException ex3) {
//            while (ex3 != null) {
//                System.out.println("Message: " + ex3.getMessage ());
//                System.out.println("SQLState: " + ex3.getSQLState ());
//                System.out.println("ErrorCode: " + ex3.getErrorCode ());
//                ex3 = ex3.getNextException();
//                System.out.println("");
//            }
//        }
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        String page = "<!DOCTYPE html><html><body>" +
//                "<img src=\"data:image/jpeg;base64," +
//                Base64.getEncoder().encodeToString(bArr) + "\"" +
//                " width=\"500\" height=\"500\"></img>" +
//                "</body></html>";
//        System.out.println(page);
//        out.println(page);
    }
//    public static byte[] asBytes(UUID uuid) {
//        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
//        bb.putLong(uuid.getMostSignificantBits());
//        bb.putLong(uuid.getLeastSignificantBits());
//        return bb.array();
//    }
//    public static UUID asUuid(byte[] bytes) {
//        ByteBuffer bb = ByteBuffer.wrap(bytes);
//        long firstLong = bb.getLong();
//        long secondLong = bb.getLong();
//        return new UUID(firstLong, secondLong);
//    }
}
