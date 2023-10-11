package com.example.demo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.mysql.jdbc.Driver;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    public static final String URL = "jdbc:mysql://localhost:3306/jeoparody";
    public static final String USER = "root";
    public static final String PASS = "Stuccosong88";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private JsonArray questionsArray;
    private JsonArray allOptionsArray;
    private JsonArray correctAnswersArray;
    private JsonArray imageLocations;

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
    public void update() {
        init();
        String query = SQLCommands.getSQLQuestionAndText();
        JsonArray jsonArray = new JsonArray();

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int questionId = rs.getInt("question_id");
                String questionText = rs.getString("question_text");

                // Create a JsonObject for each row
                JsonObject jsonObject = new JsonObject();
                jsonObject.add("questionId", new JsonPrimitive(questionId));
                jsonObject.add("questionText", new JsonPrimitive(questionText));

                // Add the JsonObject to the JsonArray
                jsonArray.add(jsonObject);
            }
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("Message: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        }

        // Now jsonArray contains all the question IDs and question texts
        setQuestionsArray(jsonArray);
    }

    @Override
    public void delete(Quiz quiz) {
        init();
        String gsonQuiz = quiz.serialize();
        JsonObject jsonObject = JsonParser.parseString(gsonQuiz).getAsJsonObject();

        int deleteID = jsonObject.get("deleteQuiz").getAsInt();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLCommands.getSQLDeleteFromQuestions())) {

            preparedStatement.setInt(1, deleteID);
            preparedStatement.executeUpdate();

        } catch(SQLException ex) {
            while (ex != null) {
                System.out.println("Message: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        }
    }

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
    public List<String> getCategories(Quiz quiz) {

        List<String> categories = new ArrayList<>();

        String gsonQuiz = quiz.serialize();
        JsonObject jsonObject = JsonParser.parseString(gsonQuiz).getAsJsonObject();

        int languageId = jsonObject.get("languageIDCategory").getAsInt();

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
    public JsonArray getImageLocations() {
        return this.imageLocations;
    }

    @Override
    public boolean login(Quiz quiz) {

            init();
            String gsonQuiz = quiz.serialize();
            JsonObject jsonObject = JsonParser.parseString(gsonQuiz).getAsJsonObject();

            String username = jsonObject.get("username").getAsString();
            String enteredPassword = jsonObject.get("enteredPassword").getAsString();

            try {

                String errMsg = "";

                // Retrieve the hashed password from the database
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM accounts WHERE username=?");
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String dbHashedPassword = resultSet.getString("password");
                    // Check if the entered password matches the stored hashed password using BCrypt
                    if (BCrypt.checkpw(enteredPassword, dbHashedPassword)) {
                        return true;
                    } else {
                        // Failed login
                        errMsg = "Invalid username or password.";
                        // You can handle failed login here (e.g., display an error message)
                    }
                } else {
                    // User not found
                    errMsg = "User not found.";
                    // You can handle this case as needed (e.g., display an error message)
                }

                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                // Handle exceptions
                ex.printStackTrace();
                // You can handle errors here (e.g., display an error message)
            }


            return false;

    }

    @Override
    public boolean signup(Quiz quiz) {

        init();

        String errMsg = "";

        String gsonQuiz = quiz.serialize();
        JsonObject jsonObject = JsonParser.parseString(gsonQuiz).getAsJsonObject();

        String username = jsonObject.get("usernameSignup").getAsString();
        String password = jsonObject.get("password").getAsString();

        try {


            // Staff code is correct, proceed with account creation
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            System.out.println("Hashed Password " + hashedPassword);

            // Create an SQL INSERT statement to add the new user
            String insertSQL = "INSERT INTO accounts (username, password) VALUES (?, ?)";

            // Use PreparedStatement to safely insert the values into the database
            PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);

            // Execute the INSERT statement to add the new user
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                // User added successfully
                System.out.println("User " + username + " signed up successfully!");
                return true;
            } else {
                // User signup failed
                System.out.println("Failed to sign up user " + username);
            }

            preparedStatement.close();


            con.close();
        } catch (SQLException ex) {
            errMsg = errMsg + "\n--- SQLException caught ---\n";
            while (ex != null) {
                errMsg += "Message: " + ex.getMessage();
                errMsg += "SQLState: " + ex.getSQLState();
                errMsg += "ErrorCode: " + ex.getErrorCode();
                ex = ex.getNextException();
                errMsg += "";
            }
        }

        return false;
    }

}
