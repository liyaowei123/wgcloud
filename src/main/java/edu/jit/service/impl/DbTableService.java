package edu.jit.service.impl;

import edu.jit.model.DbTable;
import edu.jit.mapper.DbTableMapper;
import edu.jit.service.IDbTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class DbTableService extends ServiceImpl<DbTableMapper , DbTable> implements IDbTableService {

    @Resource
    private DbTableMapper dbTableMapper;

    @Override
    public void removeDbTableById(String id) {
        dbTableMapper.deleteTableById(id);
    }

    @Override
    public Integer getDbTableCount() {
        return dbTableMapper.queryDbTableCount();
    }

    @Override
    public Integer getTotalTableCount() {
        return dbTableMapper.queryTotalTableCount();
    }
}
