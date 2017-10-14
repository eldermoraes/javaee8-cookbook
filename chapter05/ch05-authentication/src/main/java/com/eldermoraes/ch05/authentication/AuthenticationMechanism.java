package com.eldermoraes.ch05.authentication;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import static java.util.Arrays.asList;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import java.util.HashSet;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.Credential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApplicationScoped
public class AuthenticationMechanism implements HttpAuthenticationMechanism {

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {

        if (httpMessageContext.isAuthenticationRequest()) {

            Credential credential = httpMessageContext.getAuthParameters().getCredential();
            if (!(credential instanceof CallerOnlyCredential)) {
                throw new IllegalStateException("This authentication mechanism requires CallerOnlyCredential");
            }

            CallerOnlyCredential callerOnlyCredential = (CallerOnlyCredential) credential;

            if ("user".equals(callerOnlyCredential.getCaller())) {
                return httpMessageContext.notifyContainerAboutLogin("user", new HashSet<>(asList("role1", "role2")));
            }

            if ("uzer".equals(callerOnlyCredential.getCaller())) {
                throw new AuthenticationException();
            }

            return SEND_FAILURE;

        }

        return httpMessageContext.doNothing();
    }

}
