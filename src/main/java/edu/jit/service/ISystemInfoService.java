package edu.jit.service;

import edu.jit.model.SystemInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * nullService接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface ISystemInfoService extends IService<SystemInfo> {


    List<SystemInfo> getHostnames();

    void removeHostById(String id);
}
