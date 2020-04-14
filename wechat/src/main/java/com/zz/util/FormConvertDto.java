package com.zz.util;

import com.zz.dto.MemberDTO;
import com.zz.form.AddressForm;
import com.zz.form.MemberForm;
import com.zz.member.entity.Address;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 描述
 * @Author: llf
 * @CreateDate: 2019/11/18 11:42
 */
@Slf4j
public class FormConvertDto {
    public static MemberDTO convert (MemberForm memberForm) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberForm.getId());
        if (null == memberForm.getAge()) {
            memberDTO.setAge("");
        } else {
            memberDTO.setAge(memberForm.getAge());
        }
        if(null==memberForm.getPhone()){
            memberDTO.setPhone("");
        }else
        {
            memberDTO.setPhone(memberForm.getPhone());
        }
        memberDTO.setName(memberForm.getName());
        memberDTO.setCreateBy(memberForm.getCreateBy());
        memberDTO.setEmail(memberForm.getEmail());
        memberDTO.setUpdateBy(memberForm.getUpdateBy());
        memberDTO.setSex(memberForm.getSex());
        return memberDTO;
    }
    public static MemberDTO convert2 (AddressForm addressForm){
        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setId(addressForm.getMid());
        Address address=new Address();
        address.setId(addressForm.getAid());
        address.setName(addressForm.getPerson());
        if(addressForm.getMobile()==null){
            address.setMobile("");
        }else{
            address.setMobile(addressForm.getMobile());
        }
        address.setAddressNameKid(addressForm.getAddressNameKid());
        address.setAddressNameKid2(addressForm.getAddressNameKid2());
        address.setArea(addressForm.getArea());
        address.setAddressName(addressForm.getAddressName());
        String result="false";
        String result2="true";
        if(addressForm.getDefaultVal()==null){
            address.setDefaultVal(false);
        }else if(addressForm.getDefaultVal().equals(result2)){
            address.setDefaultVal(true);
        }else if (addressForm.getDefaultVal().equals(result)){
            address.setDefaultVal(false);
        }
        List<Address> addressList=new ArrayList<>();
        addressList.add(address);
        memberDTO.setAddressList(addressList);
        return memberDTO;
    }
}
