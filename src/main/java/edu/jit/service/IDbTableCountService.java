package edu.jit.service;

import edu.jit.model.DbTableCount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * nullService接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface IDbTableCountService extends IService<DbTableCount> {


    List<DbTableCount> getCountListByTableId(String id);

    Integer getTotalTableCount();
}
