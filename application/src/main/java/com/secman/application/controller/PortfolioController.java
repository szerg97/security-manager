package com.secman.application.controller;

import com.secman.application.dto.PortfolioDto;
import com.secman.application.dto.PortfolioMapper;
import com.secman.model.Portfolio;
import com.secman.model.Transaction;
import com.secman.service.PortfolioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/portfolios")
@CrossOrigin("*")
@AllArgsConstructor
@Validated
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final PortfolioMapper portfolioMapper;

    @Autowired
    private HttpServletRequest request;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Portfolio.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Portfolio.class))) }),
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
            summary = "Query all portfolios",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Portfolio>> getAllPortfolios(){
        return ResponseEntity.ok(this.portfolioService.getAll());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Portfolio.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Portfolio.class))) }),
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
            summary = "Query portfolio by customer",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec", "customer"})
            }
    )
    @GetMapping(path = "/self", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PortfolioDto> getPortfolioByCustomer(){
        Portfolio portfolio = this.portfolioService.getByCustomer(this.getKeycloakSecurityContext().getToken().getPreferredUsername());
        PortfolioDto dto = portfolioMapper.fromEntity(portfolio);
        return ResponseEntity.ok(dto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Portfolio.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Portfolio.class))) }),
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
            summary = "Query portfolio",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec", "customer"})
            }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Portfolio> getOnePortfolio(
            @Parameter(name = "id", required = true)
            @PathVariable(name = "id", required = true) Long id){
        return ResponseEntity.ok(this.portfolioService.getOne(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Transaction.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Transaction.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(summary = "Editing portfolio",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec", "customer"})
            }
    )
    @PutMapping("")
    public ResponseEntity<Portfolio> updateOnePortfolio(
            @Parameter (name = "portfolio", required = true)
            @RequestBody (required = true) Portfolio portfolio){
        return ResponseEntity.ok(this.portfolioService.updateOne(portfolio));
    }

    private KeycloakSecurityContext getKeycloakSecurityContext(){
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }
}
