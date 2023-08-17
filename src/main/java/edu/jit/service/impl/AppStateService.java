package edu.jit.service.impl;

import edu.jit.model.AppState;
import edu.jit.mapper.AppStateMapper;
import edu.jit.service.IAppStateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class AppStateService extends ServiceImpl<AppStateMapper , AppState> implements IAppStateService {

    @Resource
    private AppStateMapper appStateMapper;

}
