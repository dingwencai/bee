package com.bee.parse.utils;

import com.bee.parse.pojo.DPojo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCUtil {

    public static Connection getConnection(){

        String driver = Utils.getPropertiesValue("driver");;    //驱动标识符
        String url = Utils.getPropertiesValue("url");; //链接字符串

        String username = Utils.getPropertiesValue("username");;         //数据库的用户名
        String password = Utils.getPropertiesValue("password");;     //数据库的密码
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 通过PreparedStatement对象进行批量插入
     * @throws SQLException
     */
    public static void insertByPreparedStatement(List<DPojo> objList) throws SQLException {
        // 获取连接对象
        Connection conn = getConnection();
        // 插入Sql语句
        String insertSql = "insert into xx values(?,?)";
        // 获取PreparedStatement对象
        PreparedStatement preStatement = conn.prepareStatement(insertSql);
        // 设置参数
        Long i = 0L;
        for (DPojo e:objList) {
            preStatement.setString(1,e.getKey());
            preStatement.setString(2,e.getValue());
            preStatement.addBatch();

            i++;
            if ((i + 1) % 10000 == 0) {
                //批量执行
                preStatement.executeBatch();
                //清理缓存
                preStatement.clearBatch();
            }
        }
        if ((i + 1) % 10000 != 0) {
            preStatement.executeBatch();
            // 清空批量
            preStatement.clearBatch();
        }

        //关闭对象
        preStatement.close();
    }

    public static void main(String[] args) {

    }

}
