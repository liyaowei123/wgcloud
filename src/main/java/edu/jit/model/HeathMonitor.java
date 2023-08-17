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
@TableName("HEATH_MONITOR")
public class HeathMonitor {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("appName")
    @ApiModelProperty(value = "")
    private String appName;

    @TableField("heathUrl")
    @ApiModelProperty(value = "")
    private String heathUrl;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

    @TableField("heathStatus")
    @ApiModelProperty(value = "")
    private String heathStatus;

}
