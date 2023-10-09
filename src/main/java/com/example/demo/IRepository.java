package com.example.demo;

import com.google.gson.JsonArray;

import java.util.List;

public interface IRepository {

        void init();
        void close();
        void create(Quiz quiz);
        void delete(Quiz quiz);

        void getQuizQuestions(Quiz quiz);

        List<String> find();
        List<String> getCategories(int languageId);
        List<String> getLanguages();

        void setQuestionsArray(JsonArray questionsArray);

        JsonArray getQuestionsArray();

        void setAllOptionsArray(JsonArray allOptionsArray);

        JsonArray getAllOptionsArray();

        void setCorrectAnswersArray(JsonArray correctAnswersArray);

        JsonArray getCorrectAnswersArray();

        void setImageLocations(JsonArray imageLocations);

        JsonArray getImageLocations();
}
