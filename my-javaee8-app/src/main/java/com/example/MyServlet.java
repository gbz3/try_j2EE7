package com.example;

import com.example.db.entity.EmployeesEntity;
import com.example.service.EmployeeService;
import com.example.service.EmployeeServiceRequest;
import com.example.service.EmployeeServiceResponse;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/hello")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private EmployeeService employeeService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        EmployeeServiceRequest.EmployeeServiceRequestBuilder req = EmployeeServiceRequest.builder();

        String birthStart = request.getParameter("birth-start");
        if (birthStart != null && !birthStart.isEmpty()) {
            req.birthStart(LocalDate.parse(birthStart));
        }
        String birthEnd = request.getParameter("birth-end");
        if (birthEnd != null && !birthEnd.isEmpty()) {
            req.birthEnd(LocalDate.parse(birthEnd));
        }
        String firstName = request.getParameter("first-name");
        if (firstName != null && !firstName.isEmpty()) {
            req.firstName(firstName);
        }

        req.managerIds(
                Arrays.stream(request.getParameterValues("manager"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        );

        EmployeeServiceResponse res = employeeService.getEmployees(req.build());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // REQUEST
            JsonObjectBuilder reqBuilder = Json.createObjectBuilder()
                    .add("birth-start", birthStart)
                    .add("birth-end", birthEnd)
                    ;

            // RESPONSE
            JsonObjectBuilder resBuilder = Json.createObjectBuilder()
                    .add("resultCode", res.getResultCode())
                    .add("employees", listToJ(res.getEmployees()))
                    ;

            // ALL
            JsonObjectBuilder allBuilder = Json.createObjectBuilder()
                    .add("REQUEST", reqBuilder.build())
                    .add("RESPONSE", resBuilder.build())
                    ;

            out.println(allBuilder.build());
            out.flush();
        }
    }

    private JsonArray listToJ(List<EmployeesEntity> records) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (EmployeesEntity record : records) {
            JsonObjectBuilder jsonBuilder = Json.createObjectBuilder()
                    .add("registerId", record.getEmployeeId())
                    .add("firstName", record.getFirstName())
                    .add("lastName", record.getLastName())
                    .add("email", record.getEmail())
                    .add("birth", String.valueOf(record.getBirth()))
                    .add("jobId", record.getJobId())
                    .add("managerId", record.getManagerId());
            arrayBuilder.add(jsonBuilder.build());
        }
        return arrayBuilder.build();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Java EE 8 Hello</title></head>");
        out.println("<body>");
        out.println("<h1>Hello from Java EE 8!</h1>");
        out.println("</body>");
        out.println("</html>");
    }

}
