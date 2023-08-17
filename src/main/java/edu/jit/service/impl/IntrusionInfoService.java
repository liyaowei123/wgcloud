package edu.jit.service.impl;

import edu.jit.model.IntrusionInfo;
import edu.jit.mapper.IntrusionInfoMapper;
import edu.jit.service.IIntrusionInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class IntrusionInfoService extends ServiceImpl<IntrusionInfoMapper , IntrusionInfo> implements IIntrusionInfoService {

    @Resource
    private IntrusionInfoMapper intrusionInfoMapper;

}
