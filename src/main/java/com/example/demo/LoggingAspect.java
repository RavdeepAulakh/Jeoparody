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

//package com.example.demo;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import jakarta.servlet.http.*;
//import jakarta.servlet.*;

//@Aspect
//public class LoggingAspect {
//
//        @Before("execution(void jakarta.servlet.http.HttpServlet+.doGet(jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse))")
//        public void logDoGet(JoinPoint joinPoint) {
//                System.out.println("doGet called for servlet: " + joinPoint.getTarget().getClass().getName());
//        }
//
//        @Before("execution(void jakarta.servlet.http.HttpServlet+.doPost(jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse))")
//        public void logDoPost(JoinPoint joinPoint) {
//                System.out.println("doPost called for servlet: " + joinPoint.getTarget().getClass().getName());
//        }
//}
