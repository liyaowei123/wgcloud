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
@TableName("NETIO_STATE")
public class NetioState {

    @TableField("id")
    @ApiModelProperty(value = "")
    private String id;

    @TableField("hostName")
    @ApiModelProperty(value = "")
    private String hostName;

    @TableField("rxpck")
    @ApiModelProperty(value = "")
    private String rxpck;

    @TableField("txpck")
    @ApiModelProperty(value = "")
    private String txpck;

    @TableField("rxbyt")
    @ApiModelProperty(value = "")
    private String rxbyt;

    @TableField("txbyt")
    @ApiModelProperty(value = "")
    private String txbyt;

    @TableField("rxcmp")
    @ApiModelProperty(value = "")
    private String rxcmp;

    @TableField("txcmp")
    @ApiModelProperty(value = "")
    private String txcmp;

    @TableField("rxmcst")
    @ApiModelProperty(value = "")
    private String rxmcst;

    @TableField("dateStr")
    @ApiModelProperty(value = "")
    private String dateStr;

    @TableField("createTime")
    @ApiModelProperty(value = "")
    private java.util.Date createTime;

}
