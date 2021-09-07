package com.mike.osdb.lightapi.service;

import com.mike.osdb.lightapi.repository.dto.CompanyDto;
import com.mike.osdb.lightapi.repository.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CompanyService {

    Company findById(Long id);

    Company create(CompanyDto companyDto);

    void updateById(Long id, CompanyDto companyDto);

    void deleteById(Long id);

    Page<Company> findAllPages(PageRequest pageRequest);
}
