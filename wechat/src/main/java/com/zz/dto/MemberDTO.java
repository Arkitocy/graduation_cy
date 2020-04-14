package com.zz.dto;

import com.zz.member.entity.Address;
import lombok.Data;

import java.util.List;

/**
 * @Description: 描述
 * @Author: llf
 * @CreateDate: 2019/11/18 11:24
 */
@Data
public class MemberDTO {
    private String id;
    private String name;
    private String sex;
    private String age;
    private String createBy;
    /**
     * 创建会员时间
     */
    private String creatTime;
    /**
     * 更改者
     */

    private String updateBy;
    /**
     * 更改时间
     */
    private String updateDate;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 二维码
     */
    private String qrcode;


    private List<Address> addressList;
}
