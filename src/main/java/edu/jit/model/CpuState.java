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
@TableName("CPU_STATE")
public class CpuState {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("hostName")
    @ApiModelProperty(value = "")
    private String hostname;

    @TableField("user")
    @ApiModelProperty(value = "")
    private String user;

    @TableField("sys")
    @ApiModelProperty(value = "")
    private Double sys;

    @TableField("idle")
    @ApiModelProperty(value = "")
    private Double idle;

    @TableField("iowait")
    @ApiModelProperty(value = "")
    private Double iowait;

    @TableField("irq")
    @ApiModelProperty(value = "")
    private String irq;

    @TableField("soft")
    @ApiModelProperty(value = "")
    private String soft;

    @TableField("dateStr")
    @ApiModelProperty(value = "")
    private String dateStr;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

}
