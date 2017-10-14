package com.eldermoraes.ch05.authentication;

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

@DeclareRoles({"role1", "role2", "role3"})
@WebServlet("/userAuthServlet")
public class UserAuthServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private SecurityContext securityContext;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");

        if (null != name) {

            AuthenticationStatus status = securityContext.authenticate(
                    request, response,
                    withParams()
                            .credential(
                                    new CallerOnlyCredential(name)));

            response.getWriter().write("Authenticated with status: " + status.name() + "\n");
        }

        String principal = null;
        if (request.getUserPrincipal() != null) {
            principal = request.getUserPrincipal().getName();
        }

        response.getWriter().write("username: " + principal + "\n");

        response.getWriter().write("user has role \"role1\": " + request.isUserInRole("role1") + "\n");
        response.getWriter().write("user has role \"role2\": " + request.isUserInRole("role2") + "\n");
        response.getWriter().write("user has role \"role3\": " + request.isUserInRole("role3") + "\n");

        String callerName = null;
        if (securityContext.getCallerPrincipal() != null) {
            callerName = securityContext.getCallerPrincipal().getName();
        }

        response.getWriter().write("caller username: " + callerName + "\n");

        response.getWriter().write("caller user has role \"role1\": " + securityContext.isCallerInRole("role1") + "\n");
        response.getWriter().write("caller user has role \"role2\": " + securityContext.isCallerInRole("role2") + "\n");
        response.getWriter().write("caller user has role \"role3\": " + securityContext.isCallerInRole("role3") + "\n");

        response.getWriter().write("has access to /authServlet: " + securityContext.hasAccessToWebResource("/authServlet") + "\n");

    }

}
