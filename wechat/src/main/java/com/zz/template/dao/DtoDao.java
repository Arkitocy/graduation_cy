package com.zz.template.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zsj55
 */
@Repository
public class DtoDao {
    @Resource
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>>getTemplateShowDto(){
        String sql = "select id, name, picture1,picture2 from tb_template";
        Object[] args={};
        int[] argsTypes={};
        return jdbcTemplate.queryForList(sql, args, argsTypes);
    }
}
