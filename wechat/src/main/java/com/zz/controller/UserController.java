package com.zz.controller;/**
 * @Description: 描述
 * @Author: Bsea
 * @CreateDate: 2019/11/22
 */

import com.zz.dto.MemberDTO;
import com.zz.exception.DataValidationException;
import com.zz.form.MemberForm;
import com.zz.member.service.MemberService;
import com.zz.util.FormConvertDto;
import com.zz.util.KeyUtil;
import com.zz.util.WeChatUtil;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: Bsea
 * @CreateDate: 2019/11/22$ 21:58$
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Resource
    MemberService memberService;
    @RequestMapping("url4")
    public  Map<String, String>  toURL4(HttpServletRequest req){
        String token=req.getParameter("token");
        log.info("token="+token);
        System.out.println(token+"***");
        Map<String, String> mapdata =(Map)req.getSession().getAttribute(token);
        return mapdata;
    }

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
        log.info("token----"+request.getHeader("token"));
        memberForm.setId(mapData.get("openid"));
        memberForm.setCreateBy(mapData.get("nickname"));
        log.info("memberForm***"+memberForm);
        MemberDTO memberDTO = FormConvertDto.convert(memberForm);
        log.info("memberDTO***"+memberDTO);
        if (null == memberDTO.getName()) {
            log.error("【新建会员】用户名不能为空");
            throw new DataValidationException("用户名不能为空");
        }
        return ResponseDataUtil.success("添加成功", memberService.addMember(memberDTO));
    }
}
