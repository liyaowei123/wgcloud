package edu.jit.service.impl;

import edu.jit.model.LogInfo;
import edu.jit.mapper.LogInfoMapper;
import edu.jit.service.ILogInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class LogInfoService extends ServiceImpl<LogInfoMapper , LogInfo> implements ILogInfoService {

    @Resource
    private LogInfoMapper logInfoMapper;

    @Override
    public LogInfo getDefaultHostname() {
        return logInfoMapper.queryDefaultHostName();
    }

    @Override
    public Integer getLogCount() {
        return logInfoMapper.queryLogCount();
    }
}
