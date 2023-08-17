package edu.jit.service;

import edu.jit.model.DbTable;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * nullService接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface IDbTableService extends IService<DbTable> {


    void removeDbTableById(String id);

    Integer getDbTableCount();

    Integer getTotalTableCount();
}
