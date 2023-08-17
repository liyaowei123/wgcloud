package edu.jit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.SysLoadState;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * nullMapper接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface SysLoadStateMapper extends BaseMapper<SysLoadState> {

    @Select("select ID id,HOST_NAME hostname,ONE_LOAD oneLoad,FIVE_LOAD fiveLoad," +
            "FIFTEEN_LOAD fifteenLoad,USERS users,DATE_STR dateStr,CREATE_TIME createTime " +
            " from SYS_LOAD_STATE where HOST_NAME=#{hostname} and CREATE_TIME between" +
            " concat(#{dateNow},' 00:00:00') and concat(#{dateNow},' 23:59:59')")
    List<SysLoadState> querySysStateListByName(@Param("hostname") String hostname,
                                               @Param("dateNow") String dateNow);
}
