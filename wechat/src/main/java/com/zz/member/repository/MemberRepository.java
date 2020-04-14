package com.zz.member.repository;

import com.zz.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @Description: 描述
 * @Author: llf
 * @CreateDate: 2019/11/18 10:58
 */
public interface MemberRepository extends JpaRepository<Member,String> {
    /**
     *复杂JPA操作  使用@Query()自定义sql语句  根据业务id UId去更新整个实体
     * 删除和更新操作，需要@Modifying和@Transactional注解的支持
     *
     * 更新操作中 如果某个字段为null则不更新，否则更新【注意符号和空格位置】
     * @param member 对象
     * @return int 被修改数据 条数
     */
    @Modifying
    @Transactional
    @Query("update tb_membermg tm set " +
            "tm.name =  CASE WHEN :#{#m.name} IS NULL THEN tm.name ELSE :#{#m.name} END ," +
            "tm.age =  CASE WHEN :#{#m.age} IS NULL THEN tm.age ELSE :#{#m.age} END ," +
            "tm.sex =  CASE WHEN :#{#m.sex} IS NULL THEN tm.sex ELSE :#{#m.sex} END ," +
            "tm.phone =  CASE WHEN :#{#m.phone} IS NULL THEN tm.phone ELSE :#{#m.phone} END ," +
            "tm.createBy =  CASE WHEN :#{#m.createBy} IS NULL THEN tm.createBy ELSE :#{#m.createBy} END ," +
            "tm.updateBy =  CASE WHEN :#{#m.updateBy} IS NULL THEN tm.updateBy ELSE :#{#m.updateBy} END ," +
            "tm.updateDate =  CASE WHEN :#{#m.updateDate} IS NULL THEN tm.updateDate ELSE :#{#m.updateDate} END ," +
            "tm.email =  CASE WHEN :#{#m.email} IS NULL THEN tm.email ELSE :#{#m.email} END ," +
            "tm.creatTime =  CASE WHEN :#{#m.creatTime} IS NULL THEN tm.creatTime ELSE :#{#m.creatTime} END " +
            "where tm.id = :#{#m.id}")
    int updateMember(@Param("m") Member member);

    Page<Member> findByName(String name, Pageable pageable);
}
