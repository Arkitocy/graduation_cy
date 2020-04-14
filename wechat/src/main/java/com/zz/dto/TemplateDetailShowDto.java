package com.zz.dto;

import com.zz.template.entity.Attribute;
import lombok.Data;

import java.util.List;

/**
 * @author zsj55
 */
@Data
public class TemplateDetailShowDto {
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

    private List<Attribute> attributeList;
}
