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
@TableName("INTRUSION_INFO")
public class IntrusionInfo {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("hostName")
    @ApiModelProperty(value = "")
    private String hostName;

    @TableField("lsmod")
    @ApiModelProperty(value = "")
    private String lsmod;

    @TableField("passwdInfo")
    @ApiModelProperty(value = "")
    private String passwdInfo;

    @TableField("crontab")
    @ApiModelProperty(value = "")
    private String crontab;

    @TableField("promisc")
    @ApiModelProperty(value = "")
    private String promisc;

    @TableField("rpcinfo")
    @ApiModelProperty(value = "")
    private String rpcinfo;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

}
