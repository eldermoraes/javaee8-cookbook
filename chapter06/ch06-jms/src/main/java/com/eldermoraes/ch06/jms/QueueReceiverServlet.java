package com.eldermoraes.ch06.jms;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eldermoraes
 */
@WebServlet(name = "QueueReceiverServlet", urlPatterns = {"/QueueReceiverServlet"})
public class QueueReceiverServlet extends HttpServlet {
    
    @Inject
    private QueueReceiver receiver;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(PrintWriter writer = response.getWriter()){
            receiver.receive();
            writer.write("Message receive from queue. Check the log for details.");
        } catch (JMSException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
