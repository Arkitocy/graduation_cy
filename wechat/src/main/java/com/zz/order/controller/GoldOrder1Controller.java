package com.zz.order.controller;

import com.zz.exception.DataValidationException;
import com.zz.form.GoldOrderLookupForm;
import com.zz.form.GoldOrderSaveForm;
import com.zz.form.GoldOrderUpdateForm;
import com.zz.order.entity.OrderDetail;
import com.zz.util.RedisTemplateService;
import com.zz.order.entity.GoldOrder1;
import com.zz.order.service.OrderService;
import com.zz.util.KeyUtil;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.io.File.separator;

/**
 * @author mdw
 * @CreateDate2019/11/27
 */
@Slf4j
@RestController
@RequestMapping("GoldOrder1")
public class GoldOrder1Controller {
    @Resource
    OrderService orderService;
    @Resource
    RedisTemplateService redisTemplateService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Value("${fileUpLoadPath}")
    String filePath;

    @PostMapping("updateState/{state}/{id}")
    public int updatestate(@PathVariable("state") int state, @PathVariable("id") String id) {
        return orderService.updatestate(state, id);
    }

    @PostMapping("update")
    public int update(@Valid GoldOrderUpdateForm goldOrderUpdateForm) {
        return orderService.update(goldOrderUpdateForm);
    }

