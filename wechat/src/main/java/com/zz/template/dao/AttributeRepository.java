package com.zz.template.dao;


import com.zz.template.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttributeRepository extends JpaRepository<Attribute, String> {
    Attribute findAllById(String id);
}