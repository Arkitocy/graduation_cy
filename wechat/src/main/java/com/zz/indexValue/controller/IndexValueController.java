package com.zz.indexValue.controller;

import com.zz.form.TemplateForm;
import com.zz.indexValue.entity.IndexValue;
import com.zz.indexValue.service.IndexValueService;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author cy
 */
@Api(value = "shou首页Controller")
@RestController
@RequestMapping("index")
public class IndexValueController {
    @Resource
    IndexValueService indexValueService;

    @GetMapping("get")
    private ResponseData get() {
        IndexValue indexValue = indexValueService.get();
        if (indexValue == null) {
            return ResponseDataUtil.failure("失败");
        } else {
            return ResponseDataUtil.success("成功", indexValue);
        }

    }


    @PostMapping("save")
    private ResponseData save(IndexValue indexValue) {
        if (indexValueService.save(indexValue) != null) {
            return ResponseDataUtil.success("成功");
        } else {
            return ResponseDataUtil.failure("失败");
        }
    }
}
