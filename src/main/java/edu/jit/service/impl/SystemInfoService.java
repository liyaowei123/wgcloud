package edu.jit.service.impl;

import edu.jit.model.SystemInfo;
import edu.jit.mapper.SystemInfoMapper;
import edu.jit.service.ISystemInfoService;
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
public class SystemInfoService extends ServiceImpl<SystemInfoMapper , SystemInfo> implements ISystemInfoService {

    @Resource
    private SystemInfoMapper systemInfoMapper;

    @Override
    public List<SystemInfo> getHostnames() {
        return systemInfoMapper.queryAllHostnames();
    }

    @Override
    public void removeHostById(String id) {
        systemInfoMapper.deleteHostById(id);
    }
}
