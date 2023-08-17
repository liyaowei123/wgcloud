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
@TableName("MEM_STATE")
public class MemState {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("hostName")
    @ApiModelProperty(value = "")
    private String hostName;

    @TableField("total")
    @ApiModelProperty(value = "")
    private String total;

    @TableField("used")
    @ApiModelProperty(value = "")
    private String used;

    @TableField("free")
    @ApiModelProperty(value = "")
    private String free;

    @TableField("usePer")
    @ApiModelProperty(value = "")
    private Double usePer;

    @TableField("dateStr")
    @ApiModelProperty(value = "")
    private String dateStr;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

}
