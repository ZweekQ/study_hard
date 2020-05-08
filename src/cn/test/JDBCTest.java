package cn.test;

import cn.ToolSutil.JDBCUtils;
import org.junit.Test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 用来测试JDBC的工具类
 */
public class JDBCTest {
    @Test
    public void  jdbcTest(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //1.注册驱动，获取数据库连接
            conn = JDBCUtils.getConnection();
            //2.获取传输器
            if (conn != null) {
                st = conn.createStatement();
            }
            //3.执行sql
            String sql = "select * from account";
            if(st != null) {
                rs = st.executeQuery(sql);
            }
            //4.遍历结果集
            if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.getObject(1));
                    System.out.println(rs.getObject(2));
                    System.out.println(rs.getObject(3));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn,st,rs);
        }
    }
}
