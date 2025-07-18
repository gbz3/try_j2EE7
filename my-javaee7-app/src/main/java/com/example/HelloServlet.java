package com.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello") // URLパスを定義
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>Hello Java EE 7</title></head>");
            out.println("<body>");
            out.println("<h1>Hello from Java EE 7 Web Application!</h1>");
            out.println("<p>Current time: " + new java.util.Date() + "</p>");
            out.println("</body>");
            out.println("</html>");
            System.out.printf("%s%n", "HOGE");
        }
    }

}
