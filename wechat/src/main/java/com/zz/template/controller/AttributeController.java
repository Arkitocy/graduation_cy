package com.zz.template.controller;


import com.zz.form.AttributeForm;
import com.zz.template.entity.Attribute;
import com.zz.template.service.AttributeService;
import com.zz.template.service.TemplateService;
import com.zz.util.KeyUtil;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;


/**
 * @author dell
 * @Description：描述
 * @CreateDate: now
 */

@RestController
@RequestMapping("attribute")

public class AttributeController {
    @Resource
    AttributeService attributeService;
    @Resource
    TemplateService templateService;

    @PostMapping("add")

    public ResponseData addAttribute(@Valid AttributeForm attributeForm) {

        Attribute attribute = new Attribute();
        if (attributeForm.getId().isEmpty()) {
            attribute.setId(KeyUtil.genUniqueKey());
        } else {
            attribute.setId(attributeForm.getId());
        }
        attribute.setName(attributeForm.getName());

        attribute.setPrice(attributeForm.getPrice());
        attribute.setTemplate(templateService.getAllById(attributeForm.getTemplateId()));
        return ResponseDataUtil.success("成功", attributeService.addAttribute(attribute));
    }

    @DeleteMapping("deleteById/{id}")

    public ResponseData deleteAttribute(@PathVariable String id) {


        attributeService.deleteAttribute(id);

        return ResponseDataUtil.success("成功");

    }


    @GetMapping("getAllById/{id}")
    public ResponseData getAllById(@PathVariable("id") String id) {

        return ResponseDataUtil.success("成功", attributeService.getAllById(id));
    }

}
