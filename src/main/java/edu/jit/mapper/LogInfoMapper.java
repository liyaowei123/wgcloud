package edu.jit.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.LogInfo;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * nullMapper接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
//系统信息-日志信息
public interface LogInfoMapper extends BaseMapper<LogInfo> {

    @Select("select ID id,HOST_NAME hostname,INFO_CONTENT infoContent,STATE state," +
            "CREATE_TIME createTime from LOG_INFO")
    List<LogInfo> selectList(Wrapper<LogInfo> queryWrapper);

    @Select("select distinct substr(name,1,if(instr(name,'，')=0,length(name),instr(name,'，')-1)) hostname from " +
            "(select distinct substr(HOST_NAME,instr(HOST_NAME,'：')+1) name from LOG_INFO) a limit 1")
    LogInfo queryDefaultHostName();

    @Select("select ID id,HOST_NAME hostname,INFO_CONTENT infoContent,STATE state," +
            "CREATE_TIME createTime from LOG_INFO where ID=#{id}")
    LogInfo selectById(Serializable id);

    @Select("select count(1) from LOG_INFO")
    Integer queryLogCount();
}
