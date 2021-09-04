package com.mike.osdb.lightapi.controller;

import com.mike.osdb.lightapi.dto.CompanyDto;
import com.mike.osdb.lightapi.entity.Company;
import com.mike.osdb.lightapi.exception.ApiRequestException;
import com.mike.osdb.lightapi.service.implementation.CompanyServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/")
@RequiredArgsConstructor
@Tag(name = "Light Api REST Controller", description = "it makes some simple queries")
public class CompanyControllerRest {

    private final CompanyServiceImpl companyService;

    @Operation(
            summary = "Find all users",
            description = "Sort and makes pagination"
    )
    @GetMapping(value = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CompanyDto>> findAll(@RequestParam String sort,
                                                    @RequestParam @Parameter(description = "Page you wish to find") int page,
                                                    @RequestParam @Parameter(description = "Quantity of the elements on a page") int size) {
        String[] array = sort.split(",");
        PageRequest pageRequest;
        if (array[1].equals("desc"))
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, array[0]));
        else
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, array[0]));
        final Page<Company> companyPage = companyService.findAllPages(pageRequest);
        final Page<CompanyDto> companyDtoPage = companyPage
                .map(company -> {
                    CompanyDto companyDto = new CompanyDto();
                    BeanUtils.copyProperties(company, companyDto);
                    return companyDto;
                });
        return new ResponseEntity<>(companyDtoPage, HttpStatus.OK);
    }

    @Operation(
            summary = "Find user by ID",
            description = "Helps to find needed user"
    )
    @GetMapping(value = "/companies/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable @Parameter(description = "ID to find") Long id) {
        final Company company = companyService.findById(id);
        CompanyDto companyDto = new CompanyDto();
        BeanUtils.copyProperties(company, companyDto);
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Create new user",
            description = "Helps to create new user"
    )
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDto> create(@RequestBody @Parameter(description = "The body for the query") CompanyDto companyDto) {
        final Company company = companyService.create(companyDto);
        companyDto.setId(company.getId());
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete user by ID",
            description = "Helps to delete user"
    )
    @DeleteMapping(value = "/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Parameter(description = "ID to find") Long id) {
        try {
            companyService.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new ApiRequestException("Huston, we have got a problems! Company with such ID doesnt exist!");
        }
    }

    @Operation(
            summary = "Update user",
            description = "You can change any fields you want by sending JSON"
    )
    @PutMapping(value = "/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable @Parameter(description = "ID to find") Long id, @RequestBody CompanyDto companyDto) {
        try {
            companyService.updateById(id, companyDto);
        } catch (IllegalArgumentException e) {
            throw new ApiRequestException("Huston, we have got a problems! Company with such ID doesnt exist!");
        }
    }
}
