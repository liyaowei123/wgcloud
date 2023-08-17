package edu.jit.service.impl;

import edu.jit.model.CpuState;
import edu.jit.mapper.CpuStateMapper;
import edu.jit.service.ICpuStateService;
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
public class CpuStateService extends ServiceImpl<CpuStateMapper , CpuState> implements ICpuStateService {

    @Resource
    private CpuStateMapper cpuStateMapper;

    @Override
    public List<CpuState> getCpuStateListByName(String hostname, String dateNow) {
        return cpuStateMapper.queryCpuStateListByName(hostname,dateNow);
    }
}
