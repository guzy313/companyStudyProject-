package my.dao.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtilsForDruid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BasicDao<T> {

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * dml操作
     * @param sql
     * @param params
     * @return int 操作影响的数据条数
     */
    public int update(String sql,Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsForDruid.getConnection();
            return queryRunner.update(sql,params,connection);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsForDruid.close(connection,null,null);
        }
    }

    /**
     * 查询多条记录
     * @param sql
     * @param cls
     * @param parameters
     * @return List<T>
     */
    public List<T> querys(String sql,Class<T> cls,Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsForDruid.getConnection();
            return queryRunner.query(connection,sql,new BeanListHandler<>(cls),parameters);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsForDruid.close(connection,null,null);
        }
    }

    /**
     * 查询单条记录
     * @param sql
     * @param cls
     * @param parameters
     * @return
     */
    public T query(String sql,Class<T> cls,Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsForDruid.getConnection();
            return queryRunner.query(connection,sql,new BeanHandler<>(cls),parameters);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsForDruid.close(connection,null,null);
        }
    }

    /**
     * 单值查询
     * @param sql
     * @param parameters
     * @return Object
     */
    public Object querySimple(String sql,Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsForDruid.getConnection();
            return queryRunner.query(connection,sql,new ScalarHandler<>(),parameters);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsForDruid.close(connection,null,null);
        }
    }


}
