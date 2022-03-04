package com.secman.application.controller;

import com.secman.model.Country;
import com.secman.service.CountryService;
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
@RequestMapping(path = "/countries")
@CrossOrigin("*")
@AllArgsConstructor
@Validated
public class CountryController {

    private final CountryService countryService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))) }),
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
            summary = "Query all countries",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Country>> getAllCountries(){
        return ResponseEntity.ok(this.countryService.getAll());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))) }),
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
            summary = "Query country",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> getOneCountry(
            @Parameter (name = "id", required = true)
            @PathVariable(name = "id", required = true) Long id){
        return ResponseEntity.ok(this.countryService.getOne(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(summary = "Deleting country",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> deleteOneCountry(
            @Parameter (name = "id", required = true)
            @PathVariable(name = "id", required = true) Long id){
        return ResponseEntity.ok(this.countryService.deleteOneById(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create country successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(summary = "Adding country",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @PostMapping("")
    public ResponseEntity<Country> addOneCountry(
            @Valid
            @Parameter (description = "country", required = true)
            @RequestBody (required = true) Country country) throws Exception{
        return ResponseEntity.created(URI.create("/countries/{id}")).body(this.countryService.addOne(country));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(summary = "Editing country",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @PutMapping("")
    public ResponseEntity<Country> updateOneCountry(
            @Parameter (name = "country", required = true)
            @RequestBody (required = true) Country country){
        return ResponseEntity.ok(this.countryService.updateOne(country));
    }
}
