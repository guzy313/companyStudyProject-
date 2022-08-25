package my.jdbc_.DBUtils_;

import my.jdbc_.entity.Test27;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;
import utils.JDBCUtilsForDruid;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DBUtils01 {


    @Test
    public void testDBUtilsSelect() throws SQLException {
        Connection conn = JDBCUtilsForDruid.getConnection();
        QueryRunner query = new QueryRunner();
        String sql = "select *  from test27";
        List<Test27> list = query.query(conn,sql,new BeanListHandler<>(Test27.class));
        for (Test27 t:list ) {
            System.out.println(t);
        }
    }

    @Test
    public void testDBUtilsSelect1() throws SQLException {
        Connection connection = JDBCUtilsForDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select name from test27  ";
        Object query = queryRunner.query(connection, sql, new ScalarHandler() );
        System.out.println(query);

        JDBCUtilsForDruid.close(connection,null,null);

    }


    @Test
    public void testDBUtilsCU() throws SQLException {
        Connection connection = JDBCUtilsForDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        for (int i = 0; i < 6; i++) {
            String sql = " insert into test27 values (?,?) ";
           int a = queryRunner.execute(connection,sql,i,"name"+i);
            System.out.println(a);
        }
        System.out.println("数据操作成功");
        JDBCUtilsForDruid.close(connection,null,null);
    }


    @Test
    public void test(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(new Date()));

    }


}
