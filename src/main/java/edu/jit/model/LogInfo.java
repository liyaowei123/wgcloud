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
@TableName("LOG_INFO")
public class LogInfo {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("hostName")
    @ApiModelProperty(value = "")
    private String hostname;

    @TableField("infoContent")
    @ApiModelProperty(value = "")
    private String infoContent;

    @TableField("state")
    @ApiModelProperty(value = "")
    private String state;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

}
