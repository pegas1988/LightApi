package com.mike.osdb.lightapi.service;

import com.mike.osdb.lightapi.dto.CompanyDto;
import com.mike.osdb.lightapi.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    Company findById(Long id);

    Company create(CompanyDto companyDto);

    void updateById(Long id, CompanyDto companyDto);

    void deleteById(Long id);

}
