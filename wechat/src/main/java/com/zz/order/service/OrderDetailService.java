package com.zz.order.service;


import com.zz.form.OrderDetailForm;
import com.zz.order.entity.GoldOrder1;
import com.zz.order.entity.OrderDetail;
import com.zz.order.repository.OrderDetailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class OrderDetailService {
    @Resource
    OrderDetailRepository orderDetailRepository;

    /**
     * @Description:  订单详情Service
     * @param
     * @return OrderDetail
     */
    public int update(OrderDetailForm goldOrderUpdateForm){
        OrderDetail member =new OrderDetail();
        BeanUtils.copyProperties(goldOrderUpdateForm,member);
        System.out.println("**#*%***"+member.getId());
        return 	orderDetailRepository.update(member);
    }
    public OrderDetail findById(String id){
        System.out.println("SubjectService quiz_id   "+ id);
        return orderDetailRepository.findById(id).get();
    }
    public OrderDetail sava(OrderDetail orderDetail){
        System.out.println(" "+ orderDetail.getProductName());
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteById(String id) {
        // TODO Auto-generated method stub
        System.out.println("***%***"+id);
        OrderDetail orderDetail = orderDetailRepository.findById(id).get();
        GoldOrder1 goldOrder1 = orderDetail.getGoldOrder1();
        goldOrder1.getOrderDetails().remove(orderDetail);
        orderDetailRepository.deleteById(id);

    }
}
