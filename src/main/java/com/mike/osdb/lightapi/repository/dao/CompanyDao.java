package com.mike.osdb.lightapi.repository.dao;

import com.mike.osdb.lightapi.repository.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends JpaRepository<Company, Long> {
}
