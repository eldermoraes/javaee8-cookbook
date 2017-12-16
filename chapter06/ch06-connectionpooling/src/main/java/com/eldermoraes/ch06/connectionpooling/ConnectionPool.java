package com.eldermoraes.ch06.connectionpooling;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author eldermoraes
 */
public class ConnectionPool {

    public static Connection getConnection() throws SQLException, NamingException {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jdbc/MysqlPool");

        return ds.getConnection();
    }

}
