package com.zz.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author mdw
 * @CreateDate2019/12/2
 */
@Data
public class OrderDetailForm {



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
    /**
     * 产品价格
     */
    private BigDecimal productPrice;
    /**
     * 产品数量
     */
    private int productQuantity;

}
