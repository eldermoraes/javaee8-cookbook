package com.eldermoraes.ch05.authorization;

import javax.annotation.security.RunAs;
import javax.inject.Named;

/**
 *
 * @author eldermoraes
 */
@Named
@RunAs(Roles.ROLE1)
public class Role1Executor implements RoleExecutable {

    @Override
    public void run(Executable executable) throws Exception {
        executable.execute();
    }
}
