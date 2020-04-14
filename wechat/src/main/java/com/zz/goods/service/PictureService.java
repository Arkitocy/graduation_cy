package com.zz.goods.service;

import javax.annotation.Resource;

import com.zz.dto.GoodsDetailShowDto;
import com.zz.dto.GoodsShowDto;
import com.zz.goods.dao.PictureRepository;
import com.zz.goods.entity.Picture;
import com.zz.goods.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PictureService {
	@Resource
	PictureRepository pictureRepository;
	
	public Picture findById(String id){
		return pictureRepository.findById(id).get();
	}
	
	public Picture save(Picture picture){
		return pictureRepository.save(picture);
	}
	
    public void deleteById(String id) {
        Picture picture = pictureRepository.findById(id).get();
        Product product = picture.getProduct();
        product.getPictureSet().remove(picture);
        pictureRepository.deleteById(id);

    }



}
