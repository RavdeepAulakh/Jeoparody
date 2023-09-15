package com.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "GameServlet", value = "/game-servlet")
public class GameServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String category = request.getParameter("category");
        String questionText = "";
        String[] options = new String[4];

        switch (category) {
            case "FAMOUS PEOPLE":
                questionText = "This legendary boxer was originally named Cassius Clay.";
                options = new String[] {"Joe Frazier", "Sugar Ray Leonard", "Muhammad Ali", "George Foreman"};
                break;
            case "HISTORIC EVENTS":
                questionText = "In 1969, humans first set foot on the Moon during this mission.";
                options = new String[] {"Apollo 10", "Apollo 13", "Apollo 11", "Apollo 15"};
                break;
        }

        request.setAttribute("questionText", questionText);
        request.setAttribute("options", options);
        request.getRequestDispatcher("/displayQuestion.html").forward(request, response);
    }
}
