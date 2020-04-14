package com.zz.template.service;


import com.zz.dto.TemplateDetailShowDto;
import com.zz.dto.TemplateShowDto;
import com.zz.template.dao.DtoDao;
import com.zz.template.dao.TemplateRepository;
import com.zz.template.entity.Attribute;
import com.zz.template.entity.Template;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author dell
 * @Description：描述
 * @CreateDate:
 */
@Service
public class TemplateService {
    @Resource
    TemplateRepository templateRepository;

    @Resource
    DtoDao dtoDao;

    public Template addTemplate(Template template, List<Attribute> attributesList) {
        for (Attribute os : attributesList) {
            os.setTemplate(template);
        }
        return templateRepository.save(template);
    }

    public void deleteTemplate(String id) {
        templateRepository.deleteById(id);
    }

    public Page<Template> findAll(String page, String limit) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit));
        Page<Template> pageinfo = templateRepository.findAll(pageable);
        return pageinfo;
    }

    public Template getAllById(String id) {
        return templateRepository.findAllById(id);
    }

    public Page<Template> findBy(String id, String name, String page, String limit) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit));
        Page<Template> pageinfo = templateRepository.findAllByIdOrName(id, name, pageable);
        return pageinfo;
    }


    /**
     * @return TemplateShowDto
     */
    public List<TemplateShowDto> getTemplateShowDto() {
        List<Map<String, Object>> mapList = dtoDao.getTemplateShowDto();
        List<TemplateShowDto> templateShowDtoList = new ArrayList<>();
        for(Map map:mapList){
            TemplateShowDto templateShowDto = new TemplateShowDto();
            templateShowDto.setId(String.valueOf(map.get("id")));
            templateShowDto.setName(String.valueOf(map.get("name")));
            templateShowDto.setPicture1(String.valueOf(map.get("picture1")).substring(1));
            templateShowDtoList.add(templateShowDto);
        }
        return templateShowDtoList;
    }

    /**
     * @param id
     * @return Template
     */
    public TemplateDetailShowDto getTemplateDetailShowDtoById(String id) {
        Template template = templateRepository.findById(id).get();
        TemplateDetailShowDto templateDetailShowDto = new TemplateDetailShowDto();
        BeanUtils.copyProperties(template, templateDetailShowDto);
        templateDetailShowDto.setPicture1(templateDetailShowDto.getPicture1().substring(1));
        templateDetailShowDto.setPicture2(templateDetailShowDto.getPicture2().substring(1));
        return templateDetailShowDto;
    }

    public Page<Template> getTemplatePage(Pageable pageable) {
        return templateRepository.findAll(pageable);
    }

    public int getNum(){
        return templateRepository.getNum();
    }
}
