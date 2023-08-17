package edu.jit.service;

import edu.jit.model.AppInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * nullService接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface IAppInfoService extends IService<AppInfo> {


    List<AppInfo> getAppInfoListByName(String hostname);

    void removeAppById(String id);

    Integer getAppCount();

    Integer getAppMaxCount();
}
