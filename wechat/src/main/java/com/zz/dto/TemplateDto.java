package com.zz.dto;

import com.zz.template.entity.Attribute;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dell
 * @Description：描述
 * @CreateDate: 2019/11/3
 */
@Data

public class TemplateDto {
    private String id;
    private String name;
    /**
     * 尺寸
     */
    private String size;
    /**
     * 图片1id
     */
    private String picture1;
    /**
     * 图片2id
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
    /**
     * 属性
     */
    private List<Attribute> attributesList=new ArrayList<>();

}
