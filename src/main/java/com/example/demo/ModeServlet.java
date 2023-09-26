package com.example.demo;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ModeServlet", value = "/gameMode")
public class ModeServlet extends HttpServlet {
    String gameMode;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the response content type to plain text
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Read the message from the request as a string
        gameMode = request.getReader().readLine();
        System.out.println("Received game mode: " + gameMode); // Debug print statement

        // You can send a response back to the frontend if needed
        PrintWriter out = response.getWriter();
        out.println("Received game mode: " + gameMode);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("gameMode", gameMode);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
        System.out.println("response sent to frontend");
    }
}
