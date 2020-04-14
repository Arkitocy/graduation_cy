package com.zz.controller;

import com.zz.util.CustomImgUtils;
import com.zz.util.KeyUtil;
import com.zz.util.UploadUtils;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


/**
 * @author cy
 */
@RestController
@RequestMapping("custom")
public class CustomController {

//    @Value("D://tmp//")
//    String filePath;

    @Value("${fileUpLoadPath}")
    String filePath;
    String empty = "null";

    @PostMapping("uploadImgFront")
    public ResponseData uploadImgFront(@RequestParam("file") MultipartFile blobFile, @RequestParam("orderId") String orderId, @RequestParam("templateSrc") String templateSrc) throws Exception {
        if ((empty).equals(orderId)) {
            orderId = KeyUtil.genUniqueKey();
        }
        String fullPath = filePath + "userfiles" + File.separator + "customtemp" + File.separator + orderId + "-template-A" + ".jpg";
        try {
            UploadUtils.upload(blobFile, fullPath);
        } catch (Exception e) {
            return ResponseDataUtil.failure(e.getMessage());
        }
        //依据所选模板id获得模板正面图片
        String backgroundUrl = filePath + templateSrc.substring(9);
        return ResponseDataUtil.success("上传成功", CustomImgUtils.customFront(fullPath, backgroundUrl, orderId, filePath));

    }


    @PostMapping("uploadImgBack")
    public ResponseData uploadImgBack(@RequestParam("file") MultipartFile blobFile, @RequestParam("orderId") String orderId, @RequestParam("templateSrc") String templateSrc) throws Exception {
        if ((empty).equals(orderId)) {
            orderId = KeyUtil.genUniqueKey();
        }
        String fullPath = filePath + "userfiles" + File.separator + "customtemp" + File.separator + orderId + "-template-B" + ".jpg";
        try {
            UploadUtils.upload(blobFile, fullPath);
        } catch (Exception e) {
            return ResponseDataUtil.failure(e.getMessage());
        }
        //依据所选模板id获得模板正面图片
        String backgroundUrl = filePath + templateSrc.substring(9);
        return ResponseDataUtil.success("上传成功", CustomImgUtils.customBack(fullPath, backgroundUrl, orderId, filePath));

    }


//    @PostMapping("uploadRar")
//    public ResponseData uploadRar(@RequestParam("file") MultipartFile blobFile, @RequestParam("templateId") String templateId) {
//        if ((empty).equals(templateId)) {
//            templateId = KeyUtils.genUniqueKey();
//        }
//        String fullPath = filePath + templateId + "-CompanyRAR-" + ".rar";
//        try {
//            UploadUtils.upload(blobFile, fullPath);
//        } catch (Exception e) {
//            return ResponseDataUtil.failure(e.getMessage());
//        }
//        return ResponseDataUtil.success("上传成功", templateId);
//    }


}
