package edu.jit.service;

import edu.jit.model.HeathMonitor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * nullService接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface IHeathMonitorService extends IService<HeathMonitor> {


    void removeHeathById(String id);

    Integer getServiceCount();

    Integer getErrServiceCount();
}
