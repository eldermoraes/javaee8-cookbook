package com.eldermoraes.ch05.authorization;

import javax.annotation.security.RunAs;

/**
 *
 * @author eldermoraes
 */
public class RoleExecutor {
    
    public interface Executable {
        void execute() throws Exception;
    }
    
    public interface RoleExecutable {
        void run(Executable executable) throws Exception;
    }
    
    @RunAs("role1")
    public class Role1Executor implements RoleExecutable{
        @Override
        public void run(Executable executable) throws Exception {
            executable.execute();
        }
    }

    @RunAs("role2")
    public class Role2Executor  implements RoleExecutable{
        @Override
        public void run(Executable executable) throws Exception {
            executable.execute();
        }
    }
    
    @RunAs("role3")
    public class Role3Executor  implements RoleExecutable{
        @Override
        public void run(Executable executable) throws Exception {
            executable.execute();
        }
    }    
}
