package com.zz.controller;/**
 * @Description: 描述
 * @Author: Bsea
 * @CreateDate: 2019/11/22
 */

import com.zz.config.SystemConfig;
import com.zz.util.KeyUtil;
import com.zz.util.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: Bsea
 * @CreateDate: 2019/11/22$ 21:35$
 */
@Controller
@RequestMapping("url")
@Slf4j
public class  URLController {
    //个人中心
    @RequestMapping("t1")
    public void tot1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String code = req.getParameter("code");
        Map<String, String> data = WeChatUtil.getUnionId(code);
        String token= KeyUtil.genUniqueKey();
        req.getSession().setAttribute(token, data);
        res.sendRedirect(SystemConfig.DOMAIN + "myinfo.html?token=" + token);
    }
    //个人订制
    @RequestMapping("t2")
    public void tot2(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String code = req.getParameter("code");
        Map<String, String> data = WeChatUtil.getUnionId(code);
        String token= KeyUtil.genUniqueKey();
        req.getSession().setAttribute(token, data);
        res.sendRedirect(SystemConfig.DOMAIN + "template.html?token=" + token);
    }

    //首页
    @RequestMapping("t3")
    public void tot3(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String code = req.getParameter("code");
        Map<String, String> data = WeChatUtil.getUnionId(code);
        String token= KeyUtil.genUniqueKey();
        req.getSession().setAttribute(token, data);
        res.sendRedirect(SystemConfig.DOMAIN + "index.html?token=" + token);
    }

    //注册
    @RequestMapping("t4")
    public void tot4(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String code = req.getParameter("code");
        Map<String, String> data = WeChatUtil.getUnionId(code);
        //本地测试
//        Map<String, String> data=new HashMap<>();
//        data.put("openid", "3453777");
        String token= KeyUtil.genUniqueKey();
        req.getSession().setAttribute(token, data);
        res.sendRedirect(SystemConfig.DOMAIN + "addUser.html?token=" + token);
    }
}
