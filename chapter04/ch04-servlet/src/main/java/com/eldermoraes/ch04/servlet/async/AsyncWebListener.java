package com.eldermoraes.ch04.servlet.async;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author eldermoraes
 */
@WebListener
public class AsyncWebListener implements AsyncListener {
 
    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        System.out.println("onComplete event");
    }
 
    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        System.out.println("onError event");
    }
 
    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        System.out.println("onStartAsync event");
    }
 
    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        System.out.println("onTimeout event");

        ServletResponse response = asyncEvent.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.write("Process timed out");
    }
 
}
