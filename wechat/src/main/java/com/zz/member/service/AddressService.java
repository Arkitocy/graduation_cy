package com.zz.member.service;

import com.zz.dto.MemberDTO;
import com.zz.member.entity.Address;
import com.zz.member.entity.Member;
import com.zz.member.repository.AddressRepository;
import com.zz.member.repository.MemberRepository;
import com.zz.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description: 描述
 * @Author: llf
 * @CreateDate: 2019/11/18 11:01
 */
@Slf4j
@Service
public class AddressService {
    @Resource
    AddressRepository addressRepository;
    @Resource
    MemberRepository memberRepository;

    @Resource
    MemberService memberService;

    /**
     * 增加地址
     * @param memberDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MemberDTO saveAddress (MemberDTO memberDTO){
        System.out.println("id"+memberDTO.getId());
        System.out.println("id"+memberDTO.getId());
        Member member=memberRepository.findById(memberDTO.getId()).get();
        log.info("================================");
        log.info(member.getId());
        log.info("================================");
        for(int i=0;i<memberDTO.getAddressList().size();i++){
            Address address=new Address();
            address.setId("AD"+ KeyUtil.genUniqueKey());
            address.setAddressName(memberDTO.getAddressList().get(i).getAddressName());
            address.setAddressNameKid(memberDTO.getAddressList().get(i).getAddressNameKid());
            address.setAddressNameKid2(memberDTO.getAddressList().get(i).getAddressNameKid2());
            address.setArea(memberDTO.getAddressList().get(i).getArea());
            if(true==memberDTO.getAddressList().get(i).isDefaultVal()){
                if(null==addressRepository.findAddressesBymid(memberDTO.getId())){
                    address.setDefaultVal(true);
                }else{
                    addressRepository.changeAddress(memberDTO.getId());
                    address.setDefaultVal(true);
                }
            }else if(false==memberDTO.getAddressList().get(i).isDefaultVal()){
                address.setDefaultVal(false);
            }
            address.setMobile(memberDTO.getAddressList().get(i).getMobile());
            address.setName(memberDTO.getAddressList().get(i).getName());
            SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date creatTime =new Date();
            address.setCreatTime(s.format(creatTime));
            address.setUpdateTime(s.format(creatTime));
            address.setMemberId(member);

            addressRepository.save(address);
            member.getAddressList().add(address);
        }
        memberRepository.save(member);
        BeanUtils.copyProperties(member,memberDTO);
        return memberDTO;
    }

    /**
     * 删除地址
     * @param memberDTO
     * @return
     */
    public MemberDTO deleteAddress (MemberDTO memberDTO){
        Member member=memberRepository.findById(memberDTO.getId()).get();
        List<Address> addressList=memberDTO.getAddressList();
        for(int i=0;i<addressList.size();i++){
            String addressId=addressList.get(i).getId();
            Address address=addressRepository.findById(addressId).get();
            member.getAddressList().remove(address);
            addressRepository.deleteById(addressId);
        }
        memberRepository.save(member);
        BeanUtils.copyProperties(member,memberDTO);
        return memberDTO;
    }

    /**
     * 修改地址
     * @param memberDTO
     * @return
     */
    @Transactional
    public MemberDTO updateAddress (MemberDTO memberDTO){
        Member member=memberRepository.findById(memberDTO.getId()).get();
        List<Address> addressList=memberDTO.getAddressList();
        for(int i=0;i<addressList.size();i++){
            String addressId=addressList.get(i).getId();
            System.out.println(addressId);
            Address address=addressRepository.findById(addressId).get();
            Address adr=new Address();
            BeanUtils.copyProperties(memberDTO.getAddressList().get(i),adr);
            if(address.isDefaultVal()!=adr.isDefaultVal()){
                if(true==adr.isDefaultVal()){
                    addressRepository.changeAddress(memberDTO.getId());
                }
            }
            System.out.println("----name-"+adr.getName());
            SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date updateTime =new Date();
            adr.setUpdateTime(s.format(updateTime));
            adr.setMemberId(member);
            adr.setCreatTime(address.getCreatTime());
            member.getAddressList().add(addressRepository.save(adr));
        }
        memberRepository.save(member);
        BeanUtils.copyProperties(member,memberDTO);
        return memberDTO;
    }

    /**
     * 根据id查找地址
     * @param id
     * @return
     */
    public Address findAddressByid(String id){
        return addressRepository.findById(id).get();
    }

    /**
     * 根据mid查找
     * @param mid
     * @return
     */
    public List<Address> AddressAllByMid(String mid){
        return addressRepository.findAddressesAllBymid(mid);
    }

    /**
     * 根据mid查询默认地址
     */

    public Address addressDefaultVal(String mid){
        return addressRepository.findAddressesBymid(mid);
    }
}
