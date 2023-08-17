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
@TableName("APP_INFO")
public class AppInfo {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("HOST_NAME")
    @ApiModelProperty(value = "")
    private String hostname;

    @TableField("appPid")
    @ApiModelProperty(value = "")
    private String appPid;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

    @TableField("appName")
    @ApiModelProperty(value = "")
    private String appName;

    @TableField("cpuPer")
    @ApiModelProperty(value = "")
    private Double cpuPer;

    @TableField("memPer")
    @ApiModelProperty(value = "")
    private Double memPer;

    @TableField("appType")
    @ApiModelProperty(value = "")
    private String appType;

    @TableField("state")
    @ApiModelProperty(value = "")
    private String state;

}
