package com.eldermoraes.ch05.programmatic;

import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.CallerOnlyCredential;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import java.io.IOException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@DeclareRoles({Roles.ADMIN, Roles.USER})
@WebServlet(name = "/OperationServlet", urlPatterns = {"/OperationServlet"})
public class OperationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private UserBean userActivity;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        Credential credential = new UsernamePasswordCredential(name, new Password(password));

        AuthenticationStatus status = securityContext.authenticate(
                request, response, withParams().credential(credential));

        response.getWriter().write("Role \"admin\" access: " + request.isUserInRole(Roles.ADMIN) + "\n");
        response.getWriter().write("Role \"user\" access: " + request.isUserInRole(Roles.USER) + "\n");

        if (status.equals(AuthenticationStatus.SUCCESS)) {

            if (request.isUserInRole(Roles.ADMIN)) {
                userActivity.adminOperation();
                response.getWriter().write("adminOperation executed: true\n");
            } else if (request.isUserInRole(Roles.USER)) {
                userActivity.userOperation();
                response.getWriter().write("userOperation executed: true\n");
            }

            userActivity.everyoneCanDo();
            response.getWriter().write("everyoneCanDo executed: true\n");

        } else {
            response.getWriter().write("Authentication failed\n");
        }

    }
}
