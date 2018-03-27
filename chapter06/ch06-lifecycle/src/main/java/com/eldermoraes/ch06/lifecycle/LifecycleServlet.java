package com.eldermoraes.ch06.lifecycle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eldermoraes
 */
@WebServlet(name = "LifecycleServlet", urlPatterns = {"/LifecycleServlet"})
public class LifecycleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(PrintWriter writer = resp.getWriter()){
            writer.write("doGet");
            System.out.println("doGet");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(PrintWriter writer = resp.getWriter()){
            writer.write("doPost");
            System.out.println("doPost");
        }
    }    
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(PrintWriter writer = resp.getWriter()){
            writer.write("doDelete");
            System.out.println("doDelete");
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(PrintWriter writer = resp.getWriter()){
            writer.write("doPut");
            System.out.println("doPut");
        }
    }    
    
    @Override
    public void init() throws ServletException {
        System.out.println("init()");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
    
}
