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
@TableName("MAIL_SET")
public class MailSet {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("sendMail")
    @ApiModelProperty(value = "")
    private String sendMail;

    @TableField("fromMailName")
    @ApiModelProperty(value = "")
    private String fromMailName;

    @TableField("fromPwd")
    @ApiModelProperty(value = "")
    private String fromPwd;

    @TableField("smtpHost")
    @ApiModelProperty(value = "")
    private String smtpHost;

    @TableField("smtpPort")
    @ApiModelProperty(value = "")
    private String smtpPort;

    @TableField("smtpSsl")
    @ApiModelProperty(value = "")
    private String smtpSSL;

    @TableField("toMail")
    @ApiModelProperty(value = "")
    private String toMail;

    @TableField("cpuPer")
    @ApiModelProperty(value = "")
    private String cpuPer;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

    @TableField("memPer")
    @ApiModelProperty(value = "")
    private String memPer;

    @TableField("heathPer")
    @ApiModelProperty(value = "")
    private String heathPer;

}
