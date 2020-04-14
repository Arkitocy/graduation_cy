/**
 *
 */
package com.zz.order.service;


import com.zz.form.GoldOrderUpdateForm;
import com.zz.order.entity.GoldOrder1;
import com.zz.order.repository.GoldOrder1Repository;
import com.zz.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author asus
 *2019年9月23日
 */
@Service
@Transactional
public class OrderService {
    @Resource
    GoldOrder1Repository goldOrder1Repository;



    public int updatestate(int state, String id) {
        return goldOrder1Repository.updatestate(state, id);
    }


    public int update(GoldOrderUpdateForm goldOrderUpdateForm) {
        GoldOrder1 member = new GoldOrder1();
        BeanUtils.copyProperties(goldOrderUpdateForm, member);
        return goldOrder1Repository.update(member);
    }

    public GoldOrder1 sava(GoldOrder1 goldOrder1) {
        return goldOrder1Repository.save(goldOrder1);
    }

    public void deleteById(String id) {
        // TODO Auto-generated method stub
        goldOrder1Repository.deleteById(id);
    }

    public GoldOrder1 findById(String id) {
        return goldOrder1Repository.findById(id).get();
    }

//    public Page<GoldOrder1> findAllByBuyerId(String id,String page){
//        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
//
//        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt("10"), sort);
//
//        return goldOrder1Repository.findAllByBuyerId(id,pageable );
//    }
    GoldOrder1 findAllById(String buyerId){

        return goldOrder1Repository.findAllById(buyerId);
    }


    public Page<GoldOrder1> findAllByBuyerId(String id, String page) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");

        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt("10"), sort);
        Page<GoldOrder1> pageinfo = goldOrder1Repository.findAllByBuyerId(id, pageable);
        return pageinfo;
    }

    public Page<GoldOrder1> findAllByBuyerIdAndAndState(String userId, int state, String page) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt("10"), sort);
        return goldOrder1Repository.findAllByBuyerIdAndAndState(userId, state, pageable);
    }

    public Page<GoldOrder1> findAll(String page) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt("10"));
        return goldOrder1Repository.findAll(pageable);
    }
    public Page<GoldOrder1> findAllByBuyerIdOrTypeOrState(String buyerId,int Type,int State,String page){
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt("10"), sort);
        return goldOrder1Repository.findAllByBuyerIdOrTypeOrState(buyerId,Type,State,pageable);
    }
    public Page<GoldOrder1> findAllByGoldOrderLookupForm(String date1,String date2,String buyerId,int Type,int State,String page){
        Sort sort = new Sort(Sort.Direction.DESC, "create_time");
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt("10"), sort);
        return goldOrder1Repository.findGoldOrderLookupForm(date1,date2,buyerId,Type,State,pageable);
    }
    public Page<GoldOrder1> findconpanyGoldOrderLookupForm(String date1,String date2,String buyerId,int Type,int State,String page){
        Sort sort = new Sort(Sort.Direction.DESC, "create_time");
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt("10"), sort);
        return goldOrder1Repository.findconpanyGoldOrderLookupForm(date1,date2,buyerId,Type,State,pageable);
    }

    public Map<String,Object> getNum(){
        String s1="";
        String s2="";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        ArrayList<String> date = new ArrayList();
        date.add(formatter.format(currentDate));
        for (int i=6;i>-1;i--){
            date.add(DateUtil.getPastDate(i));
        }
        for (int i=1;i<date.size();i++){
            s1=s1+goldOrder1Repository.getNum0(date.get(i))+",";
            s2=s2+goldOrder1Repository.getNum1(date.get(i))+",";
        }
        Map map =new HashMap();
        map.put("0","商品,"+s1.substring(0, s1.length()-1));
        map.put("1","模板,"+s2.substring(0, s2.length()-1));
        return map;
    }
}
