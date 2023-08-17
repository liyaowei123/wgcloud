package edu.jit.service.impl;

import edu.jit.model.SysLoadState;
import edu.jit.mapper.SysLoadStateMapper;
import edu.jit.service.ISysLoadStateService;
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
public class SysLoadStateService extends ServiceImpl<SysLoadStateMapper , SysLoadState> implements ISysLoadStateService {

    @Resource
    private SysLoadStateMapper sysLoadStateMapper;

    @Override
    public List<SysLoadState> getSysStateListByName(String hostname, String dateNow) {
        return sysLoadStateMapper.querySysStateListByName(hostname,dateNow);
    }
}
