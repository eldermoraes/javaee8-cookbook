package com.eldermoraes.ch04.servlet.async;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eldermoraes
 */
@WebServlet(urlPatterns = "/AsyncServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("AsyncServlet Begin, Name="
                + Thread.currentThread().getName() + ", ID="
                + Thread.currentThread().getId());
 
        String time = request.getParameter("timestamp");
        int secs = Integer.valueOf(time);
 
        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.addListener(new AsyncWebListener());
        asyncCtx.setTimeout(30000);
 
        ThreadPoolExecutor executor = (ThreadPoolExecutor) request
                .getServletContext().getAttribute("async-exec");
 
        executor.execute(new AsyncExecutor(asyncCtx, secs));
        long endTime = System.currentTimeMillis();
        System.out.println("AsyncServlet Finish, Name="
                + Thread.currentThread().getName() + ", ID="
                + Thread.currentThread().getId() + ", Duration="
                + (endTime - startTime) + " milliseconds.");
    }
 
}
