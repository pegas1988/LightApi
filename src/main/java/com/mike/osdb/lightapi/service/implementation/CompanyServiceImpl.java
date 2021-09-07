package com.mike.osdb.lightapi.service.implementation;

import com.mike.osdb.lightapi.repository.dao.CompanyDao;
import com.mike.osdb.lightapi.repository.dto.CompanyDto;
import com.mike.osdb.lightapi.repository.entity.Company;
import com.mike.osdb.lightapi.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;

    @Override
    public Company findById(Long id) throws RuntimeException {
        return companyDao.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Could not find Company with id " + id));

    }

    @Override
    @Transactional
    public Company create(CompanyDto companyDto) {
        if (!companyDao.existsById(companyDto.getId())) {
            throw new IllegalArgumentException("Failed to create. Company with such ID already exist.");
        }
        Company company = new Company();
        BeanUtils.copyProperties(companyDto, company);
        return companyDao.save(company);
    }

    @Override
    @Transactional
    public void updateById(Long id, CompanyDto companyDto) throws IllegalArgumentException {
        if (!companyDao.existsById(id)) {
            throw new IllegalArgumentException("Failed to update. Company with such ID doesnt exist.");
        }
        Company company = new Company();
        BeanUtils.copyProperties(companyDto, company);
        company.setId(id);
        companyDao.save(company);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws IllegalArgumentException {
        if (!companyDao.existsById(id)) {
            throw new IllegalArgumentException("Failed to delete. Company with such ID doesnt exist.");
        }
        companyDao.deleteById(id);
    }

    @Override
    public Page<Company> findAllPages(PageRequest pageRequest) {
        return companyDao.findAll(pageRequest);
    }

}
