package edu.jit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.AppState;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * nullMapper接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface AppStateMapper extends BaseMapper<AppState> {

    @Select("<script>select ID id,APP_INFO_ID appInfoId,CPU_PER cpuPer," +
            "MEM_PER memPer,CREATE_TIME createTime,date_format(CREATE_TIME,'%Y-%m-%d %H:%i:%s') from APP_STATE where " +
            "APP_INFO_ID in <foreach collection='idList' item='id' open='(' close=')'>" +
            "#{id}</foreach></script>")
    List<AppState> selectBatchIds(@Param("idList") Collection<? extends Serializable> idList);
}
