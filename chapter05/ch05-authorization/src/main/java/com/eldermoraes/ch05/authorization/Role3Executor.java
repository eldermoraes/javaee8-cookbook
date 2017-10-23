package com.eldermoraes.ch05.authorization;

import javax.annotation.security.RunAs;
import javax.inject.Named;

/**
 *
 * @author eldermoraes
 */
@Named
@RunAs(Roles.ROLE3)
public class Role3Executor implements RoleExecutable {

    @Override
    public void run(Executable executable) throws Exception {
        executable.execute();
    }
}