    /**
     * 根据ID删除题目
     *
     * @param id
     */
    @DeleteMapping("delete/{id}")
    public ResponseData delete(@PathVariable("id") String id) {
        Date date1 = new Date();
        GoldOrder1 goldOrder1 = orderService.findById(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = new Date();
        try {
            date2 = sdf.parse(goldOrder1.getCreateTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date1.getTime() - date2.getTime());
        if (date1.getTime() - date2.getTime() >= 7200000) {
            return ResponseDataUtil.failure("超过两个小时，删除失败");

        } else {
            orderService.deleteById(id);
            return ResponseDataUtil.success("删除成功");
        }

    }
    @PostMapping("updatestate/{id}/{state}")
    public int update(@PathVariable("state") int state,@PathVariable("id") String id) {
        return orderService.updatestate(state,id);
    }
    @DeleteMapping("masterDelete/{id}")
    public ResponseData masterdelete(@PathVariable("id") String id) {
        orderService.deleteById(id);
        return ResponseDataUtil.success("删除成功");


    }

    @PostMapping("saveOrder")
    public ResponseData saveDetail(@Valid @RequestBody GoldOrderSaveForm goldOrderSaveForm, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        GoldOrder1 goldOrder1 = new GoldOrder1();
        OrderDetail orderDetail = new OrderDetail();
        List<OrderDetail> list = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            log.error("【新建订单】参数不正确 ，goldOrderSaveForm{}");
            return ResponseDataUtil.failure(bindingResult.getFieldError().getDefaultMessage());
        }
        BeanUtils.copyProperties(goldOrderSaveForm, goldOrder1);
        BeanUtils.copyProperties(goldOrderSaveForm, orderDetail);
        //根据header中的token取得用户信息
        try {
            Map<String, String> mapdata = (Map) httpServletRequest.getSession().getAttribute(httpServletRequest.getHeader("token"));
            goldOrder1.setBuyerId(mapdata.get("openid"));
            orderDetail.setId(KeyUtil.genUniqueKey());
            String createdate = sdf.format(new Date());
            String filepath;
            System.out.println(goldOrderSaveForm);
            if (goldOrderSaveForm.getOrderId() != null) {
                goldOrder1.setId(goldOrderSaveForm.getOrderId());
                if (("0").equals(goldOrderSaveForm.getFb())) {
                    filepath = "|" + File.separator + "xszadmin" + File.separator + "userfiles" + File.separator + "customsave" + File.separator + goldOrderSaveForm.getOrderId() + "-result-A" + ".jpg" + "|" + goldOrderSaveForm.getTemplateB();
                    orderDetail.setProductPicture("|" +File.separator + "xszadmin" + File.separator + "userfiles" + File.separator + "customsave" + File.separator + goldOrderSaveForm.getOrderId() + "-result-A" + ".jpg" );
                } else if (("1").equals(goldOrderSaveForm.getFb())) {
                    filepath = "|" + goldOrderSaveForm.getTemplateF() + "|" + File.separator + "userfiles" + File.separator + "customsave" + File.separator + goldOrderSaveForm.getOrderId() + "-result-B" + ".jpg";
                    orderDetail.setProductPicture("|" +File.separator + "xszadmin" + File.separator + "userfiles" + File.separator + "customsave" + File.separator + goldOrderSaveForm.getOrderId() + "-result-B" + ".jpg" );
                } else {
                    filepath = "|" + File.separator + "xszadmin" + File.separator + "userfiles" + File.separator + "customsave" + File.separator + goldOrderSaveForm.getOrderId() + "-result-A" + ".jpg" + "|" + File.separator + "userfiles" + File.separator + "customsave" + File.separator + goldOrderSaveForm.getOrderId() + "-result-B" + ".jpg";
                    orderDetail.setProductPicture("|" +File.separator + "xszadmin" + File.separator + "userfiles" + File.separator + "customsave" + File.separator + goldOrderSaveForm.getOrderId() + "-result-A" + ".jpg" );
                }
                orderDetail.setFile(filepath);
                goldOrder1.setType(1);
            } else {
                filepath = "|";
                orderDetail.setFile(filepath);
                goldOrder1.setId(KeyUtil.genUniqueKey());
                goldOrder1.setType(0);
                orderDetail.setProductPicture(goldOrderSaveForm.getProductPicture());
            }
            orderDetail.setGoldOrder1(goldOrder1);
            list.add(orderDetail);
            goldOrder1.setOrderDetails(list);
            goldOrder1.setCreateTime(createdate);
            goldOrder1.setUpdateTime(createdate);
            goldOrder1.setState(0);
            if (orderService.sava(goldOrder1) != null) {
                return ResponseDataUtil.success("下单成功");
            } else {
                return ResponseDataUtil.failure("下单失败");
            }
        } catch (Exception e) {
            return ResponseDataUtil.failure("下单失败");
        }

    }

    @PostMapping("save")
    public GoldOrder1 save(@Valid GoldOrderUpdateForm goldOrderUpdateForm) {
        GoldOrder1 goldOrder1 = new GoldOrder1();
        BeanUtils.copyProperties(goldOrderUpdateForm, goldOrder1);
        goldOrder1.setId(KeyUtil.genUniqueKey());
        return orderService.sava(goldOrder1);
    }

    @GetMapping("findById/{id}")
    public ResponseData findById(@PathVariable("id") String id) {

        return ResponseDataUtil.success("查询成功", orderService.findById(id));

    }

    @GetMapping("findALL/{page}")
    public ResponseData findALL(@PathVariable("page") String page) {

        return ResponseDataUtil.success("查询成功", orderService.findAll(page));

    }

    @GetMapping("findAllByBuyerId/{id}/{page}")
    public ResponseData findAllByBuyerId(@PathVariable("id") String id, @PathVariable("page") String page) {
        return ResponseDataUtil.success("查询成功", orderService.findAllByBuyerId(id, page));


    }
    @GetMapping("findAllByBuyerId/{page}")
    public ResponseData findAllByBuyerId1( HttpServletRequest request, @PathVariable("page") String page) {
        Map<String, String> mapData = (Map) request.getSession().getAttribute(request.getHeader("token"));
        return ResponseDataUtil.success("查询成功", orderService.findAllByBuyerId(mapData.get("openid"), page));
//        return ResponseDataUtil.success("查询成功", orderService.findAll(page));

    }

    @GetMapping("findAllByBuyerIdOrTypeOrState/{id}/{type}/{state}/{page}")
    public ResponseData findAllByBuyerIdOrTypeOrState(@PathVariable("id") String id, @PathVariable("type") int type, @PathVariable("state") int state, @PathVariable("page") String page) {

        return ResponseDataUtil.success("查询成功", orderService.findAllByBuyerIdOrTypeOrState(id, type, state, page));

    }

    @PostMapping("findconpanyGoldOrderLookupForm/{page}")
    public ResponseData findconpanyGoldOrderLookupForm(@Valid GoldOrderLookupForm goldOrderLookupForm, @PathVariable("page") String page) {
        return ResponseDataUtil.success("查询成功", orderService.findconpanyGoldOrderLookupForm(goldOrderLookupForm.getDate1(), goldOrderLookupForm.getDate2(), goldOrderLookupForm.getBuyerId(), goldOrderLookupForm.getType(), goldOrderLookupForm.getState(), page));

    }

    @PostMapping("findAllByGoldOrderLookupForm/{page}")
    public ResponseData findAllByGoldOrderLookupForm(@Valid GoldOrderLookupForm goldOrderLookupForm, @PathVariable("page") String page) {
        return ResponseDataUtil.success("查询成功", orderService.findAllByGoldOrderLookupForm(goldOrderLookupForm.getDate1(), goldOrderLookupForm.getDate2(), goldOrderLookupForm.getBuyerId(), goldOrderLookupForm.getType(), goldOrderLookupForm.getState(), page));

    }

    @PostMapping("findAllByOrderForm/{page}")
    public ResponseData findAllByOrder(@Valid GoldOrderUpdateForm goldOrderUpdateForm, @PathVariable("page") String page) {
        return ResponseDataUtil.success("查询成功", orderService.findAllByBuyerIdOrTypeOrState(goldOrderUpdateForm.getBuyerId(), goldOrderUpdateForm.getType(), goldOrderUpdateForm.getState(), page));

    }

    @GetMapping("findAllByBuyerIdAndAndState/{state}/{page}")
    public ResponseData findAllByBuyerIdAndAndState( @PathVariable("state") int state, @PathVariable("page") String page,HttpServletRequest request) {
        Map<String, String> mapData = (Map) request.getSession().getAttribute(request.getHeader("token"));
        return ResponseDataUtil.success("查询成功", orderService.findAllByBuyerIdAndAndState(mapData.get("openid"), state, page));
//        return ResponseDataUtil.success("查询成功", orderService.findAll(page));

    }

    @GetMapping("getNum")
    public ResponseData getNum(){
        return ResponseDataUtil.success("成功",orderService.getNum());
        }

}
