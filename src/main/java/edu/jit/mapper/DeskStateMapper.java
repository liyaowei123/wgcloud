package edu.jit.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.DeskState;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * nullMapper接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface DeskStateMapper extends BaseMapper<DeskState> {
    @Select("select ID id,HOST_NAME hostname,FILE_SYSTEM fileSystem,SIZE size," +
            "USED used,AVAIL avail,USE_PER usePer,DATE_STR dateStr," +
            "CREATE_TIME createTime from DESK_STATE where HOST_NAME=#{hostname}")
    List<DeskState> queryDeskListByName(String hostname);
}
