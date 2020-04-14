
package com.zz.goods.dao;


import com.zz.goods.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月18日 上午9:04:57 
 */

public interface ProductRepository extends JpaRepository<Product,String> {
	Page<Product> findByIdOrName(String id, String name, Pageable pageable);

	@Query(value = "select count(*) from tb_product",nativeQuery = true)
	int getNum();


}
