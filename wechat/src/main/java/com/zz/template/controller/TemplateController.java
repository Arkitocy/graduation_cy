package com.zz.template.controller;


import com.zz.dto.TemplateDto;
import com.zz.form.TemplateDetailShowForm;
import com.zz.form.TemplateForm;
import com.zz.form.TemplateShowForm;
import com.zz.template.entity.Attribute;
import com.zz.template.entity.Template;
import com.zz.template.service.TemplateService;
import com.zz.util.KeyUtil;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * @author dell
 * @Description：描述
 * @CreateDate: now
 */

@RestController
@RequestMapping("template")
@Api(value = "模板管理")
public class TemplateController {
    @Resource
    TemplateService templateService;


    @PostMapping("add")
    @ApiOperation(value = "添加模板", notes = "添加模板")
    public ResponseData addTemplate(@Valid TemplateForm templateForm) {
        TemplateDto templateDto = new TemplateDto();
        templateDto.setIntroduce(templateForm.getIntroduce());
        templateDto.setAttributesList(templateForm.getAttributesList());
        templateDto.setMaterial(templateForm.getMaterial());
        templateDto.setName(templateForm.getName());
        templateDto.setSize(templateForm.getSize());
        templateDto.setWeight(templateForm.getWeight());
        templateDto.setPicture1(templateForm.getPicture1());
        templateDto.setPicture2(templateForm.getPicture2());
        if (templateForm.getId().isEmpty()) {
            templateForm.setId(KeyUtil.genUniqueKey());
        }
        templateDto.setId(templateForm.getId());
        Template template = new Template();
        List<Attribute> attributesList = templateForm.getAttributesList();
        BeanUtils.copyProperties(templateDto, template);

        return ResponseDataUtil.success("成功", templateService.addTemplate(template, attributesList));
    }

    @DeleteMapping("deleteById/{id}")
    @ApiOperation(value = "删除模板", notes = "根据id删模板")
    public ResponseData deleteMoban(@PathVariable String id) {
        templateService.deleteTemplate(id);
        return ResponseDataUtil.success("成功");

    }

    @ApiOperation(value = "查所有", notes = "查看所有模板")

    @GetMapping("findAll/{page}/{size}")
    public ResponseData findAll(@PathVariable("page") String page, @PathVariable("size") String size) {
        Page<Template> page1 = templateService.findAll(page, size);
        return ResponseDataUtil.success("成功", page1);
    }

    @PostMapping("findAll1/{page}/{size}")
    public ResponseData findAll1(@Valid TemplateForm templateForm, @PathVariable("page") String page, @PathVariable("size") String size) {
        Page<Template> page1 = templateService.findBy(templateForm.getId(), templateForm.getName(), page, size);
        return ResponseDataUtil.success("成功", page1);
    }


    @ApiOperation(value = "查模板", notes = "根据模板id查所有")
    @ApiImplicitParam(name = "id", value = "模板ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("getAllById/{id}")
    public ResponseData getAllById(@PathVariable("id") String id) {

        return ResponseDataUtil.success("成功", templateService.getAllById(id));
    }

    @GetMapping("templateShow")
    public TemplateShowForm templateShow(){
        TemplateShowForm templateShowForm = new TemplateShowForm();
        templateShowForm.setTemplateShowDtoList(templateService.getTemplateShowDto());
        return templateShowForm;
    }

    @GetMapping("templateDetailShow/{id}")
    public TemplateDetailShowForm templateDetailShow(@PathVariable(name = "id") String id){
        TemplateDetailShowForm templateDetailShowForm = new TemplateDetailShowForm();
        BeanUtils.copyProperties(templateService.getTemplateDetailShowDtoById(id), templateDetailShowForm);
        return templateDetailShowForm;
    }

    @GetMapping("getTemplatePage/{page}")
    public Page<Template> getTemplatePage(@PathVariable(name = "page") int page){
        int size = 6;
        Pageable pageable = PageRequest.of(page, size);
        return templateService.getTemplatePage(pageable);
    }

    @GetMapping("getNum")
    public int getNum(){
        return templateService.getNum();
    }
}
