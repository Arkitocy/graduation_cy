package com.zz.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * @Description: 描述
 * @Author: llf
 * @CreateDate: 2019/11/18 9:58
 */
//@Entity(name = "xsz_memberMG_tb")
@Entity(name = "tb_membermg")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Member {
    /**
     * 主键 （openId)
     */
    @Id
    @Column(length=255)
    private String id;
    /**
     * 名字
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private String age;
    /**
     *创建者
     */
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
     * 地址
     */
    @JsonIgnoreProperties({"memberId"})
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="memberId",orphanRemoval =true)
    private List<Address> addressList;
}
