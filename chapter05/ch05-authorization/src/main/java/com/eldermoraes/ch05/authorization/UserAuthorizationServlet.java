package com.eldermoraes.ch05.authorization;

import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.CallerOnlyCredential;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@DeclareRoles({"role1", "role2", "role3"})
@WebServlet(name = "/UserAuthorizationServlet", urlPatterns = {"/UserAuthorizationServlet"})
public class UserAuthorizationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private SecurityContext securityContext;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            if (null != name || !"".equals(name)) {
                AuthenticationStatus status = securityContext.authenticate(
                        request, response, withParams().credential(new CallerOnlyCredential(name)));

                response.getWriter().write("Authentication status: " + status.name() + "\n");
            }

            String principal = null;
            if (request.getUserPrincipal() != null) {
                principal = request.getUserPrincipal().getName();
            }

            response.getWriter().write("User: " + principal + "\n");
            response.getWriter().write("Role \"role1\" access: " + request.isUserInRole("role1") + "\n");
            response.getWriter().write("Role \"role2\" access: " + request.isUserInRole("role2") + "\n");
            response.getWriter().write("Role \"role3\" access: " + request.isUserInRole("role3") + "\n");

            RoleExecutor.RoleExecutable executable = null;

            if (request.isUserInRole("role1")) {
                executable = new RoleExecutor().new Role1Executor();
            } else if (request.isUserInRole("role2")) {
                executable = new RoleExecutor().new Role2Executor();
            } else if (request.isUserInRole("role3")) {
                executable = new RoleExecutor().new Role3Executor();
            }

            if (executable != null) {
                executable.run(() -> {
                    try {
                        response.getWriter().write("role1Allowed: " + UserActivity.role1Allowed() + "\n");
                    } catch (Exception e) {
                        response.getWriter().write("role1Allowed: " + e.getMessage() + "\n");
                    }

                    try {
                        response.getWriter().write("role2Allowed: " + UserActivity.role2Allowed() + "\n");
                    } catch (Exception e) {
                        response.getWriter().write("role2Allowed: " + e.getMessage() + "\n");
                    }

                    try {
                        response.getWriter().write("role3Allowed: " + UserActivity.role3Allowed() + "\n");
                    } catch (Exception e) {
                        response.getWriter().write("role3Allowed: " + e.getMessage() + "\n");
                    }

                });

            }

            try {
                response.getWriter().write("anonymousAllowed: " + UserActivity.anonymousAllowed() + "\n");
            } catch (Exception e) {
                response.getWriter().write("anonymousAllowed: " + e.getMessage() + "\n");
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }
}
