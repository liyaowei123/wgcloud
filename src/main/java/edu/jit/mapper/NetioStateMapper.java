package edu.jit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.NetioState;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * nullMapper接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface NetioStateMapper extends BaseMapper<NetioState> {

    @Select("select ID id,HOST_NAME hostname,RXPCK rxpck,TXPCK txpck,RXBYT rxbyt," +
            "TXBYT txbyt,RXCMP rxcmp,TXCMP txcmp,DATE_STR dateStr,CREATE_TIME createTime," +
            "RXMCST rxmcst from NETIO_STATE where HOST_NAME=#{hostname} and CREATE_TIME between" +
            " concat(#{dateNow},' 00:00:00') and concat(#{dateNow},' 23:59:59')")
    List<NetioState> queryNetIoStateListByName(@Param("hostname") String hostname,
                                               @Param("dateNow") String dateNow);
}
