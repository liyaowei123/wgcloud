package edu.jit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jit.model.TbUserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * nullMapper接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface TbUserInfoMapper extends BaseMapper<TbUserInfo> {

    @Select("select * from tb_user_info where username=#{username} and password=#{password}")
    TbUserInfo queryUserByNameAndPwd(@Param("username") String username,
                                     @Param("password") String password);

    @Update("update tb_user_info set password=#{password} where username=#{username}")
    void updatePwd(@Param("username") String username,@Param("password") String pwd);

    @Select("select * from tb_user_info where username=#{username}")
    TbUserInfo queryUserByName(String username);

    @Insert("insert into tb_user_info(username,password) values(" +
            "#{username},#{password})")
    int insert(TbUserInfo entity);
}
