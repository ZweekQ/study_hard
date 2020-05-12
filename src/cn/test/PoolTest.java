package cn.test;

import cn.ToolSutil.JDBCUtils;
import cn.pool.MyPool;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

/**
 * 利用自定义连接池来测试数据库的增删改查
 */
public class PoolTest {
    @Test
    public void pooltest(){
        Connection conn = null;
        PreparedStatement ps = null;
        MyPool myPool = new MyPool();
        try {
            //1.从连接池获取数据库连接对象
            conn = myPool.getConnection();
            //关闭jdbc的自动事务提交
            conn.setAutoCommit(false);
            //2.获取预编译的数据库连接对象
            String sql = "insert into customer values (null,?,?)";
            ps = conn.prepareStatement(sql);
            //3.传入参数
            for (int i=0;i<10;i++){
                ps.setString(1,"chen"+i);
                ps.setString(2,"77777"+i);
                //将sql语句打包成一个批次
                ps.addBatch();
            }
            //4.执行sql语句
            int[] res = ps.executeBatch();
            System.out.println(Arrays.toString(res));
            //5.提交事务
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //数据库连接对象，不用关闭，因为要还回连接池，如果关闭了下次就会再次开启
            JDBCUtils.close(null,ps,null);
            //归还对象
            myPool.returnConn(conn);
        }
    }
}
