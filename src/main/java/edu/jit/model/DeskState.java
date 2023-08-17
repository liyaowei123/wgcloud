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
@TableName("DESK_STATE")
public class DeskState {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("hostName")
    @ApiModelProperty(value = "")
    private String hostname;

    @TableField("fileSystem")
    @ApiModelProperty(value = "")
    private String fileSystem;

    @TableField("size")
    @ApiModelProperty(value = "")
    private String size;

    @TableField("used")
    @ApiModelProperty(value = "")
    private String used;

    @TableField("avail")
    @ApiModelProperty(value = "")
    private String avail;

    @TableField("usePer")
    @ApiModelProperty(value = "")
    private String usePer;

    @TableField("dateStr")
    @ApiModelProperty(value = "")
    private String dateStr;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

}
