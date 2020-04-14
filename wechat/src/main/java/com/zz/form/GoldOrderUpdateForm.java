package com.zz.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author mdw
 * @CreateDate2019/11/27
 */
@Data
public class GoldOrderUpdateForm {
    private String id;
    /**
     * 订单用户名字
     */
    private String buyerName;
    /**
     * 订单用户地址
     */
    private String buyerAddress;
    /**
     * 订单用户id
     */
    private String buyerId;
    /**
     * 订单用户电话
     */
    private String buyerPhone;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 订单总价格
     */
    private BigDecimal totalPrice;
    /**
     * 分类
     */
    private int type;
    /**
     * 状态
     */
    private int state;
}
