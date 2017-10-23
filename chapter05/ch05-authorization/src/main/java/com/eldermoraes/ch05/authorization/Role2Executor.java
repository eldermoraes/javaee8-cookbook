package com.eldermoraes.ch05.authorization;

import javax.annotation.security.RunAs;
import javax.inject.Named;

/**
 *
 * @author eldermoraes
 */
@Named
@RunAs(Roles.ROLE2)
public class Role2Executor implements RoleExecutable {

    @Override
    public void run(Executable executable) throws Exception {
        executable.execute();
    }
}
