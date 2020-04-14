package com.zz.dto;

import com.zz.goods.entity.Picture;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zsj55
 */
@Data
public class GoodsDetailShowDto {
    /**
     * 名称
     */
    private String name;
    /**
     * 尺寸
     */
    private String size;
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
    private String detail;
    /**
     * 价钱
     */
    private BigDecimal price;

    private List<Picture> pictureList;
}
