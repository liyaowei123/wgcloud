package edu.jit.service.impl;

import edu.jit.model.TbUserInfo;
import edu.jit.mapper.TbUserInfoMapper;
import edu.jit.service.ITbUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class TbUserInfoService extends ServiceImpl<TbUserInfoMapper , TbUserInfo> implements ITbUserInfoService {

    @Resource
    private TbUserInfoMapper tbUserInfoMapper;

    @Override
    public TbUserInfo login(String username, String password) {
        return tbUserInfoMapper.queryUserByNameAndPwd(username,password);
    }

    @Override
    public void updateUserPwd(String username, String pwd) {
        tbUserInfoMapper.updatePwd(username,pwd);
    }

    @Override
    public void regex(String username, String password) {
        //1.如果用户名已存在，则不能注册，需要提前查询
        TbUserInfo userInfo=tbUserInfoMapper.queryUserByName(username);
        if(null!=userInfo){
            //查出来不是空，代表用户名已存在
            return;
        }
        //2.用户名不存在时，才允许注册
        TbUserInfo user=new TbUserInfo();
        user.setUsername(username);
        user.setPassword(password);
        tbUserInfoMapper.insert(user);
    }
}
