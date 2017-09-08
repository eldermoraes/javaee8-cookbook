package com.eldermoraes.ch04.servlet.async;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;

/**
 *
 * @author eldermoraes
 */
public class AsyncExecutor implements Runnable {
 
    private AsyncContext asyncContext;
    private int secs;
 
    public AsyncExecutor() {
    }
 
    public AsyncExecutor(AsyncContext asyncCtx, int secs) {
        this.asyncContext = asyncCtx;
        this.secs = secs;
    }
 
    @Override
    public void run() {
        System.out.println("isAsyncSupported="
                + asyncContext.getRequest().isAsyncSupported());
        longProcessing(secs);
        try {
            PrintWriter out = asyncContext.getResponse().getWriter();
            out.write("Async process time: " + secs + " milliseconds");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        asyncContext.complete();
    }
 
    private void longProcessing(int secs) {
        try {
            Thread.sleep(secs);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
