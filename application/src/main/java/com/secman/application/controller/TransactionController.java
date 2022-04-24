package com.secman.application.controller;

import com.secman.application.dto.TransactionDto;
import com.secman.application.dto.TransactionMapper;
import com.secman.model.Portfolio;
import com.secman.model.Transaction;
import com.secman.service.PortfolioService;
import com.secman.service.TransactionService;
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
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/transactions")
@CrossOrigin("*")
@AllArgsConstructor
@Validated
public class TransactionController {

    private final TransactionMapper transactionMapper;
    private final TransactionService transactionService;
    private final PortfolioService portfolioService;

    @Autowired
    private HttpServletRequest request;

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
    @Operation(
            summary = "Query all securities",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return ResponseEntity.ok(this.transactionService.getAll());
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
    @Operation(
            summary = "Query all securities",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec", "customer"})
            }
    )
    @GetMapping(path = "/self", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDto>> getTransactionsByCustomer(){
        Portfolio portfolio = this.portfolioService.getByCustomer(this.getKeycloakSecurityContext().getToken().getPreferredUsername());
        List<Transaction> transactions = this.transactionService.getByPortfolio(portfolio);
        List<TransactionDto> dtos = new ArrayList<>();
        transactions.forEach(t -> dtos.add(transactionMapper.fromEntity(t)));
        return ResponseEntity.ok(dtos);
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
    @Operation(
            summary = "Query security",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec", "customer"})
            }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDto> getOneTransaction(
            @Parameter(name = "id", required = true)
            @PathVariable(name = "id", required = true) Long id){
        TransactionDto dto = transactionMapper.fromEntity(this.transactionService.getOne(id));
        return ResponseEntity.ok(dto);
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
    @Operation(summary = "Deleting security",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> deleteOneTransaction(
            @Parameter (name = "id", required = true)
            @PathVariable(name = "id", required = true) Long id){
        return ResponseEntity.ok(this.transactionService.deleteOneById(id));
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
    @Operation(summary = "Adding security",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec", "customer"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec", "customer"})
            }
    )
    @PostMapping("")
    public ResponseEntity<Transaction> addOneTransaction(
            @Valid
            @Parameter (name = "dto", required = true)
            @RequestBody (required = true) TransactionDto dto){
        Portfolio portfolio = this.portfolioService.getByCustomer(this.getKeycloakSecurityContext().getToken().getPreferredUsername());
        Transaction transaction = transactionMapper.toEntity(dto, portfolio);
//        transaction.setPortfolio(portfolio);
        portfolio.setBalance(portfolio.getBalance() - transaction.getNetValue());
        this.portfolioService.updateOne(portfolio);
        return ResponseEntity.ok(this.transactionService.addOne(transaction));
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
    @Operation(summary = "Editing security",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @PutMapping("")
    public ResponseEntity<Transaction> updateOneTransaction(
            @Parameter (name = "transaction", required = true)
            @RequestBody (required = true) Transaction transaction){
        return ResponseEntity.ok(this.transactionService.updateOne(transaction));
    }

    private KeycloakSecurityContext getKeycloakSecurityContext(){
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }
}


