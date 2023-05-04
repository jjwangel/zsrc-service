package com.zsebank.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用账户表
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "app_account")
public class AppAccount implements Serializable {
    /** 自增主键 */
    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id ;
    /** 应用ID;如：oa、bms */
    @TableField(value = "app_id",insertStrategy = FieldStrategy.NOT_NULL,updateStrategy = FieldStrategy.NOT_NULL)
    private String appId ;
    /** 应用名称;如：综合办公管理平台 */
    @TableField(value = "app_name",insertStrategy = FieldStrategy.NOT_NULL,updateStrategy = FieldStrategy.NOT_NULL)
    private String appName ;
    /** IP白名单;如：192.168.0.1 */
    @TableField(value = "ip_white",updateStrategy = FieldStrategy.NOT_NULL,fill = FieldFill.INSERT)
    private String ipWhite ;
    /** 应用公钥 */
    @TableField(value = "app_public_key",insertStrategy = FieldStrategy.NOT_NULL,updateStrategy = FieldStrategy.NOT_NULL)
    private String appPublicKey ;
    /** 应用私钥 */
    @TableField(value = "app_private_key",insertStrategy = FieldStrategy.NOT_NULL,updateStrategy = FieldStrategy.NOT_NULL)
    private String appPrivateKey ;
    /** 额外的信息 */
    @TableField(value = "extra_info")
    private String extraInfo ;
    /** 创建时间 */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime ;
    /** 更新时间 */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime ;
}
