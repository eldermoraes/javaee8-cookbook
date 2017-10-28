package com.eldermoraes.ch05.declarative;

import javax.annotation.security.RunAs;
import javax.inject.Named;

/**
 *
 * @author eldermoraes
 */
@Named
@RunAs(Roles.ADMIN)
public class AdminExecutor implements RoleExecutable {

    @Override
    public void run(Executable executable) throws Exception {
        executable.execute();
    }
}
