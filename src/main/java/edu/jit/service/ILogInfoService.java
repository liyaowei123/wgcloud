package edu.jit.service;

import edu.jit.model.LogInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * nullService接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface ILogInfoService extends IService<LogInfo> {


    LogInfo getDefaultHostname();

    Integer getLogCount();
}
