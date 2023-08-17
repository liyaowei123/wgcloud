package edu.jit.service;

import edu.jit.model.MemState;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * nullService接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface IMemStateService extends IService<MemState> {


    List<MemState> getMemStateListByName(String hostname, String dateNow);
}
