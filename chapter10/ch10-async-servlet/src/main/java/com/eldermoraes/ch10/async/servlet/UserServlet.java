package com.eldermoraes.ch10.async.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
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
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"}, asyncSupported = true)
public class UserServlet extends HttpServlet {

    @Inject
    private UserBean userBean;

    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext ctx = req.startAsync();
        ctx.start(() -> {
            try (PrintWriter writer = ctx.getResponse().getWriter()) {
                writer.write(jsonb.toJson(userBean.getUser()));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            ctx.complete();
        });
    }

    @Override
    public void destroy() {
        try {
            jsonb.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
