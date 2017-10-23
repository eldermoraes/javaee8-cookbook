package com.eldermoraes.ch05.authorization;

import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.CallerOnlyCredential;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@DeclareRoles({Roles.ROLE1, Roles.ROLE2, Roles.ROLE3})
@WebServlet(name = "/UserAuthorizationServlet", urlPatterns = {"/UserAuthorizationServlet"})
public class UserAuthorizationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private SecurityContext securityContext;
    
    @Inject
    private Role1Executor role1Executor;
    
    @Inject
    private Role2Executor role2Executor;
    
    @Inject
    private Role3Executor role3Executor;
    
    @Inject
    private UserActivity userActivity;
    
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
            response.getWriter().write("Role \"role1\" access: " + request.isUserInRole(Roles.ROLE1) + "\n");
            response.getWriter().write("Role \"role2\" access: " + request.isUserInRole(Roles.ROLE2) + "\n");
            response.getWriter().write("Role \"role3\" access: " + request.isUserInRole(Roles.ROLE3) + "\n");

            RoleExecutable executable = null;

            if (request.isUserInRole(Roles.ROLE1)) {
                executable = role1Executor;
            } else if (request.isUserInRole(Roles.ROLE2)) {
                executable = role2Executor;
            } else if (request.isUserInRole(Roles.ROLE3)) {
                executable = role3Executor;
            }

            if (executable != null) {
                executable.run(() -> {
                    try {
                        userActivity.role1Allowed();
                        response.getWriter().write("role1Allowed executed: true\n");
                    } catch (Exception e) {
                        response.getWriter().write("role1Allowed executed: false\n");
                    }

                    try {
                        userActivity.role2Allowed();
                        response.getWriter().write("role2Allowed executed: true\n");
                    } catch (Exception e) {
                        response.getWriter().write("role2Allowed executed: false\n");
                    }

                    try {
                        userActivity.role3Allowed();
                        response.getWriter().write("role2Allowed executed: true\n");
                    } catch (Exception e) {
                        response.getWriter().write("role2Allowed executed: false\n");
                    }

                });

            }

            try {
                userActivity.anonymousAllowed();
                response.getWriter().write("anonymousAllowed executed: true\n");
            } catch (Exception e) {
                response.getWriter().write("anonymousAllowed executed: false\n");
            }
            
            try {
                userActivity.noOneAllowed();
                response.getWriter().write("noOneAllowed executed: true\n");
            } catch (Exception e) {
                response.getWriter().write("noOneAllowed executed: false\n");
            }            

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }
}
