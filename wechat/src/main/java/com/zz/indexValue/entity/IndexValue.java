package com.zz.indexValue.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cy
 */
@Data
@Entity
@Table(name="tb_index_value")
public class IndexValue {
    @Id
    @Column(length = 50)
    private String id;
    private String pic;
    private String video;
}
