package com.zz.goods.controller;

import com.zz.form.GoodsDetailShowForm;
import com.zz.form.GoodsShowForm;
import com.zz.goods.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zsj55
 */
@RestController
@RequestMapping("goodsController")
public class GoodsController {
    @Resource
    ProductService productService;

    @GetMapping("getById/{id}")
    public GoodsDetailShowForm getById(@PathVariable("id") String id){
        GoodsDetailShowForm goodsDetailShowForm = new GoodsDetailShowForm();
        BeanUtils.copyProperties(productService.findById1(id),goodsDetailShowForm);
        return goodsDetailShowForm;
    }

    @GetMapping("getAll")
    public GoodsShowForm getAll(){
        GoodsShowForm goodsShowForm = new GoodsShowForm();
        goodsShowForm.setGoodsShowDtoList(productService.findAll());
        return goodsShowForm;
    }
}
