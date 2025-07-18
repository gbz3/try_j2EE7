package com.example;

import javax.json.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SalesErrorServlet", urlPatterns = "/error") // URLパスを定義
public class SalesErrorInfoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        String[] selectedItems = request.getParameterValues("item");
        String[] selectedFruits = request.getParameterValues("fruit");
        String startDateString = request.getParameter("startDate");
        String endDateString = request.getParameter("endDate");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            JsonObjectBuilder reqBuilder = Json.createObjectBuilder()
                    .add("item",  array(selectedItems))
                    .add("fruit", array(selectedFruits))
                    .add("startDate", startDateString)
                    .add("endDate", endDateString)
                    ;
            JsonObjectBuilder jsonBuilder = Json.createObjectBuilder()
                    .add("REQUEST", reqBuilder.build())
                    .add("RESPONSE", JsonValue.NULL)
                    ;
            out.print(jsonBuilder.build().toString());
            out.flush();
        }
    }

    private JsonArray array(String[] items) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (String item : items) {
            arrayBuilder.add(item);
        }
        return arrayBuilder.build();
    }

}
