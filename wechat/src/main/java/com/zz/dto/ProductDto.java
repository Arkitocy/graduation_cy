
package com.zz.dto;

import java.math.BigDecimal;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月18日 上午9:55:24 
 */

public class ProductDto {
	private String name;

	private BigDecimal price;

	private String detail;

	private String size;

	private String material;

	private String weight;

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
	
	
	

	
	
}
