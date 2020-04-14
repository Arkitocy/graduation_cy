
package com.zz.goods.service;

import com.zz.dto.GoodsDetailShowDto;
import com.zz.dto.GoodsShowDto;
import com.zz.goods.dao.ProductRepository;
import com.zz.goods.entity.Picture;
import com.zz.goods.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author asuslh
 * @DateTime 2019年11月18日 上午8:58:00
 */
@Service
public class ProductService {
    @Resource
    ProductRepository productRepository;

    /**
     * @param product
     * @return Product
     * @Description: 添加商品/修改商品
     * @Author lh
     * @DateTime 2019年11月18日 上午10:20:38
     */
    public Product save(Product product) {
        return productRepository.save(product);

    }

    /**
     * @param id
     * @Description: 根据id删除商品
     * @Author lh
     * @DateTime 2019年11月18日 上午10:22:05
     */
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    /**
     * @param page
     * @param size
     * @return Page<Product>
     * @Description: 分页查询所有商品
     * @Author lh
     * @DateTime 2019年11月18日 上午10:40:45
     */
    public Page<Product> showAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return productRepository.findAll(pageable);
    }

    /**
     * @param id
     * @return Product 商品实体
     * @Description:根据id查询商品
     * @Author lh
     * @DateTime 2019年11月18日 上午11:20:16
     */
    public Product findById(String id) {
        return productRepository.findById(id).get();
    }

    public Page<Product> findByIdOrName(String id, String name, int page) {
        Pageable pageable = PageRequest.of(page, Integer.parseInt("10"));
        return productRepository.findByIdOrName(id, name, pageable);
    }

    public GoodsDetailShowDto findById1(String id) {
        Product product = productRepository.findById(id).get();
        GoodsDetailShowDto goodsDetailShowDto = new GoodsDetailShowDto();
        goodsDetailShowDto.setName(product.getName());
        goodsDetailShowDto.setDetail(product.getDetail());
        goodsDetailShowDto.setMaterial(product.getMaterial());
        List productSet = new ArrayList();
        for (Picture picture : product.getPictureSet()) {
            picture.setPath(picture.getPath().substring(1));
            productSet.add(picture);
        }
        goodsDetailShowDto.setPictureList(productSet);
        goodsDetailShowDto.setPrice(product.getPrice());
        goodsDetailShowDto.setSize(product.getSize());
        goodsDetailShowDto.setWeight(product.getWeight());
        return goodsDetailShowDto;
    }

    public List<GoodsShowDto> findAll() {
        List<Product> productList = productRepository.findAll();
        List<GoodsShowDto> goodsShowDtoList = new ArrayList<>();
        for (Product product : productList) {
            GoodsShowDto goodsShowDto = new GoodsShowDto();
            goodsShowDto.setId(product.getId());
            goodsShowDto.setName(product.getName());
            goodsShowDto.setPicture(product.getPictureSet().get(0).getPath().substring(1));
            goodsShowDtoList.add(goodsShowDto);
        }
        return goodsShowDtoList;
    }

    public Page<Product> findAllPageable(Pageable pageable) {
        Page<Product> templatePage = productRepository.findAll(pageable);
        return templatePage;
    }

    public int getNum(){
        return productRepository.getNum();
    }
}
