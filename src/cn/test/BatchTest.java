package cn.test;
import cn.ToolSutil.JDBCUtils;
import org.junit.Test;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

/**
 * 这个类用来测试jdbc的批量处理能力
 */
public class BatchTest {
    @Test
    //用statement对象来实现批量处理
    public  void Sttest(){
        Statement st = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            //1,注册驱动 2,获取数据库链接
            long start = System.currentTimeMillis();//获取当时间的毫秒数
            conn = JDBCUtils.getConnection();
            //3.获取传输器
            if (conn != null) {
                st = conn.createStatement();
            }
            //4.执行sql
            for (int i=0;i<100;i++){
            String sql = "insert into customer values (null,'wang"+i+"',33333"+i+")";
            //把10条sql打成一个批次
            if (st != null) {
                st.addBatch(sql);
                }
            //统一把sql发送给服务器
            if(st != null) {
                st.executeBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn,st,null);
        }
    }

    //利用prepardStatement对象来完成批量处理
    @Test
    public void PSTest(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
        //1.注册驱动，获取数据库链接
        conn = JDBCUtils.getConnection();
        //3.获取传输器
            String sql = "insert into customer values (null,?,?)";
            if (conn != null) {
                //获取预编译对象preparedStatement对象
                ps = conn.prepareStatement(sql);
            }
            //设置参数
            for (int x=0;x<100;x++) {
                if (ps != null) {
                    ps.setString(1, "zhang" + x);
                    ps.setString(2, "55555" + x);
                    //把sql打包成一个批次
                    ps.addBatch();
                }
            }
        //批量提交到数据库
            if (ps != null) {
                int[] rows = ps.executeBatch();
                System.out.println(Arrays.toString(rows));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn,ps,null);
        }
    }
}
