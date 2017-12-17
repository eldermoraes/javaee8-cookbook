package com.eldermoraes.ch06.jms;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author eldermoraes
 */
@WebServlet(name = "QueueReceiverServlet", urlPatterns = {"/QueueReceiverServlet"}, loadOnStartup = 1)
public class QueueReceiverServlet extends HttpServlet {
    
    @Inject
    private QueueReceiver receiver;

    @Override
    public void init() throws ServletException {
        receiver.receive();
        System.out.println("**********Queue receiver defined. Check the log for details.**********");
    }    

}