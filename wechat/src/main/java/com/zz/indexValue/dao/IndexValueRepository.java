package com.zz.indexValue.dao;

import com.zz.indexValue.entity.IndexValue;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cy
 */
public interface IndexValueRepository extends JpaRepository<IndexValue,String> {
}
