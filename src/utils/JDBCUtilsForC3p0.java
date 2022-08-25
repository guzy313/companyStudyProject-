package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtilsForC3p0 {
    private static ComboPooledDataSource dataSource ;

    //初始化c3p0连接池
    static {
        dataSource = new ComboPooledDataSource("testc3p0");//通过配置文件中数据源名称读取配置
    }

    /**
     * 获取c3p0连接
     * @return Connection
     */
    public static Connection getConnection(){
       try {
           return dataSource.getConnection();
       }catch (SQLException e){
           throw new RuntimeException(e);
       }
    }

    /**
     * 关闭连接资源
     */
    public static void close(Connection connection, Statement statement,ResultSet resultSet){

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
