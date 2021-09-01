package com.mike.osdb.lightapi.service.implementation;

import com.mike.osdb.lightapi.dao.CompanyDao;
import com.mike.osdb.lightapi.dto.CompanyDto;
import com.mike.osdb.lightapi.entity.Company;
import com.mike.osdb.lightapi.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public Company findById(Long id) throws RuntimeException {
        Company company = companyDao.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Could not find Company with id " + id));
        return company;
    }

    @Override
    @Transactional
    public Company create(CompanyDto companyDto) {
        if (!Objects.isNull(companyDto.getId())) {
            throw new IllegalArgumentException("Failed to create. Company with such ID already exist.");
        }
        Company company = new Company();
        BeanUtils.copyProperties(companyDto, company);
        return companyDao.save(company);
    }

    @Override
    @Transactional
    public void updateById(Long id, CompanyDto companyDto) {
        if (!Objects.isNull(companyDto.getId())) {
            throw new IllegalArgumentException("Failed to update. Company with such ID doesnt exist.");
        }
        Company company = new Company();
        BeanUtils.copyProperties(companyDto, company);
        company.setId(id);
        companyDao.save(company);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!companyDao.existsById(id)) {
            throw new IllegalArgumentException("Failed to delete. Company with such ID doesnt exist.");
        }
        companyDao.deleteById(id);
    }

    public Page<Company> findAllPages(Pageable pageable) {
        return companyDao.findAll(pageable);
    }

}
