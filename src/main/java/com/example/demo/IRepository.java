package com.example.demo;

import java.util.List;

public interface IRepository {

        void init();
        void close();
        void create(Quiz quiz);
        void delete(Quiz quiz);
        List<String> find();
        List<String> getCategories(Quiz quiz);
        List<String> getLanguages();
        boolean login(Quiz quiz);
        boolean signup(Quiz quiz);


}
