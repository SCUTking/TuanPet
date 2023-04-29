package com.tuanpet.activitythought.JsonToStringArray;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonStringArrayTypeHandler implements TypeHandler<String[]> {
    @Override
    public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setString(i, null);
        } else {
            String jsonString = JSON.toJSONString(parameter);
            ps.setString(i, jsonString);
        }
    }

    @Override
    public String[] getResult(ResultSet rs, String columnName) throws SQLException {
        String jsonString = rs.getString(columnName);
        return StringUtils.isBlank(jsonString) ? null : JSON.parseObject(jsonString, String[].class);
    }

    @Override
    public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonString = rs.getString(columnIndex);
        return StringUtils.isBlank(jsonString) ? null : JSON.parseObject(jsonString, String[].class);
    }

    @Override
    public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonString = cs.getString(columnIndex);
        return StringUtils.isBlank(jsonString) ? null : JSON.parseObject(jsonString, String[].class);
    }
}
