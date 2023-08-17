package edu.jit.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.DbTable;
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
//数据监控-数据表管理
public interface DbTableMapper extends BaseMapper<DbTable> {

    @Select("select ID id,TABLE_NAME tableName,WHERE_VAL whereVal,CREATE_TIME createTime," +
            "REMARK remark,TABLE_COUNT tableCount,DATE_STR dateStr,DBINFO_ID dbInfoId from DB_TABLE")
    List<DbTable> selectList(Wrapper<DbTable> queryWrapper);

    @Select("select ID id,TABLE_NAME tableName,WHERE_VAL whereVal,CREATE_TIME createTime," +
            "REMARK remark,TABLE_COUNT tableCount,DATE_STR dateStr,DBINFO_ID dbInfoId" +
            " from DB_TABLE where ID=#{id}")
    DbTable selectById(Serializable id);

    @Insert("insert into DB_TABLE(ID,TABLE_NAME,WHERE_VAL,CREATE_TIME,DATE_STR,DBINFO_ID,REMARK) " +
            "values(#{id},#{tableName},#{whereVal},#{createTime},#{dateStr},#{dbInfoId},#{remark})")
    int insert(DbTable entity);

    @Update("update DB_TABLE set TABLE_NAME=#{tableName},WHERE_VAL=#{whereVal},REMARK=#{remark}," +
            "DBINFO_ID=#{dbInfoId} where ID=#{id}")
    int updateById(DbTable entity);

    @Delete("delete from DB_TABLE where ID=#{id}")
    void deleteTableById(String id);

    @Select("select count(TABLE_NAME) from DB_TABLE")
    Integer queryDbTableCount();

    @Select("select sum(TABLE_COUNT) from DB_TABLE")
    Integer queryTotalTableCount();
}
