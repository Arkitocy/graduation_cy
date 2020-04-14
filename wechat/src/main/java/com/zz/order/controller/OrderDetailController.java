package com.zz.order.controller;

import com.zz.form.OrderDetailForm;
import com.zz.order.entity.GoldOrder1;
import com.zz.order.entity.OrderDetail;
import com.zz.order.service.OrderDetailService;
import com.zz.order.service.OrderService;
import com.zz.util.KeyUtil;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author mdw
 * @CreateDate2019/12/2
 */
@RestController
@RequestMapping("OrderDetail")
public class OrderDetailController {
    @Resource
    OrderDetailService orderDetailService;
    @Resource
    OrderService orderService;
    @PostMapping("update")
    public int update( @Valid OrderDetailForm orderDetailForm){
        return orderDetailService.update(orderDetailForm);
    }
    @GetMapping("findById/{id}")
    public ResponseData findById(@PathVariable("id") String id)  {

        return ResponseDataUtil.success("查询成功",orderDetailService.findById(id));

    }
    @PostMapping("saveDetail/{id}")
    public OrderDetail saveDetail(@PathVariable("id") String id, @Valid OrderDetailForm orderDetailForm){
        OrderDetail orderDetail = new OrderDetail();
        GoldOrder1 goldOrder1 = orderService.findById(id);
        if (goldOrder1==null){
            goldOrder1 = new GoldOrder1();
            goldOrder1.setId(id);
            orderService.sava(goldOrder1);
        }
        BeanUtils.copyProperties(orderDetailForm,orderDetail);
        orderDetail.setGoldOrder1(goldOrder1);
        orderDetail.setId(KeyUtil.genUniqueKey());
        return orderDetailService.sava(orderDetail);
    }

    @DeleteMapping("masterDelete/{id}")
    public ResponseData masterdelete(@PathVariable("id") String id)  {
        orderDetailService.deleteById(id);
        return ResponseDataUtil.success("删除成功");


    }

}
