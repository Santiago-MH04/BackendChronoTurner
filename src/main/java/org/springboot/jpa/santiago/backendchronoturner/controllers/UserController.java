package org.springboot.jpa.santiago.backendchronoturner.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springboot.jpa.santiago.backendchronoturner.entities.Task;
import org.springboot.jpa.santiago.backendchronoturner.entities.User;
import org.springboot.jpa.santiago.backendchronoturner.exceptions.EntityNotFoundException;
import org.springboot.jpa.santiago.backendchronoturner.services.entityServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@Tag(name = "Users")
public class UserController {
        //Atributos de UserController
    @Autowired
    private UserService userService;

    //Constructores de UserController
    //Asignadores de atributos de UserController (setters)
    //Lectores de atributos de UserController (getters)
        //Métodos de UserController
    @GetMapping("/show/{id}")
    @Operation(
        summary = "This endpoint enables the system to find a User, submitting its id",
        description = "Here, you can find a User, using its id like search parameter",
        tags = {"Users, show, find-user, id"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives a String class instance",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "302",
                description = "Successful User finding",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<User> findById(@PathVariable String id) {
        User user = this.userService.findById(id)
                .orElseThrow(
                    () -> new EntityNotFoundException("Error! The user you're looking for does not exist")
                );  //Para un manejo de excepciones bien poderoso
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(
        summary = "This endpoint enables the user to create a User account",
        description = "Here, you can create your new user",
        tags = {"User, creation"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives an entity class, User",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = User.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Successful User creation",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveNewUser(@RequestBody User user){   //Dejar así un momento antes de añadir la anotación @Valid
        User newUser = this.userService.save(user);
        return ResponseEntity.ok("A new user has been successfully logged in" /*+ newUser.getId()*/);   //También se puede crear un interceptor para este método, de tal manera que devolvamos un Goal en lugar de un mensaje, y dejar el mensaje en el log
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
        summary = "This endpoint enables the user to remove a User of its own",
        description = "Here, you can delete a User",
        tags = {"Tasks, delete"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives the id of the User, String typed",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful User deletion",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteUser(@RequestBody String id){
        this.userService.deleteById(id);    //Acá se captura la excepción, en caso tal de que ocurra
        return ResponseEntity.ok("The user has been successfully deleted");
    }
}
