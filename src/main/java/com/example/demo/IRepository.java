package com.example.demo;

import com.google.gson.JsonArray;

import java.util.List;

public interface IRepository {

        void init();
        void close();
        void create(Quiz quiz);
        void update();
        void delete(Quiz quiz);
        void getQuizQuestions(Quiz quiz);
        List<String> find();
        List<String> getCategories(Quiz quiz);
        List<String> getLanguages();
        boolean login(Quiz quiz);
        boolean signup(Quiz quiz);
        void setQuestionsArray(JsonArray questionsArray);
        JsonArray getQuestionsArray();
        void setAllOptionsArray(JsonArray allOptionsArray);
        JsonArray getAllOptionsArray();
        void setCorrectAnswersArray(JsonArray correctAnswersArray);
        JsonArray getCorrectAnswersArray();
        void setImageLocations(JsonArray imageLocations);
        JsonArray getImageLocations();
}
