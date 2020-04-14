package com.zz.goods.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.zz.form.PictureForm;
import com.zz.goods.entity.Picture;
import com.zz.goods.entity.Product;
import com.zz.goods.service.PictureService;
import com.zz.goods.service.ProductService;
import com.zz.util.KeyUtil;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("picture")
public class PictureController{
	@Resource
    PictureService pictureService;
	
	@Resource
    ProductService productService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@GetMapping("findById/{id}")
	public ResponseData findById(@PathVariable("id") String id){
		return ResponseDataUtil.success("查询成功",pictureService.findById(id));
	}
	
	@PostMapping("savePicture/{id}")
    public Picture savePicture(@PathVariable("id") String id, @Valid PictureForm pictureForm){
        Picture picture = new Picture();
        Product product=productService.findById(id);
        if (product==null){
        	product = new Product();
        	product.setId(id);
        	productService.save(product);
        }
        BeanUtils.copyProperties(pictureForm,picture);
        picture.setProduct(product);
        picture.setUploadTime(sdf.format(new Date()));
        picture.setId(KeyUtil.genUniqueKey());
        return pictureService.save(picture);
    }

	
    @PostMapping("update")
    public Picture update( @Valid PictureForm pictureForm){
    	Picture picture = new Picture();
    	 BeanUtils.copyProperties(pictureForm,picture);
    	 Product product=productService.findById(pictureForm.getProductId());
    	 picture.setUploadTime(sdf.format(new Date()));
    	 picture.setProduct(product);
        return pictureService.save(picture);
    }
    @DeleteMapping("delete/{id}")
    public ResponseData masterdelete(@PathVariable("id") String id)  {
    	pictureService.deleteById(id);
        return ResponseDataUtil.success("删除成功");


    }
	
	
	
	
}
