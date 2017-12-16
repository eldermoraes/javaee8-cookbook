package com.eldermoraes.ch06.jms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
@WebServlet(name = "QueueSenderServlet", urlPatterns = {"/QueueSenderServlet"})
public class QueueSenderServlet extends HttpServlet {
    
    @Inject
    private QueueSender sender;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(PrintWriter writer = response.getWriter()){
            sender.send();
            writer.write("Message sent to queue. Check the log for details.");
        } catch (JMSException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
