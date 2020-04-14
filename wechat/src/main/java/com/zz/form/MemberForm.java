package com.zz.form;

import lombok.Data;


/**
 * @Description: 描述
 * @Author: llf
 * @CreateDate: 2019/11/18 11:43
 */
@Data
public class MemberForm {
    private String id;
    private String name;
    private String sex;
    private String age;
    private String createBy;
    /**
     * 更改者
     */
    private String updateBy;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    private int pagesize;
    private int pageno;


}
