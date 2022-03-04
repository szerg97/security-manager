package com.secman.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class GSecEntity {

    @Schema(description = "Id of entity")
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Schema(description = "Insertion time")
    private final LocalDateTime inserted;
    @Schema(description = "Modification time")
    private LocalDateTime lastModified;
    @Schema(description = "Visibility")
    private Boolean visible;

    public GSecEntity() {
        this.inserted = LocalDateTime.now();;
        this.visible = true;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
