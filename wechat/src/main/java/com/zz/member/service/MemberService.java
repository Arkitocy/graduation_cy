package com.zz.member.service;

import com.zz.dto.MemberDTO;
import com.zz.member.entity.Address;
import com.zz.member.entity.Member;
import com.zz.member.repository.MemberRepository;
import com.zz.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 描述
 * @Author: llf
 * @CreateDate: 2019/11/18 11:00
 */
@Slf4j
@Service
public class MemberService {
    @Resource
    MemberRepository memberRepository;

    /**
     * 查询会员
     * @param page
     * @param size
     * @return
     */
    public Page<Member> findAllMember(int page, int size ){
        Sort sort=new Sort(Sort.Direction.DESC,"updateDate");
        Pageable pageable= PageRequest.of(page,size,sort);
        return memberRepository.findAll(pageable);
    }
    /**
     * 修改会员
     * @param memberDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int update(MemberDTO memberDTO){
        Member member=new Member();
        BeanUtils.copyProperties(memberDTO,member);
        Date date=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        member.setUpdateDate(s.format(date));
        return memberRepository.updateMember(member);
    }

    /**
     * 对会员信息进行增加
     * @param memberDTO
     * @return
     */
    public MemberDTO addMember(MemberDTO memberDTO){
        Member member=new Member();
        BeanUtils.copyProperties(memberDTO,member);
        System.out.println(memberDTO.getPhone());
        /**
         * openid 后期存入
         */
        member.setId(memberDTO.getId());
        Date date=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        member.setCreatTime(s.format(date));
        member.setUpdateDate(s.format(date));
        log.info("addMember--age--"+member.getAge());
        log.info("addMember--phone--"+member.getPhone());
        BeanUtils.copyProperties(member,memberDTO);
        Address address=new Address();
        address.setId(KeyUtil.genUniqueKey());
        List<Address> addressList=new ArrayList<Address>();
        addressList.add(address);
        member.setAddressList(addressList);
        memberRepository.save(member);
        return memberDTO;
    }

    /**
     * 删除会员信息
     * @param id
     */
    public void deleteMember(String id){
        memberRepository.deleteById(id);
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public Member findById(String id){
        return memberRepository.findById(id).get();
    }

    public Page<Member> findByName(String name,int page,int size){
        Pageable pageable= PageRequest.of(page,size);
        return memberRepository.findByName(name,pageable);
    }
}
