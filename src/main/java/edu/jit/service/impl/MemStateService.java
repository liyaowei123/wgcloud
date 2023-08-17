package edu.jit.service.impl;

import edu.jit.model.MemState;
import edu.jit.mapper.MemStateMapper;
import edu.jit.service.IMemStateService;
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
public class MemStateService extends ServiceImpl<MemStateMapper , MemState> implements IMemStateService {

    @Resource
    private MemStateMapper memStateMapper;

    @Override
    public List<MemState> getMemStateListByName(String hostname, String dateNow) {
        return memStateMapper.queryMemStateListByName(hostname,dateNow);
    }
}
