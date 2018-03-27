package com.eldermoraes.ch06.connectionpooling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.naming.NamingException;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class SysConfigBean {

    public String getSysConfig() throws SQLException, NamingException, Exception {
        String sql = "SELECT variable, value FROM sys_config";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                Jsonb jsonb = JsonbBuilder.create()) {

            List<SysConfig> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new SysConfig(rs.getString("variable"), rs.getString("value")));
            }

            String json = jsonb.toJson(list);
            return json;
        }
    }
}
