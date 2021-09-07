package com.mike.osdb.lightapi.repository.entity;


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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "founded", nullable = false)
    private String founded;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;
}
