package com.eldermoraes.ch04.servlet.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author eldermoraes
 */
@WebListener
public class ContextWebListener implements ServletContextListener {
 
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
 
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                200, 200, 30000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(50));
        servletContextEvent.getServletContext().setAttribute("async-exec", executor);
 
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent.
                getServletContext().getAttribute("async-exec");
        executor.shutdown();
    }
 
}
