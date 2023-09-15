package com.example.demo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/category-servlet")
public class CategoryServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // For the sake of simplicity, we'll use a static list.
        // You can fetch this from a database or other sources in a real-world application.
        List<String> categories = Arrays.asList("Famous People", "Car Models", "History Moments", "Fashion", "Music");

        // Manually generate the JSON string
        StringBuilder json = new StringBuilder("[");
        for(int i = 0; i < categories.size(); i++) {
            json.append("\"").append(categories.get(i)).append("\"");
            if(i < categories.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());

    }
}
