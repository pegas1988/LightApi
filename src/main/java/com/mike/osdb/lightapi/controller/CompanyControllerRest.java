package com.mike.osdb.lightapi.controller;

import com.mike.osdb.lightapi.dto.CompanyDto;
import com.mike.osdb.lightapi.entity.Company;
import com.mike.osdb.lightapi.service.implementation.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/companies")
@RequiredArgsConstructor
public class CompanyControllerRest {

    private final CompanyServiceImpl companyService;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CompanyDto>> findAll(@RequestParam int size, @RequestParam int page) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        final Page<Company> companyPage = companyService.findAllPages(pageRequest);
        final Page<CompanyDto> companyDtoPage = companyPage
                .map(company -> {
                    CompanyDto companyDto = new CompanyDto();
                    BeanUtils.copyProperties(company, companyDto);
                    return companyDto;
                });
        return new ResponseEntity<>(companyDtoPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable Long id) {
        final Company company = companyService.findById(id);
        CompanyDto companyDto = new CompanyDto();
        BeanUtils.copyProperties(company, companyDto);
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDto> create(@RequestBody CompanyDto companyDto) {
        final Company company = companyService.create(companyDto);
        companyDto.setId(company.getId());
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        companyService.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        companyService.updateById(id, companyDto);
    }
}
