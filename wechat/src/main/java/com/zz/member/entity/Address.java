package com.zz.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Description: 描述
 * @Author: llf
 * @CreateDate: 2019/11/18 10:48
 */
//@Entity(name="xsz_address_tb")
@Entity(name="tb_address")
@Getter
@Setter
public class Address {
    @Id
    @Column(length = 50,name="aid")
    private String id;
    /**
     * 创建时间
     */
    private String creatTime;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 联系人
     */
    private String name;
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
    private boolean defaultVal;
    /**
     * 会员id
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="mid")
    private Member memberId;

}
