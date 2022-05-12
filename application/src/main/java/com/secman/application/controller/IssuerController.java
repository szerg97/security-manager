package com.secman.application.controller;

import com.secman.application.dto.IssuerDto;
import com.secman.application.dto.IssuerMapper;
import com.secman.model.Issuer;
import com.secman.service.IssuerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/issuers")
@CrossOrigin("*")
@AllArgsConstructor
@Validated
public class IssuerController {

    private final IssuerService issuerService;
    private final IssuerMapper issuerMapper;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Issuer.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Issuer.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(
            summary = "Query all issuers",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec", "customer"})
            }
    )
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Issuer>> getAllIssuers(){
        return ResponseEntity.ok(this.issuerService.getAll());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Issuer.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Issuer.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(
            summary = "Query issuer",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec", "customer"})
            }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssuerDto> getOneIssuer(
            @Parameter(name = "id", required = true)
            @PathVariable(name = "id", required = true) Long id){
        IssuerDto dto = this.issuerMapper.fromEntity(this.issuerService.getOne(id));
        return ResponseEntity.ok(dto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Issuer.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Issuer.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(summary = "Deleting issuer",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Issuer> deleteOneIssuer(
            @Parameter (name = "id", required = true)
            @PathVariable(name = "id", required = true) Long id){
        return ResponseEntity.ok(this.issuerService.deleteOneById(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Issuer.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Issuer.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(summary = "Adding issuer",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @PostMapping("")
    public ResponseEntity<Issuer> addOneIssuer(
            @Parameter (name = "issuer", required = true)
            @RequestBody (required = true) Issuer issuer){
        return ResponseEntity.ok(this.issuerService.addOne(issuer));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Issuer.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Issuer.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(summary = "Editing issuer",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @PutMapping("")
    public ResponseEntity<Issuer> updateOneIssuer(
            @Parameter (name = "issuer", required = true)
            @RequestBody (required = true) Issuer issuer){
        return ResponseEntity.ok(this.issuerService.updateOne(issuer));
    }
}

