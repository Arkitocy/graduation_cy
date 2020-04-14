package com.zz.template.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author zsj55
 */
@Entity(name = "tb_attribute")
@Data
public class Attribute {
    @Id
    @Column(length = 50)
    private String id;

    private String name;

    private BigDecimal price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;
}
