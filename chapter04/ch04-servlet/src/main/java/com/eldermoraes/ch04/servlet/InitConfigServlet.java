package com.eldermoraes.ch04.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eldermoraes
 */
@WebServlet(name = "InitConfigServlet", urlPatterns = {"/InitConfigServlet"}, 
        initParams = {
                @WebInitParam(name = "param1", value = "value1"),
                @WebInitParam(name = "param2", value = "value2")
        }
)
public class InitConfigServlet extends HttpServlet {

    Map<String, String> param = new HashMap<>();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }
    
    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        if (param.isEmpty()){
            out.println("No params to show");
        } else{
            param.forEach((k,v) -> out.println("param: " + k + ", value: " + v));
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
        Enumeration<String> params = config.getInitParameterNames();
        while (params.hasMoreElements()){
            String key = config.getInitParameterNames().nextElement();
            param.put(key, config.getInitParameter(key));
        }
    }

}
