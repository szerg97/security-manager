package com.secman.application.controller;

import com.secman.model.Feedback;
import com.secman.service.FeedbackService;
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
@RequestMapping(path = "/feedbacks")
@CrossOrigin("*")
@AllArgsConstructor
@Validated
public class FeedbackController {

    private final FeedbackService feedbackService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Feedback.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Feedback.class))) }),
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
            summary = "Query all feedbacks",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Feedback> getAllFeedbacks(){
        return this.feedbackService.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Feedback.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Feedback.class))) }),
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
            summary = "Query feedback",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Feedback> getOneFeedback(
            @Parameter(name = "id", required = true)
            @PathVariable(name = "id", required = true) Long id){
        return ResponseEntity.ok(this.feedbackService.getOne(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Feedback.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Feedback.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(summary = "Deleting feedback",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Feedback> deleteOneFeedback(
            @Parameter (name = "id", required = true)
            @PathVariable(name = "id", required = true) Long id){
        return ResponseEntity.ok(this.feedbackService.deleteOneById(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Feedback.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Feedback.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(summary = "Adding feedback",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @PostMapping("")
    public ResponseEntity<Feedback> addOneFeedback(
            @Valid
            @Parameter (name = "feedback", required = true)
            @RequestBody (required = true) Feedback feedback){
        return ResponseEntity.created(URI.create("/feedbacks/{id}")).body(this.feedbackService.addOne(feedback));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Feedback.class))) }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Feedback.class))) }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "You do not have permission",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "302", description = "You are not logged in, redirecting",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json")}),
    })
    @Operation(summary = "Editing feedback",
            security = {
                    @SecurityRequirement(name = "apikey", scopes = {"gsec"}),
                    @SecurityRequirement(name = "openid", scopes = {"gsec"}),
                    @SecurityRequirement(name = "oauth2", scopes = {"gsec"})
            }
    )
    @PutMapping("")
    public ResponseEntity<Feedback> updateOneFeedback(
            @Parameter (name = "feedback", required = true)
            @RequestBody (required = true) Feedback feedback){
        return ResponseEntity.ok(this.feedbackService.updateOne(feedback));
    }
}
