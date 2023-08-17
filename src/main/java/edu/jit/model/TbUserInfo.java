package edu.jit.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * null
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Data
@ApiModel
@TableName("tb_user_info")
public class TbUserInfo {

    @TableField("id")
    @ApiModelProperty(value = "")
    private Integer id;

    @TableField("username")
    @ApiModelProperty(value = "登录的用户名")
    private String username;

    @TableField("password")
    @ApiModelProperty(value = "登录的密码")
    private String password;

}
