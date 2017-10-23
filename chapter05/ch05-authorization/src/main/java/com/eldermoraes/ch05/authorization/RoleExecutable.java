package com.eldermoraes.ch05.authorization;

/**
 *
 * @author eldermoraes
 */
public interface RoleExecutable {
    void run(Executable executable) throws Exception;
}
