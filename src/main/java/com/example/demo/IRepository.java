package com.example.demo;

import java.util.List;

public interface IRepository {

        void init();
        void close();
        void create(int catID, int languageID, String question, String fileName, String option1, String option2, String option3, String option4, boolean option1Correct, boolean option2Correct, boolean option3Correct, boolean option4Correct);
        void update(Quiz quiz);
        void delete(Quiz quiz);
        List<String> find();
        List<String> getCategories(int languageId);
        List<String> getLanguages();


}
