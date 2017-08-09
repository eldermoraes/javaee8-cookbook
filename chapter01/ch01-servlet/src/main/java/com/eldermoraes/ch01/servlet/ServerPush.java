package com.eldermoraes.ch01.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author eldermoraes
 */
@WebServlet(name = "ServerPush", urlPatterns = {"/ServerPush"})
public class ServerPush extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PushBuilder pb = request.newPushBuilder();
        if (pb != null) {
            pb.path("images/javaee-logo.png")
              .addHeader("content-type", "image/png")
              .push();
        }

        try (PrintWriter writer = response.getWriter();) {
            StringBuilder html = new StringBuilder();
            html.append("<html>");
            html.append("<center>");
            html.append("<img src='images/javaee-logo.png'><br>");
            html.append("<h2>Image pushed by ServerPush</h2>");
            html.append("</center>");
            html.append("</html>");
            writer.write(html.toString());
        }
    }

}
