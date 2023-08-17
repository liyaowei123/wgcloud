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
@TableName("APP_STATE")
public class AppState {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("appInfoId")
    @ApiModelProperty(value = "")
    private String appInfoId;

    @TableField("cpuPer")
    @ApiModelProperty(value = "")
    private Double cpuPer;

    @TableField("memPer")
    @ApiModelProperty(value = "")
    private Double memPer;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

    private String dateStr;
    private String cpuStateJson;
    private String memStateJson;
}
