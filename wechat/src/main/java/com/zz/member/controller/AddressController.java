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
import java.util.Map;


/**
 * @Description: 前台controller
 * @Author: llf
 * @CreateDate: 2019/11/18 19:47
 */
@RestController
@RequestMapping("address")
@Slf4j
public class AddressController {
    @Resource
    MemberService memberService;
    @Resource
    AddressService addressService;

    /**
     * 增加会员
     *
     * @param memberForm
     * @param bindingResult
     * @return
     */
    @PostMapping("addMember")
    public ResponseData addMember(@Valid @RequestBody MemberForm memberForm, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.error("【新建会员】参数不正确 ，noteForm{}");
            throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        Map<String, String> mapData = (Map) request.getSession().getAttribute(request.getHeader("token"));
        memberForm.setId(mapData.get("openid"));
        memberForm.setCreateBy(mapData.get("nickname"));
        MemberDTO memberDTO = FormConvertDto.convert(memberForm);
        if (null == memberDTO.getName()) {
            log.error("【新建会员】用户名不能为空");
            throw new DataValidationException("用户名不能为空");
        }
        return ResponseDataUtil.success("添加成功", memberService.addMember(memberDTO));
    }

    /**
     * 修改会员信息
     *
     * @param memberForm
     * @return
     */
    @PostMapping("updateMember")
    public ResponseData updateMember(@RequestBody MemberForm memberForm, HttpServletRequest request) {
        Map<String, String> mapData = (Map) request.getSession().getAttribute(request.getHeader("token"));
        memberForm.setId(mapData.get("openid"));
        memberForm.setUpdateBy(mapData.get("openid"));
        MemberDTO memberDTO = FormConvertDto.convert(memberForm);
        if (memberService.update(memberDTO) > 0) {
            return ResponseDataUtil.success("修改成功");
        } else {
            return ResponseDataUtil.failure(200, "修改失败");
        }
    }


    /**
     * 根据id显示会员信息
     *
     * @return
     */
    @GetMapping("findMember")
    public ResponseData findMember(HttpServletRequest request) {
        Map<String, String> mapData = (Map) request.getSession().getAttribute(request.getHeader("token"));
        String id = mapData.get("openid");
        return ResponseDataUtil.success("详情", memberService.findById(id));
    }

    /**
     * 根据地址id显示地址
     *
     * @param id
     * @return
     */
    @GetMapping("findAddressByid/{id}")
    public ResponseData findAddressByid(@PathVariable("id") String id) {
        return ResponseDataUtil.success("地址", addressService.findAddressByid(id));
    }

    /**
     * 根据会员id显示地址
     *
     * @return
     */
    @GetMapping("AddressAllByMid")
    public ResponseData AddressAllByMid(HttpServletRequest request) {
        log.info("token" + request.getHeader("token"));
        Map<String, String> mapData =(Map)request.getSession().getAttribute(request.getHeader("token"));
        System.out.println(mapData);
        String mid=mapData.get("openid");
        return ResponseDataUtil.success("地址", addressService.AddressAllByMid(mid));
    }

    /**
     * 增加地址
     *
     * @param addressForm
     * @param bindingResult
     * @return
     */
    @PostMapping("addAddress")
    public ResponseData addAddress(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult, HttpServletRequest request) {
        Map<String, String> mapData = (Map) request.getSession().getAttribute(request.getHeader("token"));
        System.out.println(mapData);
        addressForm.setMid(mapData.get("openid"));
        if (bindingResult.hasErrors()) {
            log.error("【新建地址】参数不正确 ，noteForm{}");
            throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        MemberDTO memberDTO = FormConvertDto.convert2(addressForm);
        return ResponseDataUtil.success("添加成功", addressService.saveAddress(memberDTO));
    }

    /**
     * 修改地址信息
     *
     * @param addressForm
     * @return
     */
    @PostMapping("updateAddress")
    public ResponseData updateAddress(@RequestBody AddressForm addressForm, HttpServletRequest request) {
        Map<String, String> mapData = (Map) request.getSession().getAttribute(request.getHeader("token"));
        addressForm.setMid(mapData.get("openid"));
        MemberDTO memberDTO = FormConvertDto.convert2(addressForm);
        if (null == memberDTO.getId()) {
            log.error("【修改地址信息】用户id不能为空");
            throw new DataValidationException("用户id不能为空");
        }
        if (null != addressService.updateAddress(memberDTO)) {
            return ResponseDataUtil.success("修改成功");
        } else {
            return ResponseDataUtil.failure(200, "修改失败");
        }
    }

    /**
     * 删除地址信息
     *
     * @param addressForm
     * @param bindingResult
     * @return
     */
    @PostMapping("deleteAddress")
    public ResponseData deleteAddress(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult,HttpServletRequest request) {
        Map<String, String> mapData = (Map) request.getSession().getAttribute(request.getHeader("token"));
        addressForm.setMid(mapData.get("openid"));
        if (bindingResult.hasErrors()) {
            log.error("【删除地址信息】参数不正确 ，noteForm{}");
            throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        MemberDTO memberDTO = FormConvertDto.convert2(addressForm);
        if (null == memberDTO.getId()) {
            log.error("【删除地址】用户id不能为空");
            throw new DataValidationException("用户id不能为空");
        }
        addressService.deleteAddress(memberDTO);
        return ResponseDataUtil.success("删除成功");
    }

    /**
     * 根据会员id显示地址
     *
     * @return
     */
    @GetMapping("addressDefaultVal")
    public ResponseData addressDefaultVal(HttpServletRequest request) {
        Map<String, String> mapData = (Map) request.getSession().getAttribute(request.getHeader("token"));
        String mid=mapData.get("openid");
        return ResponseDataUtil.success("地址", addressService.addressDefaultVal(mid));
    }

}
