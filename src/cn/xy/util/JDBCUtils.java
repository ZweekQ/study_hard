package cn.xy.util;
import com.mysql.jdbc.Driver;
import java.sql.*;

/**
 * JDBC工具类
 */
public class JDBCUtils {
    //1.创建静态的工具方法
    private JDBCUtils(){
    }
    //２.提供静态的getConnection方法，封装１，２步
    public static Connection getConnection(){
        try{
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            String url = "JDBC:mysql:///jt_db";
            String user = "root";
            String password = "1234567";
            return DriverManager.getConnection(url,user,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //3.提供静态的close方法,封装第六步
    public static void close(Connection conn, Statement st, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st != null){
            try{
                st.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if (rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
