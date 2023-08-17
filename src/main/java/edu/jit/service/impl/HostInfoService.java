package edu.jit.service.impl;

import edu.jit.model.HostInfo;
import edu.jit.mapper.HostInfoMapper;
import edu.jit.service.IHostInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class HostInfoService extends ServiceImpl<HostInfoMapper , HostInfo> implements IHostInfoService {

    @Resource
    private HostInfoMapper hostInfoMapper;

}
