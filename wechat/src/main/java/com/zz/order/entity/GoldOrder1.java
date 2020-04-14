/**
 * 
 */
package com.zz.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 订单主表
 * @author asus
 *2019年9月23日
 */
@Entity(name = "tb_order_master")
@Data
public class GoldOrder1 {

	/**
	 * 主键(订单Id)
	 */
	@Id
	@Column(length=50)
	private String id;
	/**
	 * 订单用户名字
	 */
	private String buyerName;
	/**
	 * 订单用户地址
	 */
	private String buyerAddress;
	/**
	 * 订单用户id
	 */
	private String buyerId;
	/**
	 * 订单用户电话
	 */
	private String buyerPhone;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 修改时间
	 */
	private String updateTime;
	/**
	 * 订单总价格
	 */
	private BigDecimal totalPrice;
	/**
	 * 分类
	 */
	private int type;
	/**
	 * 状态
	 */
	private int state;
	/**
	 * 订单详情表id
	 */
	@Fetch(FetchMode.SELECT)
	@JsonIgnoreProperties({"goldOrder1"})
	@OneToMany(mappedBy = "goldOrder1", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<>();


}
