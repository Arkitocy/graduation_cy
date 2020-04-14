
package com.zz.goods.controller;

import com.zz.dto.ProductDto;
import com.zz.exception.DataValidationException;
import com.zz.form.ProductForm;
import com.zz.goods.entity.Product;
import com.zz.goods.service.ProductService;
import com.zz.util.FormToDto;
import com.zz.util.KeyUtil;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月18日 上午9:06:58 
 */
@Api(value = "商品Controller")
@RestController
@RequestMapping("product")
public class ProductController {
	@Resource
	ProductService productService;
	
	@ApiOperation(value = "添加商品")
	@ApiImplicitParam(name = "productForm", value = "商品实体", required = true, dataType = "ProductForm")
	@PostMapping("add")
	public ResponseData add(@Valid ProductForm productForm, BindingResult bindingResult){
    	if(bindingResult.hasErrors()){ 
    		throw new DataValidationException("验证错误");
		}
    	ProductDto productDto= FormToDto.productFormConvert(productForm);
    	Product product=new Product();
    	BeanUtils.copyProperties(productDto, product);
    	product.setId(KeyUtil.genUniqueKey());
    	Product rs=productService.save(product);
		if(rs!=null){
			return ResponseDataUtil.success("添加成功", rs);
		}else{
			return ResponseDataUtil.failure(500, "添加失败");
		}
	}
	
	@ApiOperation(value = "删除商品", notes = "根据id删除商品")
	@ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "String",paramType="path")
	@DeleteMapping("remove/{id}")
	 public void remove(@PathVariable("id") String id){
		 productService.deleteById(id);
	 }
	
	@ApiOperation(value = "根据id修改商品")
	@ApiImplicitParam(name = "product", value = "商品实体", required = true, dataType = "Product")
	@PostMapping("update")
	public ResponseData update(@Valid ProductForm productForm,BindingResult bindingResult){
		if(bindingResult.hasErrors()){ 
    		throw new DataValidationException("验证错误");
		}
		System.out.println("productForm+++++++++++:"+productForm);
    	ProductDto productDto=FormToDto.productFormConvert(productForm);
    	Product product=new Product();
    	BeanUtils.copyProperties(productDto, product);
    	product.setId(productForm.getId());
    	Product rs=productService.save(product);
	
		if(rs!=null){
			return ResponseDataUtil.success("修改成功", rs);
		}else{
			return ResponseDataUtil.failure(500, "修改失败");
		}
	}
	@ApiOperation(value = "查看所有商品")
	@ApiImplicitParam(name = "page", value = "开始页0开始", required = true, dataType = "String", paramType = "path")
	@GetMapping("showAll/{page}/{size}")
	public Page<Product> showAll(@PathVariable("page") String page,@PathVariable("size") String size){
		return productService.showAll(Integer.parseInt(page),Integer.parseInt(size));
	}
	
	@ApiOperation(value = "根据id查看商品")
	@ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "String", paramType = "path")
	@GetMapping("findById/{id}")
	public Product findById(@PathVariable("id") String id){
		return productService.findById(id);
	}
	@ApiOperation(value = "根据id或名字查看商品")
	 @ApiImplicitParams({
	        @ApiImplicitParam(name = "id", value = "产品id", required = true, dataType = "String", paramType = "path"),
	        @ApiImplicitParam(name = "name", value = "产品", required = true, dataType = "String", paramType = "path"),
	        @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String", paramType = "path") })
	
	@GetMapping("findByIdOrName/{id}/{name}/{page}")
	public Page<Product> findByIdOrName(@PathVariable("id") String id,@PathVariable("name") String name,@PathVariable("page") String page){
		return productService.findByIdOrName(id, name, Integer.parseInt(page));
	}


	@GetMapping("getPageable/{page}")
	public Page<Product> findAllPageable(@PathVariable(name = "page") int page){
		int size = 6;
		Pageable pageable = PageRequest.of(page, size);
		return productService.findAllPageable(pageable);
	}

	@GetMapping("getNum")
	public int getNum(){
		return productService.getNum();
	}


}
