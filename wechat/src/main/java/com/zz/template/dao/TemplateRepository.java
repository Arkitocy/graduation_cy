package com.zz.template.dao;


import com.zz.template.entity.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TemplateRepository extends JpaRepository<Template, String> {
    //    @Modifying
//    @Transactional
//    @Query("update Moban tm set " +
//            "tm.name = CASE WHEN :#{#m.name} IS NULL THEN tm.name  ELSE :#{#m.name} END ," +
//            "tm.size = CASE WHEN :#{#m.size} IS NULL THEN tm.size  ELSE :#{#m.size} END ," +
//            "tm.picture1 = CASE WHEN :#{#m.picture1} IS NULL THEN tm.picture1  ELSE :#{#m.picture1} END ," +
//            "tm.material = CASE WHEN :#{#m.material} IS NULL THEN tm.material  ELSE :#{#m.amaterial} END ," +
//            "tm.weight =  CASE WHEN :#{#m.weight} IS NULL THEN tm.weight ELSE :#{#m.weight} END " +
//            "where tm.id = :#{#m.id}")
//    int update(@Param("m")Moban moban);
    Template findAllById(String id);

    //    @Query(value = "SELECT * FROM tb_template where id=?1 or name=?2",
//           countQuery = "SELECT count(*) FROM tb_template where id=?1 or name=?",
//            nativeQuery = true)
//    Page<Template> find(String id, String name,Pageable pageable);
    Page<Template> findAllByIdOrName(String id, String name, Pageable pageable);
//  ?\  List<Template> findAllByIdOrName(String id, String name);

    @Query(value = "select count(*) from tb_template",nativeQuery = true)
    int getNum();

}