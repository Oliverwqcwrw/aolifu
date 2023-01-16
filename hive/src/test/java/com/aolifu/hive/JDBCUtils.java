package com.aolifu.hive;
 
import java.sql.*;
 
public class JDBCUtils {
    // Hive的驱动
    private static String driver = "org.apache.hive.jdbc.HiveDriver";
 
    // Hive的URL地址
//    private static String url = "jdbc:hive2://192.168.193.140:10000/default";
    private static String url = "jdbc:hive2://node1:10000/default";
 
    // 注册数据库的驱动
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    // 获取数据库Hive的链接
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,"hadoop","hadoop");
//            return DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
 
    // 释放资源
    public static void release(Connection conn, Statement st, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                rs = null;
            }
        }
 
        if(st != null){
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                st = null;
            }
        }
 
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                conn = null;
            }
        }
    }
}