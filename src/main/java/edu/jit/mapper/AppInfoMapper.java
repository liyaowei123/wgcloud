package edu.jit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.AppInfo;
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
//进程管理
public interface AppInfoMapper extends BaseMapper<AppInfo> {

    @Select("select ID id,HOST_NAME hostname,APP_PID appPid,CREATE_TIME createTime," +
            "APP_NAME appName,CPU_PER cpuPer,MEM_PER memPer,APP_TYPE appType," +
            "STATE state from APP_INFO")
    List<AppInfo> queryAppInfoList();
    @Select("select ID id,HOST_NAME hostname,APP_PID appPid,CREATE_TIME createTime," +
            "APP_NAME appName,CPU_PER cpuPer,MEM_PER memPer,APP_TYPE appType," +
            "STATE state from APP_INFO where HOST_NAME=#{hostname}")
    List<AppInfo> queryAppInfoListByName(String hostname);

    @Insert("insert into APP_INFO (ID,HOST_NAME,APP_PID,CREATE_TIME,APP_NAME,APP_TYPE) " +
            " values(#{id},#{hostname},#{appPid},#{createTime},#{appName},#{appType})")
    int insert(AppInfo entity);

    @Select("select ID id,HOST_NAME hostname,APP_PID appPid,CREATE_TIME createTime," +
            "APP_NAME appName,CPU_PER cpuPer,MEM_PER memPer,APP_TYPE appType," +
            "STATE state from APP_INFO where ID=#{id}")
    AppInfo selectById(Serializable id);

    @Update("update APP_INFO set HOST_NAME=#{hostname},APP_PID=#{appPid}," +
            "APP_TYPE=#{appType},APP_NAME=#{appName} where ID=#{id}")
    int updateById(AppInfo entity);

    @Delete("delete from APP_INFO where ID=#{id}")
    int deleteAppById(Serializable id);

    @Select("select count(1) from APP_INFO")
    Integer queryAppCount();

    @Select("select count(1) from APP_INFO where CPU_PER>90")
    Integer queryAppMaxCount();
}
