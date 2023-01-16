package com.aolifu.hive;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.hive.jdbc.HiveQueryResultSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 

public class HiveJDBCTest {
    private static String driverName="org.apache.hive.jdbc.HiveDriver";//驱动名称
    private static String url = "jdbc:hive2://localhost:10000/default";//连接hive的default数据库
    private static String user = "hadoop";//hive所在机器的用户名
    private static String password = "hadoop";//hive所在机器的密码
 
    private static Connection conn = null;//连接
    private static Statement stmt = null;//声明
    private static ResultSet rs = null;//结果集
 
    @Before
    public void init() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, null, null);
        stmt = conn.createStatement();
    }
 
    /**
     * 创建数据库
     * @throws SQLException
     */
    @Test
    public void createDatabase() throws SQLException {
        String sql = "create database testdb202302";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }
 
    /**
     * 查询所有数据库
     * @throws SQLException
     */
    @Test
    public void showDatabases() throws SQLException {
        String sql = "show databases";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString(1));//从1开始，查看第一列
//            System.out.println("========");
//            System.out.println(rs.getString(2));//没有第二列会报错Invalid columnIndex: 2
        }
    }
 
    /**
     * 创建表
     * @throws SQLException
     */
    @Test
    public void createTable() throws SQLException {
        String sql = "create table dept_api1(deptno int, dname string, loc string)" +
                "row format delimited fields terminated by ','";
        System.out.println("Running:"+sql);
        stmt.execute(sql);
    }
 
    @Test
    public void showTables() throws SQLException {
        String sql = "show tables";
        System.out.println("Running:"+sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString(1));
        }
    }
 
    /**
     * 查看表结构
     * @throws SQLException
     */
    @Test
    public void descTable() throws SQLException {
        String sql = "desc dept_api1";
        System.out.println("Running:"+sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()){
            // 表结构： 列名 列类型
            System.out.println(rs.getString(1)+"\t"+rs.getString(2));
        }
    }
 
    /**
     * 加载数据
     * @throws SQLException
     */
    @Test
    public void loadData() throws SQLException {
//        String filePath = "D:/test/inputemp/dept.csv";
//        String sql = "load data local inpath 'D:/test/inputemp/dept.csv' overwrite into table dept_api";
//        String sql = "LOAD DATA LOCAL INPATH 'D:\\test\\inputemp\\dept.csv' " + "OVERWRITE INTO TABLE dept_api";
        // 注意：这里相当于连接了hiveserver2的客户端，hiverserver2在linux上
        // 路径为Linux的本地文件，用windows路径会报错
        String sql = "LOAD DATA LOCAL INPATH '/home/hadoop/dept.csv' " + "OVERWRITE INTO TABLE dept_api1";
        System.out.println("Running:"+sql);
        stmt.execute(sql);
    }
 
    /**
     * 统计记录数
     * @throws SQLException
     */
    @Test
    public void countData() throws SQLException {
        String sql = "select count(1) from invites";
        System.out.println("Running:"+sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getInt(1));
        }
    }

    @Test
    public void selectData() throws SQLException {
        String sql = "select * from default.invites";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            final int count = ((HiveQueryResultSet) rs).getMetaData().getColumnCount();
            for(int i = 1; i <= count; i++) {
                final Object value = ((HiveQueryResultSet) rs).getObject(i);
                System.out.println(value);
                final String name = ((HiveQueryResultSet) rs).getMetaData().getColumnName(i);
                System.out.println("columnName = " + name);
            }
        }
    }

    @Test
    public void selectSpecifiedIndexData() throws SQLException {
        String sql = "select * from default.invites order by ds limit 10,1";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            final int count = ((HiveQueryResultSet) rs).getMetaData().getColumnCount();
            for(int i = 1; i <= count; i++) {
                final Object object = ((HiveQueryResultSet) rs).getObject(i);
                System.out.println(object);
            }
        }
    }

 
    /**
     * 删除数据库
     * @throws SQLException
     */
    @Test
    public void dropDB() throws SQLException {
        String sql = "drop database if exists testdb1";
        System.out.println("Running:"+sql);
        stmt.execute(sql);
    }
 
    /**
     * 删除表
     * @throws SQLException
     */
    @Test
    public void deleteTable() throws SQLException {
        String sql = "drop table if exists dept_api1";
        System.out.println("Running:"+sql);
        stmt.execute(sql);
    }
 
 
    @After
    public void destory() throws SQLException {
        if(rs !=null){
            rs.close();
        }
        if(stmt != null){
            stmt.close();
        }
        if(conn !=null){
            conn.close();
        }
    }
 
}