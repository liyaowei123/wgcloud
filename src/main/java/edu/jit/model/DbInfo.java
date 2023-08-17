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
@TableName("DB_INFO")
public class DbInfo {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("dbtype")
    @ApiModelProperty(value = "")
    private String dbType;

    @TableField("user")
    @ApiModelProperty(value = "")
    private String user;

    @TableField("passwd")
    @ApiModelProperty(value = "")
    private String passwd;

    @TableField("ip")
    @ApiModelProperty(value = "")
    private String ip;

    @TableField("port")
    @ApiModelProperty(value = "")
    private String port;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

    @TableField("dbname")
    @ApiModelProperty(value = "")
    private String dbName;

    @TableField("dbState")
    @ApiModelProperty(value = "")
    private String dbState;

    @TableField("aliasName")
    @ApiModelProperty(value = "")
    private String aliasName;

}
