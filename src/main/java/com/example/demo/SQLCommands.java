package com.example.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SQLCommands {
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;


    public static List<String> getCategories(int languageId) {
        return executeQuery(getSQLCategories() + languageId,
                rs -> rs.getInt("category_id") + "," + rs.getString("category_name"));
    }



//    public static List<String> getCategories(int languageId) {
//
//        List<String> categories = new ArrayList<>();
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con =  DatabaseConnection.getConnection();
//            stmt = con.createStatement();
//
//            String sql = getSQLCategories() + languageId;
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()) {
//                int categoryId = rs.getInt("category_id");
//                String categoryName = rs.getString("category_name");
//                String categoryInfo = categoryId + "," + categoryName;
//                categories.add(categoryInfo);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        return categories;
//    }


    public static List<String> getLanguages() {
        return executeQuery(getSQLLanguage(),
                rs -> rs.getInt("language_id") + "," + rs.getString("language_name"));
    }

//    public static List<String> getLanguages() {
//
//        List<String> languages = new ArrayList<>();
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con =  DatabaseConnection.getConnection();
//            stmt = con.createStatement();
//
//            String sql = getSQLLanguage();
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()) {
//                int language_id = rs.getInt("language_id");
//                String language_name = rs.getString("language_name");
//                String languageInfo = language_id + "," + language_name;
//                languages.add(languageInfo);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        return languages;
//    }


    /** fetches data from a database using the given SQL,
    * then uses the provided function to process each piece of data,
    * and finally returns a list of the processed data.
    * It uses lambdas and the Stream API to handle the data in a streamlined way.
     */
    private static <T> List<T> executeQuery(String sql, ResultSetMapper<T> mapper) {
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return getStream(rs)
                    .map(result -> {
                        try {
                            return mapper.map(result);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error executing query", e);
        }
    }


    /**
     * Responsible for converting a ResultSet (which represents the result of a database query)
     * into a Java Stream, enabling functional-style operations on it.
     * It creates an anonymous inner class implementing the Iterator interface.
     * Functional programming style: it allows for operations on the database results without modifying
     * the original data and enables the chaining of operations.
     * @param rs
     * @return stream
     */
    private static Stream<ResultSet> getStream(ResultSet rs) {
        Iterator<ResultSet> resultSetIterator = new Iterator<>() {
            @Override
            public boolean hasNext() {
                try {
                    return rs.next();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public ResultSet next() {
                return rs;
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(resultSetIterator, Spliterator.ORDERED), false);
    }


    /**
     * functional interface that provides a contract for mapping a ResultSet into a specific type (T).
     * A way to process a row of the result set into something else/an object.
     * @param <T>
     */
    @FunctionalInterface
    private interface ResultSetMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }



    public static String getSQLCategories() {

        return "SELECT * FROM categories WHERE language_id = ";
    }
    public static String getSQLLanguage() {

        return "SELECT * FROM Languages";
    }

    public static String getSQLQuestions() {
        return "SELECT Q.question_id, Q.question_text, O.option_text, O.is_correct, Q.image_location_data " +
                "FROM Questions Q " +
                "INNER JOIN Options O ON Q.question_id = O.question_id " +
                "WHERE Q.language_id = ? AND Q.category_id = ?";
    }

    public static String getSQLInsertIntoQuestions(){
        return "INSERT INTO Questions(category_id, language_id, question_text, image_location_data) VALUES (?,?,?,?)";
    }

    public static String getSQLInsertIntoOptions() {
        return "INSERT INTO Options (question_id, option_text, is_correct) VALUES (?,?,?)";
    }

    public static String getSQLDeleteFromQuestions() {
        return "DELETE FROM Questions WHERE question_id = ?";
    }

    public static String getSQLAccountsWithUser() {
        return "SELECT * FROM accounts WHERE username=?";
    }

    public static String getSQLSignup() {
        return "INSERT INTO accounts (username, password) VALUES (?, ?)";
    }

    public static String getSQLQuestionAndText(){
        return "SELECT question_id, question_text FROM Questions";
    }

}
