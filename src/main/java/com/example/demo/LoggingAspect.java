package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;



@Aspect

public class LoggingAspect {
        @PostConstruct
        public void afterConstructor() {
                System.out.println("Aspect instantiated");
        }


        @Before("execution(* com.example.demo.*.doPost(..)) ")
        public void beforeDoPostCall() {
                System.out.println("Calling doPost");
        }

        @Before("execution(* com.example.demo.*.doGet(..)) ")
        public void beforeDoGetCall() {
                System.out.println("Calling doGet");
        }

}
