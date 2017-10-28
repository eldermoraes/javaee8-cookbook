package com.eldermoraes.ch05.declarative;

/**
 *
 * @author eldermoraes
 */
public interface RoleExecutable {

    public interface Executable {
        void execute() throws Exception;
    }

    void run(Executable executable) throws Exception;
}
