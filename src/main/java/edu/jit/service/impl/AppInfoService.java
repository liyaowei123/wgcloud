package edu.jit.service.impl;

import edu.jit.model.AppInfo;
import edu.jit.mapper.AppInfoMapper;
import edu.jit.service.IAppInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class AppInfoService extends ServiceImpl<AppInfoMapper , AppInfo> implements IAppInfoService {

    @Resource
    private AppInfoMapper appInfoMapper;

    @Override
    public List<AppInfo> getAppInfoListByName(String hostname) {
        if(StringUtils.isEmpty(hostname)){
            return appInfoMapper.queryAppInfoList();
        }
        return appInfoMapper.queryAppInfoListByName(hostname);
    }

    @Override
    public void removeAppById(String id) {
        appInfoMapper.deleteAppById(id);
    }

    @Override
    public Integer getAppCount() {
        return appInfoMapper.queryAppCount();
    }

    @Override
    public Integer getAppMaxCount() {
        return appInfoMapper.queryAppMaxCount();
    }
}
