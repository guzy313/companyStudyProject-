package my.dao.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@SuppressWarnings("all")
public class JDBCUtilsForDruid {
    private static DataSource dataSource ;

    //初始化Druid连接池数据源
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 从数据源中获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭连接释放资源(并非真断开，只是放回连接池)
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
