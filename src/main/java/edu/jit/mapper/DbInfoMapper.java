package edu.jit.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.DbInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.Serializable;
import java.util.List;

/**
 * nullMapper接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
//数据监控-数据源管理
public interface DbInfoMapper extends BaseMapper<DbInfo> {
    @Select("select ID id,DBTYPE dbType,USER user,PASSWD passwd,IP ip,PORT port," +
            "CREATE_TIME createTime,DBNAME dbName,DB_STATE dbState,ALIAS_NAME aliasName from DB_INFO")
    List<DbInfo> selectList(Wrapper<DbInfo> queryWrapper);

    @Select("select ID id,DBTYPE dbType,USER user,PASSWD passwd,IP ip,PORT port," +
            "CREATE_TIME createTime,DBNAME dbName,DB_STATE dbState,ALIAS_NAME aliasName" +
            " from DB_INFO where ID=#{id}")
    DbInfo selectById(Serializable id);

    @Update("update DB_INFO set DBTYPE=#{dbType},USER=#{user},PASSWD=#{passwd},IP=#{ip}," +
            "PORT=#{port},DBNAME=#{dbName},ALIAS_NAME=#{aliasName} where ID=#{id}")
    void updateDbInfoById(DbInfo dbInfo);

    @Insert("insert into DB_INFO (ID,DBTYPE,USER,PASSWD,IP,PORT,DBNAME,ALIAS_NAME,CREATE_TIME) " +
            "values(#{id},#{dbType},#{user},#{passwd},#{ip},#{port},#{dbName},#{aliasName},#{createTime})")
    int insert(DbInfo entity);

    @Delete("delete from DB_INFO where ID=#{id}")
    void deleteDbInfoById(Serializable id);

    @Select("select ID id,ALIAS_NAME aliasName from DB_INFO")
    List<DbInfo> queryAliasAndIdList();

    @Select("select count(1) from DB_INFO")
    Integer queryDbInfoCount();
}
