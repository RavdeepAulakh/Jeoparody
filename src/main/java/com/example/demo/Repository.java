package com.example.demo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    public static final String URL = "jdbc:mysql://localhost:3306/jeoparody";
    public static final String USER = "root";
    public static final String PASS = "Sadra1234.";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    @Override
    public void init() {

        try {
            DriverManager.registerDriver(new Driver());
            con  = DriverManager.getConnection(URL, USER, PASS);
            stmt = con.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }

    }

    @Override
    public void close() {

        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void create(Quiz quiz) {
        String gsonQuiz = quiz.serialize();
        JsonObject jsonObject = JsonParser.parseString(gsonQuiz).getAsJsonObject();

        // Extract values from the JsonObject
        int catID = jsonObject.get("catID").getAsInt();
        int languageID = jsonObject.get("languageID").getAsInt();
        String question = jsonObject.get("question").getAsString();
        String fileName = jsonObject.get("fileName").getAsString();
        String option1 = jsonObject.get("option1").getAsString();
        String option2 = jsonObject.get("option2").getAsString();
        String option3 = jsonObject.get("option3").getAsString();
        String option4 = jsonObject.get("option4").getAsString();
        boolean option1Correct = jsonObject.get("option1Correct").getAsBoolean();
        boolean option2Correct = jsonObject.get("option2Correct").getAsBoolean();
        boolean option3Correct = jsonObject.get("option3Correct").getAsBoolean();
        boolean option4Correct = jsonObject.get("option4Correct").getAsBoolean();

        init();

        try (PreparedStatement preparedStatement = con.prepareStatement(SQLCommands.getSQLInsertIntoQuestions(), Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, catID);
            preparedStatement.setInt(2, languageID);
            preparedStatement.setString(3, question);
            preparedStatement.setString(4, "/images/" + fileName);

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
                    PreparedStatement optionStatement = con.prepareStatement(SQLCommands.getSQLInsertIntoOptions());
                    optionStatement.setInt(1, questionID);
                    optionStatement.setString(2,optionText[i]);
                    optionStatement.setBoolean(3, optionValues[i]);
                    row = optionStatement.executeUpdate();
                    optionStatement.close();
                }

            }
            con.close();
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

    @Override
    public void delete(Quiz quiz) {

    }
    private JsonArray questionsArray;
    private JsonArray allOptionsArray;
    private JsonArray correctAnswersArray;
    private JsonArray imageLocations;
    @Override
    public void getQuizQuestions(Quiz quiz) {
        String gsonQuiz = quiz.serialize();
        JsonObject jsonObject = JsonParser.parseString(gsonQuiz).getAsJsonObject();

        JsonArray questionsArray = new JsonArray();
        JsonArray allOptionsArray = new JsonArray(); // Parent array to hold all option arrays
        JsonArray correctAnswersArray = new JsonArray();
        JsonArray imageLocations = new JsonArray();

        // Extract values from the JsonObject
        int catID = jsonObject.get("catID").getAsInt();
        int languageID = jsonObject.get("languageID").getAsInt();

        init();
                try {
            // Fetch all questions and options for the given language and category
            String sql = SQLCommands.getSQLQuestions();

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, languageID);
            pstmt.setInt(2, catID);

            ResultSet rs = pstmt.executeQuery();

            int currentQuestionId = -1;
            JsonArray currentOptionsArray = null; // Using JsonArray for options

            while (rs.next()) {
                int questionId = rs.getInt("question_id");
                String questionText = rs.getString("question_text");
                String optionText = rs.getString("option_text");
                boolean isCorrect = rs.getBoolean("is_correct");
                String imageLocation = rs.getString("image_location_data");

                if (currentQuestionId != questionId) {
                    if (currentOptionsArray != null) {
                        allOptionsArray.add(currentOptionsArray);
                    }

                    // New question, add it to the JsonArray
                    questionsArray.add(questionText);

                    imageLocations.add(imageLocation);

                    // Create a new JsonArray for options
                    currentOptionsArray = new JsonArray();

                    // Update the current question ID
                    currentQuestionId = questionId;
                }

                // Add the option to the current question's options JsonArray
                currentOptionsArray.add(optionText);

                if (isCorrect) {
                    correctAnswersArray.add(currentOptionsArray.size() - 1);
                }
            }

            // After the loop, add the last optionsArray if it's not null
            if (currentOptionsArray != null) {
                allOptionsArray.add(currentOptionsArray);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        System.out.println(questionsArray);
//        System.out.println(allOptionsArray);
//        System.out.println(correctAnswersArray);
//        System.out.println(imageLocations);
//
//        JsonObject responseObject = new JsonObject();
//        responseObject.add("questions", questionsArray);
//        responseObject.add("optionsList", allOptionsArray);
//        responseObject.add("correctAnswers", correctAnswersArray);
//        responseObject.add("images", imageLocations);

        setQuestionsArray(questionsArray);
        setAllOptionsArray(allOptionsArray);
        setCorrectAnswersArray(correctAnswersArray);
        setImageLocations(imageLocations);
    }

    @Override
    public List<String> find() {
        return null;
    }

    @Override
    public List<String> getCategories(int languageId) {

        List<String> categories = new ArrayList<>();

        try {

            String sql = "SELECT * FROM categories WHERE language_id = " + languageId;
            init();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");
                String categoryInfo = categoryId + "," + categoryName;
                categories.add(categoryInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return categories;
    }

    @Override
    public List<String> getLanguages() {

        List<String> languages = new ArrayList<>();

        try {


            String sql = "SELECT * FROM Languages";
            init();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int language_id = rs.getInt("language_id");
                String language_name = rs.getString("language_name");
                String languageInfo = language_id + "," + language_name;
                languages.add(languageInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return languages;
    }

    @Override
    public void setQuestionsArray(JsonArray questionsArray) {
        this.questionsArray = questionsArray;
    }
    @Override
    public JsonArray getQuestionsArray(){
       return this.questionsArray;
    }

    @Override
    public void setAllOptionsArray(JsonArray allOptionsArray) {
        this.allOptionsArray = allOptionsArray;
    }
    @Override
    public JsonArray getAllOptionsArray(){
        return this.allOptionsArray;
    }

    @Override
    public void setCorrectAnswersArray(JsonArray correctAnswersArray) {
        this.correctAnswersArray = correctAnswersArray;
    }
    @Override
    public JsonArray getCorrectAnswersArray(){
        return this.correctAnswersArray;
    }

    @Override
    public void setImageLocations(JsonArray imageLocations) {
        this.imageLocations = imageLocations;
    }
    @Override
    public JsonArray getImageLocations(){
        return this.imageLocations;
    }

}
