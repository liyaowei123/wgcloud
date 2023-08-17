package edu.jit.service.impl;

import edu.jit.model.NetioState;
import edu.jit.mapper.NetioStateMapper;
import edu.jit.service.INetioStateService;
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
public class NetioStateService extends ServiceImpl<NetioStateMapper , NetioState> implements INetioStateService {

    @Resource
    private NetioStateMapper netioStateMapper;

    @Override
    public List<NetioState> getNetIoStateListByName(String hostname, String dateNow) {
        return netioStateMapper.queryNetIoStateListByName(hostname,dateNow);
    }
}
