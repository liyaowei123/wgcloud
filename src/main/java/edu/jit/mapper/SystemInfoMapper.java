package edu.jit.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.SystemInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.Serializable;
import java.util.List;

/**
 * nullMapper接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface SystemInfoMapper extends BaseMapper<SystemInfo> {

    @Select("select s.ID id,s.HOST_NAME hostname,VERSION version,VERSION_DETAIL versionDetail," +
            "CPU_PER cpuPer,MEM_PER memPer,CPU_CORE_NUM cpuCoreNum,s.CREATE_TIME createTime," +
            "CPU_XH cpuXh,STATE state,REMARK remark,round(avg(USE_PER),2) diskPer from SYSTEM_INFO s " +
            "left join DESK_STATE as d on s.HOST_NAME=d.HOST_NAME group by s.HOST_NAME")
    List<SystemInfo> selectList(Wrapper<SystemInfo> queryWrapper);

    @Update("update SYSTEM_INFO set remark=#{remark} where id=#{id}")
    int updateById(SystemInfo entity);

    @Select("select ID id,HOST_NAME hostname,VERSION version,VERSION_DETAIL versionDetail," +
            "CPU_PER cpuPer,MEM_PER memPer,CPU_CORE_NUM cpuCoreNum,CREATE_TIME createTime," +
            "CPU_XH cpuXh,STATE state,REMARK remark, 10 diskPer from SYSTEM_INFO where id=#{id}")
    SystemInfo selectById(Serializable id);

    @Select("select DISTINCT HOST_NAME hostname from SYSTEM_INFO")
    List<SystemInfo> queryAllHostnames();

    @Delete("delete from SYSTEM_INFO where ID=#{id}")
    int deleteHostById(String id);
}
