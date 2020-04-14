/**
 * 
 */
package com.zz.order.repository;

import com.zz.order.entity.GoldOrder1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author asus
 *2019年9月23日
 */
public interface GoldOrder1Repository extends  JpaRepository<GoldOrder1,String>{
//    public List<GoldOrder1> findByUserid(int id);
    @Modifying
    @Transactional
    @Query("update tb_order_master tm set " +
            "tm.buyerName = CASE WHEN :#{#m.buyerName} IS NULL THEN tm.buyerName  ELSE :#{#m.buyerName} END ," +
            "tm.buyerAddress = CASE WHEN :#{#m.buyerAddress} IS NULL THEN tm.buyerAddress  ELSE :#{#m.buyerAddress} END ," +
            "tm.buyerId = CASE WHEN :#{#m.buyerId} IS NULL THEN tm.buyerId  ELSE :#{#m.buyerId} END ," +
            "tm.buyerPhone = CASE WHEN :#{#m.buyerPhone} IS NULL THEN tm.buyerPhone  ELSE :#{#m.buyerPhone} END ," +
            "tm.createTime = CASE WHEN :#{#m.createTime} IS NULL THEN tm.createTime  ELSE :#{#m.createTime} END ," +
            "tm.updateTime = CASE WHEN :#{#m.updateTime} IS NULL THEN tm.updateTime  ELSE :#{#m.updateTime} END ," +
            "tm.totalPrice = CASE WHEN :#{#m.totalPrice} IS NULL THEN tm.totalPrice  ELSE :#{#m.totalPrice} END ," +
            "tm.type = CASE WHEN :#{#m.type}  = 0 THEN tm.type  ELSE :#{#m.type} END ," +
            "tm.state = CASE WHEN :#{#m.state}  = 0 THEN tm.state  ELSE :#{#m.state} END " +
            "where tm.id = :#{#m.id}")
    int update(@Param("m") GoldOrder1 order);


    @Transactional
    @Modifying
    @Query(value = "update  tb_order_master set state = ? where id= ? ",
            nativeQuery = true)
    int updatestate(int state, String id);


//    Page<GoldOrder1> findAllByBuyerId(String userId,Pageable pageable);


    Page<GoldOrder1> findAllByBuyerIdAndAndState(String userId, int state, Pageable pageable);

     GoldOrder1 findAllById(String id);

    Page<GoldOrder1> findAllByBuyerId(String buyerId, Pageable pageable);

    Page<GoldOrder1> findAllByBuyerIdOrTypeOrState(String buyerId, int Type, int State, Pageable pageable);
    Page<GoldOrder1> findAllByTypeOrStateAndBuyerId(int Type, int State, String buyerId, Pageable pageable);

    @Query(value = "select * FROM tb_order_master  where\n" +
            " str_to_date(create_time, '%Y-%m-%d %H:%i:%s') BETWEEN str_to_date(?,'%Y-%m-%d %H:%i:%s') AND str_to_date(?, '%Y-%m-%d %H:%i:%s')\n"+
            "or buyer_id = ? or type = ? or state = ?",
            countQuery = "SELECT count(*) FROM tb_order_master  where\n" +
                    " str_to_date(create_time, '%Y-%m-%d %H:%i:%s') BETWEEN str_to_date(?,'%Y-%m-%d %H:%i:%s') AND str_to_date(?, '%Y-%m-%d %H:%i:%s')\n"+
                    "or buyer_id = ? or type = ? or state = ?",
            nativeQuery = true)
    Page<GoldOrder1> findGoldOrderLookupForm(String data1, String data2, String buyerId, int Type, int State, Pageable pageable);

    @Query(value = "select * FROM tb_order_master  where\n" +
            " str_to_date(create_time, '%Y-%m-%d %H:%i:%s') BETWEEN str_to_date(?,'%Y-%m-%d %H:%i:%s') AND str_to_date(?, '%Y-%m-%d %H:%i:%s')\n"+
            "and buyer_id = ? or type = ? or state = ?",
            countQuery = "SELECT count(*) FROM tb_order_master  where\n" +
                    " str_to_date(create_time, '%Y-%m-%d %H:%i:%s') BETWEEN str_to_date(?,'%Y-%m-%d %H:%i:%s') AND str_to_date(?, '%Y-%m-%d %H:%i:%s')\n"+
                    "and buyer_id = ? or type = ? or state = ?",
            nativeQuery = true)
    Page<GoldOrder1> findconpanyGoldOrderLookupForm(String data1, String data2, String buyerId, int Type, int State, Pageable pageable);

    @Query(value = "select COUNT(id) a from tb_order_master where SUBSTR(create_time,1,10)=? AND TYPE='0'",
            nativeQuery = true)
    int getNum0(String date);
    @Query(value = "select COUNT(id) a from tb_order_master where SUBSTR(create_time,1,10)=? AND TYPE='1'",
            nativeQuery = true)
    int getNum1(String date);
}
