package my.jdbc_.test;

import org.junit.jupiter.api.Test;
import utils.JDBCUtilsForC3p0;
import utils.JDBCUtilsForDruid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("all")
public class DruidUse01 {
    public static void main(String[] args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select *  from  users ";
        try {
            connection = JDBCUtilsForDruid.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("name") + "\t" +
                        resultSet.getString("address"));
            }
        }catch (SQLException e){

        }finally {
            JDBCUtilsForDruid.close(connection,preparedStatement,resultSet);
        }
    }

    @Test
    public void testc3p0(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select *  from  users ";
        try {
            connection = JDBCUtilsForC3p0.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("name") + "\t" +
                resultSet.getString("address"));
            }
        }catch (SQLException e){

        }finally {
            JDBCUtilsForC3p0.close(connection,preparedStatement,resultSet);
        }
    }

}
