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
@TableName("DB_TABLE")
public class DbTable {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("tableName")
    @ApiModelProperty(value = "")
    private String tableName;

    @TableField("whereVal")
    @ApiModelProperty(value = "")
    private String whereVal;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

    @TableField("remark")
    @ApiModelProperty(value = "")
    private String remark;

    @TableField("tableCount")
    @ApiModelProperty(value = "")
    private Long tableCount;

    @TableField("dateStr")
    @ApiModelProperty(value = "")
    private String dateStr;

    @TableField("dbinfoId")
    @ApiModelProperty(value = "")
    private String dbInfoId;

}
