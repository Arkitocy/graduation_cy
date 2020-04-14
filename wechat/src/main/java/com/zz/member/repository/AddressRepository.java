package com.zz.member.repository;

import com.zz.member.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description: 描述
 * @Author: llf
 * @CreateDate: 2019/11/18 11:00
 */
public interface AddressRepository extends JpaRepository<Address,String> {
    @Modifying
    @Query(value = "update tb_address set default_val=0 where default_val=1 and mid=?1",nativeQuery = true)
    int changeAddress(String mid);

    @Query(value = "select * from tb_address where default_val=1 and mid=?1",nativeQuery = true)
    Address findAddressesBymid(String mid);

    @Query(value = "select * from tb_address where mid=?1  order by default_val desc ",nativeQuery = true)
    List<Address> findAddressesAllBymid(String mid);
}
