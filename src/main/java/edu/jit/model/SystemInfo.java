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
@TableName("SYSTEM_INFO")
public class SystemInfo {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("hostName")
    @ApiModelProperty(value = "")
    private String hostname;

    @TableField("version")
    @ApiModelProperty(value = "")
    private String version;

    @TableField("versionDetail")
    @ApiModelProperty(value = "")
    private String versionDetail;

    @TableField("cpuPer")
    @ApiModelProperty(value = "")
    private Double cpuPer;

    @TableField("memPer")
    @ApiModelProperty(value = "")
    private Double memPer;

    @TableField("cpuCoreNum")
    @ApiModelProperty(value = "")
    private String cpuCoreNum;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

    @TableField("cpuXh")
    @ApiModelProperty(value = "")
    private String cpuXh;

    @TableField("state")
    @ApiModelProperty(value = "")
    private String state;

    @TableField("remark")
    @ApiModelProperty(value = "")
    private String remark;

    private Double diskPer;
}
