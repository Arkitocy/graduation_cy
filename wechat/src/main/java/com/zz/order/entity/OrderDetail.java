package com.zz.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "tb_order_detail")
@Data
public class OrderDetail {
    /**
     * 主键(订单Id)
     */
    @Id
    @Column(length = 50)
    private String id;


    /**
     * 产品图标1
     */
    private String file;

    /**
     * 产品id
     */
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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "goldOrderId")
    private GoldOrder1 goldOrder1;

}
