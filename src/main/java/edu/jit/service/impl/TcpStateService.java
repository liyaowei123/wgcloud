package edu.jit.service.impl;

import edu.jit.model.TcpState;
import edu.jit.mapper.TcpStateMapper;
import edu.jit.service.ITcpStateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class TcpStateService extends ServiceImpl<TcpStateMapper , TcpState> implements ITcpStateService {

    @Resource
    private TcpStateMapper tcpStateMapper;

}
