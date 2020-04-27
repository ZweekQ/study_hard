package cn.test;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.sql.*;


public class testone {
    @Test
    public void del(){
        Connection conn = null;
        Statement st = null;
        ResultSet rows = null;
        try{
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            String url = "jdbc:mysql://localhost:3306/jt_db";
            String user = "root";
            String password = "1234567";
            conn = DriverManager.getConnection(url,user,password);
            //3.获取传输器
            st = conn.createStatement();
            //4.执行sql语句
            String sql = "select * from account";
            rows = st.executeQuery(sql);
            //5.遍历结果集
            while (rows.next()){
                System.out.println("编号"+" "+"用户名"+" "+"价格"+" ");

                System.out.print((rows.getObject(1)) + " ");
                System.out.print((rows.getObject(2)) + " ");
                System.out.print((rows.getObject(3)) + " ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //6.资源释放
            if (conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (st != null){
                try{
                    st.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if (rows != null) {
                try {
                    rows.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
