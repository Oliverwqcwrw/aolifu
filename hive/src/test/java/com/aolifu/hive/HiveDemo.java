package com.aolifu.hive;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class HiveDemo {
    public static void main(String[] args) {
//        String sql = "select * from emp1";
        String sql = "create database testdb1";
 
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
 
        try {
            conn = JDBCUtils.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
 
            while(rs.next()){
                String ename = rs.getString("ename");
                double sal = rs.getDouble("sal");
                System.out.println(ename + "\t" + sal);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.release(conn,st,rs);
        }
    }
}