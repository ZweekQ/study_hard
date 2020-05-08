package cn.login;
import cn.ToolSutil.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
//        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //1.注册驱动，2.获取数据库连接
            conn = JDBCUtils.getConnection();
            //3.获取传输器
//            if (conn != null) {
//                st = conn.createStatement();
//            }
//            //4.执行sql语句
//            String sql = "select * from user where name = '"+username+"' and password = '"+password+"'";
//            rs = st.executeQuery(sql);

            //3.利用新对象获取传输器
            /**
             * 原理就是 预编译原理，sql语句已经加载，等待添加值
             *包含？的sql叫做SQL骨架,ps对象会将sql骨架发送给数据库服务器
             *preparedStatement对象能够避开sql注入攻击，本质上因为，
             * 他把数据库的关键字当做普通字符串处理
             * 带有缓存的效率的效果，提高程序的执行效率：先把相同的sql骨架缓存下来，当下次来访问
             * 相同骨架的sql时，直接去缓存中取，而不用数据库了，提高程序执行效率
             */
            String sql = "select * from customer where name = ? and password = ?";
            if(conn != null) {
                ps = conn.prepareStatement(sql);
            }
            //动态添加参数值
            if (ps != null) {
                ps.setString(1, username);
                ps.setString(2, password);
            }
            //执行sql
            assert ps != null;
            rs = ps.executeQuery();
            //5.便利结果集
            if(rs.next()) System.out.println("登陆成功！");
            else System.out.println("登录失败！");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }

    }
}
