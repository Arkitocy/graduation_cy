package com.zz.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author mdw
 * @CreateDate2019/11/27
 */
@Data
public class GoldOrderLookupForm {
    private String id;
    /**
     * 订单用户id
     */
    private String buyerId;

    /**
     * 时间1
     */
    private String date1;
    /**
     * 时间2
     */
    private String date2;

    /**
     * 分类
     */
    private int type;
    /**
     * 状态
     */
    private int state;
}
