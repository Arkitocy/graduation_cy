
package com.zz.util;

import com.zz.dto.ProductDto;
import com.zz.form.ProductForm;

import java.math.BigDecimal;


/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月18日 上午9:52:46 
 */

public class FormToDto {
	public static ProductDto productFormConvert(ProductForm productForm){
		ProductDto productDto=new ProductDto();
		productDto.setName(productForm.getName());
		productDto.setDetail(productForm.getDetail());
        BigDecimal price=new BigDecimal(productForm.getPrice()); 
		productDto.setPrice(price);
		productDto.setSize(productForm.getSize());
		productDto.setMaterial(productForm.getMaterial());
		productDto.setWeight(productForm.getWeight());
		return productDto;
		
	}
}
