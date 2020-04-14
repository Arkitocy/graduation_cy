package com.zz.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @author cy
 */
@Data
public class GoldOrderSaveForm {


    private String orderId;
    /**
     * 订单用户名字
     */
    @NotEmpty(message = "请填写用户名")
    private String buyerName;
    /**
     * 订单用户地址
     */
    @NotEmpty(message = "请填写地址")
    private String buyerAddress;
    /**
     * 订单用户id
     */
    private String buyerId;
    /**
     * 订单用户电话
     */
    @NotEmpty(message = "请填写用户地址")
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

    private String fb;

    private String templateF;

    private String templateB;
    /**
     * 状态
     */
    private int state;

    /**
     * 产品图标1
     */
    private String file;

    /**
     * 产品id
     */
    @NotEmpty(message = "请选择商品")
    private String productId;
    /**
     * 产品名字
     */
    private String productName;

    private String productPicture;
    /**
     * 产品价格
     */
    private BigDecimal productPrice;
    /**
     * 产品数量
     */
    private int productQuantity;
}
