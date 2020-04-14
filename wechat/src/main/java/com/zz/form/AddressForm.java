package com.zz.form;

import lombok.Data;

/**
 * @Description: 描述
 * @Author: llf
 * @CreateDate: 2019/11/22 19:06
 */
@Data
public class AddressForm {
    private String mid;
    /**
     * 地址
     */
    private String aid;

    /**
     * 联系人
     */
    private String person;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 地址行政区划（省）
     */
    private String addressName;
    /**
     * 地址子级行政区划（市）
     */
    private String addressNameKid;
    /**
     * 地址子级行政区划（县）
     */
    private String addressNameKid2;
    /**
     * 地址详情
     */
    private String area;
    /**
     * 是否为默认地址
     */
    private String defaultVal;


}
