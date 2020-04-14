package com.zz.indexValue.service;

import com.zz.indexValue.dao.IndexValueRepository;
import com.zz.indexValue.entity.IndexValue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cy
 */
@Service
public class IndexValueService {
    @Resource
    IndexValueRepository indexValueRepository;

    public IndexValue get() {
        return indexValueRepository.findAll().get(0);
    }

    public IndexValue save(IndexValue indexValue) {
        return indexValueRepository.save(indexValue);
    }
}
