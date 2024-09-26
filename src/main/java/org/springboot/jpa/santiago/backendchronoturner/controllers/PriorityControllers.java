package org.springboot.jpa.santiago.backendchronoturner.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springboot.jpa.santiago.backendchronoturner.entities.Goal;
import org.springboot.jpa.santiago.backendchronoturner.entities.Priority;
import org.springboot.jpa.santiago.backendchronoturner.services.entityServices.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("api/priority")
@Tag(name = "Priorities")
public class PriorityControllers {
        //Atributos de PriorityControllers
    @Autowired
    private PriorityService priorityService;

    //Constructores de PriorityControllers
    //Asignadores de atributos de PriorityControllers (setters)
    //Lectores de atributos de PriorityControllers (getters)
        //Métodos de PriorityControllers
    @Operation(
        summary = "This endpoint shows the priorities in database for the user concerned",
        description = "Priorities showing",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Here you go with the priorities list",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Priority>> findAll(){
        List<Priority> priorities = this.priorityService.findAll();
        return ResponseEntity.ok(priorities);
    }

    @PostMapping
    @Operation(
        summary = "This endpoint enables the user to create a Priority",
        description = "Here, you can create your new priority",
        tags = {"Priorities, creation"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives an entity class, Priority",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Priority.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Successful Priority creation",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveNewPriority(@RequestBody Priority priority){   //Dejar así un momento antes de añadir la anotación @Valid
        Priority newPriority = this.priorityService.save(priority);
        return ResponseEntity.ok("A new priority has been successfully logged in" /*+ newPriority.getId()*/);   //También se puede crear un interceptor para este método, de tal manera que devolvamos un Goal en lugar de un mensaje, y dejar el mensaje en el log
    }

    @RequestMapping(value = "/update/{id}", method = {PUT, PATCH})
    @Operation(
        summary = "This endpoint enables the user to update a Priority, using its id",
        description = "Here, you can update a current Priority",
        tags = {"Priorities, update"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives an entity class, Priority, and its id",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Priority.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful Priority update",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateGoal(@RequestBody Priority priority, @PathVariable String id){   //Dejar así un momento antes de añadir la anotación @Valid
        priority = this.priorityService.save(priority);
        return ResponseEntity.ok("This priority has been successfully updated. See you next time");   //También se puede crear un interceptor para este método, de tal manera que devolvamos un Goal en lugar de un mensaje, y dejar el mensaje en el log
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
        summary = "This endpoint enables the user to remove a Priority of its own",
        description = "Here, you can delete a Priority",
        tags = {"Priorities, delete"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives the id of the Priority, String typed",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful Priority deletion",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePriority(@RequestBody String id){
        this.priorityService.deleteById(id);
        return ResponseEntity.ok("The priority has been successfully deleted");
    }
}

