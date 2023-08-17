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
@TableName("HOST_INFO")
public class HostInfo {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("ip")
    @ApiModelProperty(value = "")
    private String ip;

    @TableField("port")
    @ApiModelProperty(value = "")
    private String port;

    @TableField("root")
    @ApiModelProperty(value = "")
    private String root;

    @TableField("passwd")
    @ApiModelProperty(value = "")
    private String passwd;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

    @TableField("remark")
    @ApiModelProperty(value = "")
    private String remark;

}
