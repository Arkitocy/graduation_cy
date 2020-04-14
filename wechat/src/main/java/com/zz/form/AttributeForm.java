package com.zz.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author dell
 * @Description：描述
 * @CreateDate: 2019/11/3
 */
@Data
public class AttributeForm {
    private String id;

    private String name;
    /**
     * 尺寸
     */
   private BigDecimal price;
   private String templateId;

}
