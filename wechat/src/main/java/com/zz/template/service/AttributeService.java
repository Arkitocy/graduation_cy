package com.zz.template.service;


import com.zz.template.dao.AttributeRepository;
import com.zz.template.entity.Attribute;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author dell
 * @Description：描述
 * @CreateDate:
 */
@Service
public class AttributeService {
    @Resource
    AttributeRepository attributeRepository;

    public Attribute addAttribute(Attribute attribute) {

        return attributeRepository.save(attribute);
    }

    public void deleteAttribute(String id) {

        Attribute attribute = attributeRepository.findAllById(id);
        attribute.setTemplate(null);
        attributeRepository.deleteById(id);
    }

    public Attribute getAllById(String id) {
        return attributeRepository.findAllById(id);
    }
}
