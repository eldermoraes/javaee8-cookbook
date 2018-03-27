package com.eldermoraes.ch04.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
                @WebInitParam(name = "key1", value = "value1"),
                @WebInitParam(name = "key2", value = "value2"),
                @WebInitParam(name = "key3", value = "value3"),
                @WebInitParam(name = "key4", value = "value4"),
                @WebInitParam(name = "key5", value = "value5")
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
            param.forEach((k,v) -> out.println("param: " + k + ", value: " + v + "<br />"));
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
        List<String> list = Collections.list(config.getInitParameterNames());
        list.forEach((key) -> {
            param.put(key, config.getInitParameter(key));
        });
    }

}
