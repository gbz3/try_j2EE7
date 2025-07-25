package com.example;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String birthStart = request.getParameter("birth-start");
        String birthEnd = request.getParameter("birth-end");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // REQUEST
            JsonObjectBuilder reqBuilder = Json.createObjectBuilder()
                    .add("birth-start", birthStart)
                    .add("birth-end", birthEnd)
                    ;

            // ALL
            JsonObjectBuilder allBuilder = Json.createObjectBuilder()
                    .add("REQUEST", reqBuilder.build())
                    ;

            out.println(allBuilder.build());
            out.flush();
        }
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
