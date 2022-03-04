package com.secman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "security_categories")
@Getter
@Setter
@NoArgsConstructor
public class SecurityCategory extends GSecEntity {

    @Schema(description = "Name of category")
    @NotBlank(message = "error.security-category.name.not-blank")
    @Size(min = 2, max = 128, message = "error.security-category.name.size")
    private String name;
    @Schema(description = "Description of category")
    @NotBlank(message = "error.security-category.description.not-blank")
    @Size(min = 2, max = 65535, message = "error.security-category.description.size")
    private String description;

    @JsonIgnore
    @OneToMany(targetEntity = Security.class, mappedBy = "category")
    private List<Security> securities;

    public SecurityCategory(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public void copyFrom(SecurityCategory category) {
        this.name = category.getName();
        this.description = category.getDescription();
    }
}
