package com.mike.osdb.lightapi.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "company")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "founded")
    private String founded;
    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "createdDate")
    private Date createdDate;

}
