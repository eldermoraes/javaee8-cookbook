package com.eldermoraes.ch06.connectionpooling;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eldermoraes
 */
@WebServlet(name = "PoolTestServlet", urlPatterns = {"/PoolTestServlet"})
public class PoolTestServlet extends HttpServlet {

    @EJB
    private SysConfigBean config;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (PrintWriter writer = response.getWriter()) {
            config = new SysConfigBean();
            writer.write(config.getSysConfig());
        } catch (SQLException | NamingException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
