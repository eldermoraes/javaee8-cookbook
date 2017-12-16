package com.eldermoraes.ch06.connectionpooling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.naming.NamingException;

/**
 *
 * @author eldermoraes
 */
public class SysConfigBean {

    public String getSysConfig() throws SQLException, NamingException {
        try (Connection conn = ConnectionPool.getConnection()) {
            String sql = "SELECT variable, value FROM sys_config";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<SysConfig> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new SysConfig(rs.getString("variable"), rs.getString("value")));
            }

            Jsonb jsonbBuilder = JsonbBuilder.create();
            return jsonbBuilder.toJson(list);
        } 
    }
}
