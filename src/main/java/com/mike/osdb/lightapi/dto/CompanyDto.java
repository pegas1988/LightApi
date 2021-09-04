package com.mike.osdb.lightapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Main entity to show")
public class CompanyDto {

    @Schema(description = "ID of the entity", example = "Auto generated")
    private Long id;
    @Schema(description = "Some title", example = "We teach you swimm")
    private String title;
    @Schema(description = "Description of a company", example = "The factory of a swimming pools")
    private String description;
    @Schema(description = "Who is a founder?", example = "Mike")
    private String founded;
    @Schema(description = "Who create?", example = "Mike`s friend")
    private String created_by;
    @Schema(description = "When was created?", example = "25.01.1920")
    private Date created_date;
}
