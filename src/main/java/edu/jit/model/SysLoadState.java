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
@TableName("SYS_LOAD_STATE")
public class SysLoadState {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("hostName")
    @ApiModelProperty(value = "")
    private String hostName;

    @TableField("oneLoad")
    @ApiModelProperty(value = "")
    private Double oneLoad;

    @TableField("fiveLoad")
    @ApiModelProperty(value = "")
    private Double fiveLoad;

    @TableField("fifteenLoad")
    @ApiModelProperty(value = "")
    private Double fifteenLoad;

    @TableField("users")
    @ApiModelProperty(value = "")
    private String users;

    @TableField("dateStr")
    @ApiModelProperty(value = "")
    private String dateStr;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

}
