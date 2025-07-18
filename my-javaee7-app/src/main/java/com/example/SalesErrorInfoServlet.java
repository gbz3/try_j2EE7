package com.example;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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

        JsonArrayBuilder itemsArrayBuilder = Json.createArrayBuilder();
        for (String item : selectedItems) {
            itemsArrayBuilder.add(item);
        }

        JsonArrayBuilder fruitsArrayBuilder = Json.createArrayBuilder();
        for (String fruit : selectedFruits) {
            fruitsArrayBuilder.add(fruit);
        }
        JsonObjectBuilder oBuilder = Json.createObjectBuilder()
                .add("item",  itemsArrayBuilder.build())
                .add("fruit", fruitsArrayBuilder.build())
                .add("startDate", startDateString)
                .add("endDate", endDateString)
                ;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(oBuilder.build().toString());
            out.flush();
        }
    }
}
