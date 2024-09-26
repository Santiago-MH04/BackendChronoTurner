package org.springboot.jpa.santiago.backendchronoturner.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springboot.jpa.santiago.backendchronoturner.dto.authenticationDTO.AuthenticationRequest;
import org.springboot.jpa.santiago.backendchronoturner.dto.authenticationDTO.AuthenticationResponse;
import org.springboot.jpa.santiago.backendchronoturner.dto.authenticationDTO.RegistrationRequest;
import org.springboot.jpa.santiago.backendchronoturner.services.authenticationServices.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Controller for authentication, and its endpoints")
public class AuthenticationController {
        //Atributos de AuthenticationController
    @Autowired
    private AuthenticationService authenticationService;

    //Constructores de AuthenticationController
    //Asignadores de atributos de AuthenticationController (setters)
    //Lectores de atributos de AuthenticationController (getters)
        //Métodos de AuthenticationController
    @PostMapping("/register")
    @Operation(
        summary = "This endpoint registers users",
        description = "A valid email and password are required to register a new user",
        tags = {"Registration"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives a DTO class, RegistrationRequest",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RegistrationRequest.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Successful registration",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
    ) throws MessagingException {
        this.authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    @Operation(
        summary = "This endpoint enables a user to log in",
        description = "The code received in your email is necessary to proceed",
        tags = {"Authentication, Log in"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives a DTO class, AuthenticationRequest",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = AuthenticationRequest.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "202",
                description = "Successful log in",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        return ResponseEntity.ok(this.authenticationService.authenticate(request));
    }
}
