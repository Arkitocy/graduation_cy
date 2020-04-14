package com.zz.order.repository;

import com.zz.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
//    public List<OrderDetail> findAllByOrderId1(String id);
@Modifying
@Transactional
@Query("update tb_order_detail tm set " +
        "tm.file = CASE WHEN :#{#m.file} IS NULL THEN tm.file  ELSE :#{#m.file} END ," +
        "tm.productId = CASE WHEN :#{#m.productId} IS NULL THEN tm.productId  ELSE :#{#m.productId} END ," +
        "tm.productName = CASE WHEN :#{#m.productName} IS NULL THEN tm.productName  ELSE :#{#m.productName} END ," +
        "tm.productPrice = CASE WHEN :#{#m.productPrice} IS NULL THEN tm.productPrice  ELSE :#{#m.productPrice} END ," +
        "tm.productPicture = CASE WHEN :#{#m.productPicture} IS NULL THEN tm.productPicture  ELSE :#{#m.productPicture} END ," +
        "tm.productQuantity = CASE WHEN :#{#m.productQuantity} = 0 THEN tm.productQuantity  ELSE :#{#m.productQuantity} END " +
        "where tm.id = :#{#m.id}")
int update(@Param("m") OrderDetail orderDetail);


}
