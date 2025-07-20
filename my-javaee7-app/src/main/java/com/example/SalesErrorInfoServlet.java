package com.example;

import service.SalesErrorInfoGetRequest;
import service.SalesErrorInfoGetResponse;
import service.SalesErrorInfoRecord;
import service.SalesErrorInfoService;

import javax.inject.Inject;
import javax.json.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "SalesErrorServlet", urlPatterns = "/error") // URLパスを定義
public class SalesErrorInfoServlet extends HttpServlet {

    @Inject
    private SalesErrorInfoService salesErrorInfoService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        String[] selectedItems = request.getParameterValues("item");
        String[] selectedFruits = request.getParameterValues("fruit");
        String startDateString = request.getParameter("startDate");
        String endDateString = request.getParameter("endDate");

        SalesErrorInfoGetRequest req = SalesErrorInfoGetRequest.builder()
                .items(Arrays.asList(selectedItems))
                .fruits(Arrays.asList(selectedFruits))
                .startDate(LocalDate.parse(startDateString))
                .endDate(LocalDate.parse(endDateString))
                .build();
        SalesErrorInfoGetResponse res = salesErrorInfoService.getSalesErrorInfo(req);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // REQUEST
            JsonObjectBuilder reqBuilder = Json.createObjectBuilder()
                    .add("item",  array(selectedItems))
                    .add("fruit", array(selectedFruits))
                    .add("startDate", startDateString)
                    .add("endDate", endDateString)
                    ;
            // RESPONSE
            JsonObjectBuilder resBuilder = Json.createObjectBuilder()
                    .add("resultCode",  res.getResultCode())
                    .add("records", listToJ(res.getRecords()))
                    ;
            // ALL
            JsonObjectBuilder jsonBuilder = Json.createObjectBuilder()
                    .add("REQUEST", reqBuilder.build())
                    .add("RESPONSE", resBuilder.build())
                    ;
            out.print(jsonBuilder.build().toString());
            out.flush();
        }
    }

    private JsonArray listToJ(List<SalesErrorInfoRecord> records) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (SalesErrorInfoRecord record : records) {
            JsonObjectBuilder jsonBuilder = Json.createObjectBuilder()
                    .add("registerId", record.getRegisterId())
                    .add("firstName", record.getFirstName())
                    .add("lastName", record.getLastName())
                    .add("email", record.getEmail())
                    .add("birth", String.valueOf(record.getBirthDate()))
                    .add("jobId", record.getJobId())
                    .add("managerId", record.getManagerId())
                    ;
            arrayBuilder.add(jsonBuilder.build());
        }
        return arrayBuilder.build();
    }

    private JsonArray array(String[] items) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (String item : items) {
            arrayBuilder.add(item);
        }
        return arrayBuilder.build();
    }

}
