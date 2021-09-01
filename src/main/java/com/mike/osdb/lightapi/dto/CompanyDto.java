package com.mike.osdb.lightapi.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private Long id;
    private String title;
    private String description;
    private String founded;
    private String createdBy;
    private Date createdDate;
}
