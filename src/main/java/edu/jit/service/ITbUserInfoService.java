package edu.jit.service;

import edu.jit.model.TbUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * nullService接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface ITbUserInfoService extends IService<TbUserInfo> {


    TbUserInfo login(String userName, String passwd);

    void updateUserPwd(String username, String pwd);

    void regex(String username, String password);
}