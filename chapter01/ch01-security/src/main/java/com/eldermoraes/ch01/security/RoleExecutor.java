package com.eldermoraes.ch01.security;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

/**
 *
 * @author eldermoraes
 */
public class RoleExecutor {
    
    public interface Executable {
        void execute() throws Exception;
    }
    
    @Stateless
    @RunAs(Roles.ADMIN)
    public static class AdminExecutor  {
        public void run(Executable executable) throws Exception {
            executable.execute();
        }
    }

    @Stateless
    @RunAs(Roles.OPERATOR)
    public static class OperatorExecutor {
        public void run(Executable executable) throws Exception {
            executable.execute();
        }
    }
}
