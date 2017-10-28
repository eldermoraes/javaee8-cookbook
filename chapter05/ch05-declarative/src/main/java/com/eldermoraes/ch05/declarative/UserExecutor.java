package com.eldermoraes.ch05.declarative;

import javax.annotation.security.RunAs;
import javax.inject.Named;

/**
 *
 * @author eldermoraes
 */
@Named
@RunAs(Roles.USER)
public class UserExecutor implements RoleExecutable {

    @Override
    public void run(Executable executable) throws Exception {
        executable.execute();
    }
}
