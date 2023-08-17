package edu.jit.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.HeathMonitor;
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
//服务接口检测-服务接口管理
public interface HeathMonitorMapper extends BaseMapper<HeathMonitor> {

    @Select("select ID id,APP_NAME appName,HEATH_URL heathUrl,CREATE_TIME createTime," +
            "HEATH_STATUS heathStatus from HEATH_MONITOR")
    List<HeathMonitor> selectList(Wrapper<HeathMonitor> queryWrapper);

    @Select("select ID id,APP_NAME appName,HEATH_URL heathUrl,CREATE_TIME createTime," +
            "HEATH_STATUS heathStatus from HEATH_MONITOR where ID=#{id}")
    HeathMonitor selectById(Serializable id);

    @Insert("insert into HEATH_MONITOR(ID,APP_NAME,HEATH_URL,CREATE_TIME) " +
            "values(#{id},#{appName},#{heathUrl},#{createTime})")
    int insert(HeathMonitor entity);

    @Update("update HEATH_MONITOR set APP_NAME=#{appName},HEATH_URL=#{heathUrl} where ID=#{id}")
    int updateById(HeathMonitor entity);

    @Delete("delete from HEATH_MONITOR where ID=#{id}")
    void deleteHeathById(String id);

    @Select("select count(distinct HEATH_URL) from HEATH_MONITOR")
    Integer queryServiceCount();

    @Select("select count(distinct HEATH_URL) from HEATH_MONITOR where HEATH_STATUS <> 200")
    Integer queryErrServiceCount();
}
