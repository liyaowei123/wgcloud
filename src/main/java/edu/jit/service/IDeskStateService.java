package edu.jit.service;

import edu.jit.model.DeskState;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.ui.Model;

import java.util.List;

/**
 * nullService接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface IDeskStateService extends IService<DeskState> {


    List<DeskState> getDeskListByName(String hostname);
    void initData(Model model);
}
