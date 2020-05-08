package cn.login;
import cn.xy.util.JDBCUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 这类类用来模拟用户登录的过程
 * main方法用来提示用户输入
 * login用来查询数据库
 */
public class loginUser {
    public static void main(String[] args) {
        System.out.print("请输入你的账户名:");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();

        System.out.print("请输入你的密码:");
        String password = in.nextLine();;

        //完成登录过程
        login(username,password);
    }

    private static void login(String username, String password){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            //1.注册驱动，2.获取数据库连接
            conn = JDBCUtils.getConnection();
            //3.获取传输器
            if (conn != null) {
                st = conn.createStatement();
            }
            //4.执行sql语句
            String sql = "select * from user where name = '"+username+"' and password = '"+password+"'";
            rs = st.executeQuery(sql);
            //5.便利结果集
            if(rs.next()) System.out.println("登陆成功！");
            else System.out.println("登录失败！");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn,st,rs);
        }

    }
}
