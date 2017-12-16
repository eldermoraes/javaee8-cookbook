package com.eldermoraes.ch06.jms;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author eldermoraes
 */
@WebServlet(name = "TopicReceiverServlet", urlPatterns = {"/TopicReceiverServlet"}, loadOnStartup = 1)
public class TopicReceiverServlet extends HttpServlet {
    
    @Inject
    private TopicReceiver receiver;

    @Override
    public void init() throws ServletException {
        receiver.receive();
        System.out.println("**********Topic receiver defined. Check the log for details.**********");
    }    

}
