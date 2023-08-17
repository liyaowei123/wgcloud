package edu.jit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.DbTableCount;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * nullMapper接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface DbTableCountMapper extends BaseMapper<DbTableCount> {
    @Select("select TABLE_COUNT tableCount from DB_TABLE_COUNT where DB_TABLE_ID=#{id} " +
            "and DATE_STR between date_sub(now(),interval 15 day) and now()")
    List<DbTableCount> queryCountListByTableId(String id);

    @Select("select sum(TABLE_COUNT) from DB_TABLE_COUNT")
    Integer queryTotalTableCount();
}
