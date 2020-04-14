package com.zz.template.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author zsj55
 */
@Entity(name = "tb_template")
@Getter
@Setter
public class Template {

    /**
     * 模板id
     */
    @Id
    @Column(length = 50)
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 尺寸
     */
    private String size;
    /**
     * 正面图片id
     */
    private String picture1;
    /**
     * 反面图片id
     */
    private String picture2;
    /**
     * 材质
     */
    private String material;
    /**
     * 重量
     */
    private String weight;
    /**
     * 介绍
     */
    private String introduce;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Attribute> attributeList;
}
