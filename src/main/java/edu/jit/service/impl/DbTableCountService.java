package edu.jit.service.impl;

import edu.jit.model.DbTableCount;
import edu.jit.mapper.DbTableCountMapper;
import edu.jit.service.IDbTableCountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class DbTableCountService extends ServiceImpl<DbTableCountMapper , DbTableCount> implements IDbTableCountService {

    @Resource
    private DbTableCountMapper dbTableCountMapper;

    @Override
    public List<DbTableCount> getCountListByTableId(String id) {
        return null;
    }

    @Override
    public Integer getTotalTableCount() {
        return dbTableCountMapper.queryTotalTableCount();
    }
}
