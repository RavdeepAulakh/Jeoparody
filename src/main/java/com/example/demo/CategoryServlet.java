package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CategoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Include the HTML content from category.html
        request.getRequestDispatcher("/category.html").include(request, response);

        out.println("<html><head><title>Select Category</title></head><body>");

        // Get the language ID from the request, assuming it's passed as a parameter
        String languageIdParam = request.getParameter("languageId");
        if (languageIdParam == null || languageIdParam.isEmpty()) {
            out.println("Language ID is missing in the request.");
            out.println("</body></html>");
            return;
        }

        IRepository repository = new Repository();
        int languageId = Integer.parseInt(languageIdParam);
        Quiz quiz = new Quiz(languageId);

        List<String> categoryInfos = repository.getCategories(quiz);

        /**
         * Replaced the for loop with the forEach, which is provided by the
         * Stream API.
         */
        categoryInfos.stream().forEach(categoryInfo -> {
            String[] parts = categoryInfo.split(",");
            if (parts.length == 2) {
                int categoryId = Integer.parseInt(parts[0]);
                String categoryName = parts[1];

                // Create a button for each category
                out.println("<div class=\"button\" onclick=\"selectCategory(" + categoryId + ")\">" + categoryName + "</div>");
            }
        });


//        for (String categoryInfo : categoryInfos) {
//            String[] parts = categoryInfo.split(",");
//            if (parts.length == 2) {
//                int categoryId = Integer.parseInt(parts[0]);
//                String categoryName = parts[1];
//
//                // Create a button for each category
//                out.println("<div class=\"button\" onclick=\"selectCategory(" + categoryId + ")\">" + categoryName + "</div>");
//            }
//        }


        // JavaScript function to handle button click
        out.println("<script>");
        out.println("function selectCategory(categoryId) {");
        out.println("  var languageId = " + languageId + ";"); // Get the languageId from the current page
        out.println("  // Redirect to the GameServlet with the selected category and language IDs as parameters");
        out.println("window.location.href = 'display?languageId=' + languageId + '&categoryId=' + categoryId;");
        out.println("}");
        out.println("</script>");

        out.println("</body></html>");
    }
}