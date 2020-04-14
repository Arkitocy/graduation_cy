package com.zz.member.controller;

import com.zz.dto.MemberDTO;
import com.zz.exception.DataValidationException;
import com.zz.form.AddressForm;
import com.zz.form.MemberForm;
import com.zz.member.service.AddressService;
import com.zz.member.service.MemberService;
import com.zz.util.RedisTemplateService;
import com.zz.util.FormConvertDto;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * @Description: 后台controller
 * @Author: llf
 * @CreateDate: 2019/11/18 19:47
 */
@RestController
@RequestMapping("member")
@Slf4j
public class MemberController {
    @Resource
    MemberService memberService;
    @Resource
    AddressService addressService;

    /**
     * 增加会员
     * @param memberForm
     * @param bindingResult
     * @return
     */
    @PostMapping("addMember")
    public ResponseData addMember(@Valid @RequestBody MemberForm memberForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【新建会员】参数不正确 ，noteForm{}");
            throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        MemberDTO memberDTO= FormConvertDto.convert(memberForm);
        if (null==memberDTO.getName()) {
            log.error("【新建会员】用户名不能为空");
            throw new DataValidationException("用户名不能为空");
        }
        return ResponseDataUtil.success("添加成功",memberService.addMember(memberDTO));
    }

    /**
     * 修改会员信息
     * @param memberForm
     * @return
     */
    @PostMapping("updateMember")
    public ResponseData updateMember(MemberForm memberForm){
        MemberDTO memberDTO= FormConvertDto.convert(memberForm);
        if(memberService.update(memberDTO)>0){
            return ResponseDataUtil.success("修改成功");
        }else {
            return ResponseDataUtil.failure(200,"修改失败");
        }
    }

    /**
     * 删除会员信息
     * @param id
     * @return
     */
    @PostMapping("deleteMember/{id}")
    public void deleteMember(@PathVariable("id") String id){
        memberService.deleteMember(id);
    }

    /**
     * 查询所有会员信息
     * @param memberForm
     * @return
     */
    @GetMapping("showAllMember")
    public ResponseData showAllMember(MemberForm memberForm){
        return ResponseDataUtil.success("查询成功",memberService.findAllMember(memberForm.getPageno(),memberForm.getPagesize()));
    }

    /**
     * 根据name查会员信息
     * @param memberForm
     * @return
     */
    @PostMapping("findMemberByName")
    public ResponseData findMemberByName(MemberForm memberForm){
        return ResponseDataUtil.success("查询成功",memberService.findByName(memberForm.getName(),memberForm.getPageno(),memberForm.getPagesize()));
    }

    /**
     * 根据id显示会员信息
     * @param id
     * @return
     */
    @GetMapping("findMember/{id}")
    public ResponseData findMember(@PathVariable("id") String id){
        return ResponseDataUtil.success("详情",memberService.findById(id));
    }



    /**
     * 增加地址
     * @param addressForm
     * @param bindingResult
     * @return
     */
    @PostMapping("addAddress")
    public ResponseData addAddress(@Valid AddressForm addressForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【增加地址】参数不正确 ，noteForm{}");
            throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        MemberDTO memberDTO= FormConvertDto.convert2(addressForm);
        return ResponseDataUtil.success("添加成功",addressService.saveAddress(memberDTO));
    }

    /**
     * 修改地址信息
     * @param addressForm
     * @return
     */
    @PostMapping("updateAddress")
    public ResponseData updateAddress(AddressForm addressForm){
        MemberDTO memberDTO= FormConvertDto.convert2(addressForm);
        if (null==memberDTO.getId()) {
            log.error("【修改地址】用户id不能为空");
            throw new DataValidationException("用户id不能为空");
        }
        if(null!=addressService.updateAddress(memberDTO)){
            return ResponseDataUtil.success("修改成功");
        }else {
            return ResponseDataUtil.failure(200,"修改失败");
        }
    }

    /**
     * 删除地址信息
     * @param addressForm
     * @param bindingResult
     * @return
     */
    @PostMapping("deleteAddress")
    public ResponseData deleteAddress(@Valid AddressForm addressForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【删除地址】参数不正确 ，noteForm{}");
            throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        MemberDTO memberDTO= FormConvertDto.convert2(addressForm);
        if (null==memberDTO.getId()) {
            log.error("【删除地址】用户id不能为空");
            throw new DataValidationException("用户id不能为空");
        }
        addressService.deleteAddress(memberDTO);
        return ResponseDataUtil.success("删除成功");
    }


}