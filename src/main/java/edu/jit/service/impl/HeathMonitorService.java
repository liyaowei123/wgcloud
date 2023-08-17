package edu.jit.service.impl;

import edu.jit.model.HeathMonitor;
import edu.jit.mapper.HeathMonitorMapper;
import edu.jit.service.IHeathMonitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class HeathMonitorService extends ServiceImpl<HeathMonitorMapper , HeathMonitor> implements IHeathMonitorService {

    @Resource
    private HeathMonitorMapper heathMonitorMapper;

    @Override
    public void removeHeathById(String id) {
        heathMonitorMapper.deleteHeathById(id);
    }

    @Override
    public Integer getServiceCount() {
        return heathMonitorMapper.queryServiceCount();
    }

    @Override
    public Integer getErrServiceCount() {
        return heathMonitorMapper.queryErrServiceCount();
    }
}
