package com.eldermoraes.ch05.authorization;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import static java.util.Arrays.asList;
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
                throw new IllegalStateException("Invalid mechanism");
            }

            CallerOnlyCredential callerOnlyCredential = (CallerOnlyCredential) credential;

            if (null == callerOnlyCredential.getCaller()) {
                throw new AuthenticationException();
            } else switch (callerOnlyCredential.getCaller()) {
                case "user1":
                    return httpMessageContext.notifyContainerAboutLogin(callerOnlyCredential.getCaller(), new HashSet<>(asList(Roles.ROLE1)));
                case "user2":
                    return httpMessageContext.notifyContainerAboutLogin(callerOnlyCredential.getCaller(), new HashSet<>(asList(Roles.ROLE2)));
                case "user3":
                    return httpMessageContext.notifyContainerAboutLogin(callerOnlyCredential.getCaller(), new HashSet<>(asList(Roles.ROLE3)));
                default:
                    throw new AuthenticationException();
            }

        }

        return httpMessageContext.doNothing();
    }

}
