package com.eldermoraes.ch04.servlet;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author eldermoraes
 */
@WebListener
public class SomeSlowStuff implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Queue<AsyncContext> jobQueue = new ConcurrentLinkedQueue<>();
        sce.getServletContext().setAttribute("asyncQueue", jobQueue);
        
        Executor executor = Executors.newFixedThreadPool(10);
        while(true)
        {
            if(!jobQueue.isEmpty())
            {
                final AsyncContext asyncCtx = jobQueue.poll();
                executor.execute(() -> {
                    try {
                        ServletRequest request = asyncCtx.getRequest();
                        String param1 = request.getParameter("param1");
                        String param2 = request.getParameter("param2");
                        
                        Thread.sleep(2000);

                        asyncCtx.getRequest().setAttribute("param1", param1);
                        asyncCtx.getRequest().setAttribute("param2", param2);

                        asyncCtx.dispatch("/async.jsp");
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                });             
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}