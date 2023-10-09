package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class Quiz extends AClass {
    int catID;
    int languageID;
    String question;
    String fileName;
    String option1;
    String option2;
    String option3;
    String option4;
    boolean option1Correct;
    boolean option2Correct;
    boolean option3Correct;
    boolean option4Correct;

    Quiz(int catID, int languageID, String question, String fileName, String option1, String option2, String option3, String option4, boolean option1Correct, boolean option2Correct, boolean option3Correct, boolean option4Correct) {
        this.catID = catID;
        this.languageID = languageID;
        this.question = question;
        this.fileName = fileName;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option1Correct= option1Correct;
        this.option2Correct= option2Correct;
        this.option3Correct= option3Correct;
        this.option4Correct= option4Correct;

    }

    Quiz(int catID, int languageID) {
        this.catID = catID;
        this.languageID = languageID;

    }

    @Override
    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

