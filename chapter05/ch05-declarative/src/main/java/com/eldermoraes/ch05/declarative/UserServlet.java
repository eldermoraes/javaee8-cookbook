package com.eldermoraes.ch05.declarative;

import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.CallerOnlyCredential;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@DeclareRoles({Roles.ADMIN, Roles.USER})
@WebServlet(name = "/UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private UserExecutor userExecutor;

    @Inject
    private UserBean userActivity;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            securityContext.authenticate(
                    request, response, withParams().credential(new CallerOnlyCredential(Roles.USER)));

            response.getWriter().write("Role \"admin\" access: " + request.isUserInRole(Roles.ADMIN) + "\n");
            response.getWriter().write("Role \"user\" access: " + request.isUserInRole(Roles.USER) + "\n");

            userExecutor.run(() -> {
                try {
                    userActivity.adminOperation();
                    response.getWriter().write("adminOperation executed: true\n");
                } catch (Exception e) {
                    response.getWriter().write("adminOperation executed: false\n");
                }

                try {
                    userActivity.userOperation();
                    response.getWriter().write("userOperation executed: true\n");
                } catch (Exception e) {
                    response.getWriter().write("userOperation executed: false\n");
                }

            });

            try {
                userActivity.everyoneCanDo();
                response.getWriter().write("everyoneCanDo executed: true\n");
            } catch (Exception e) {
                response.getWriter().write("everyoneCanDo executed: false\n");
            }

            try {
                userActivity.noneCanDo();
                response.getWriter().write("noneCanDo executed: true\n");
            } catch (Exception e) {
                response.getWriter().write("noneCanDo executed: false\n");
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }
}
