package com.mike.osdb.lightapi.service;

import com.mike.osdb.lightapi.dto.CompanyDto;
import com.mike.osdb.lightapi.entity.Company;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyService {
    @Query(value = "select all from company order by 'id' desc ")
    List<Company> findAll();

    Company findById(Long id);

    Company create(CompanyDto companyDto);

    void updateById(Long id, CompanyDto companyDto);

    void deleteById(Long id);

}
