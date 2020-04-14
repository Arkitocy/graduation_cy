
package com.zz.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @Description:
 * @Author asuslh
 * @DateTime 2019年11月18日 上午8:52:10
 */
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    private static final long serialVersionUID = -160427508140277775L;
    @Id
    @Column(length = 50)
    /*
     * 产品id
     */
    private String id;
    /*
     * 产品名称
     */
    private String name;
    /*
     * 产品价格
     */
    private BigDecimal price;
    /*
     * 产品描述
     */
    private String detail;
    /*
     * 产品尺寸
     */
    private String size;
    /*
     * 产品材质
     */
    private String material;
    /*
     * 产品重量
     */
    private String weight;

    /**
     * 产品图片
     */
    @Fetch(FetchMode.SELECT)
    @JsonIgnoreProperties({"product"})
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Picture> pictureSet = new ArrayList<Picture>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<Picture> getPictureSet() {
        return pictureSet;
    }

    public void setPictureSet(List<Picture> pictureSet) {
        this.pictureSet = pictureSet;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
