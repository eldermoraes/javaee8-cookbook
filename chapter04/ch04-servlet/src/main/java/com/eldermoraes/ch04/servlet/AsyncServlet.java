package com.eldermoraes.ch04.servlet;

import java.io.IOException;
import java.util.Queue;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eldermoraes
 */
@WebServlet(name = "AsyncServlet", urlPatterns = {"/AsyncServlet"}, asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("param1", System.currentTimeMillis());
            Thread.sleep(500);
            request.setAttribute("param2", System.currentTimeMillis());
            
            AsyncContext asyncCtx = request.startAsync(request, response);
            ServletContext servletCtx = request.getServletContext();
            ((Queue<AsyncContext>) servletCtx.getAttribute("asyncQueue")).add(asyncCtx);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

