package org.springboot.jpa.santiago.backendchronoturner.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springboot.jpa.santiago.backendchronoturner.entities.Category;
import org.springboot.jpa.santiago.backendchronoturner.entities.Goal;
import org.springboot.jpa.santiago.backendchronoturner.exceptions.EntityNotFoundException;
import org.springboot.jpa.santiago.backendchronoturner.services.entityServices.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("api/goal")
@Tag(name = "Goals")
public class GoalController {
        //Atributos de GoalController
    @Autowired
    private GoalService goalService;

    //Constructores de GoalController
    //Asignadores de atributos de GoalController (setters)
    //Lectores de atributos de GoalController (getters)
        //Métodos de GoalController
    @GetMapping("/show-all")
    @Operation(
        summary = "This endpoint shows the goals registered in database for the user concerned",
        description = "Goals showing",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Here you go with the categories list",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Goal>> findAll(){
        List<Goal> goals = this.goalService.findAll();
        return ResponseEntity.ok(goals);
    }

    @GetMapping("/show/{id}")
    @Operation(
        summary = "This endpoint enables the user to find a goal, submitting its id",
        description = "Here, you can find a goal, using its id like search parameter",
        tags = {"Goals, show, find-goal, id"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives an entity class, Goal",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "302",
                description = "Successful goal finding",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Goal> findById(@PathVariable String id){
        Goal goal = this.goalService.findById(id)
                .orElseThrow(
                    () -> new EntityNotFoundException("Error! The goal you're looking for hasn't been registered")
                );    //Para un manejo de excepciones bien poderoso
        return ResponseEntity.ok(goal);
    }

    @GetMapping("/show-like/{name}")
    @Operation(
        summary = "This endpoint enables the user to find a goal, submitting a portion of its name",
        description = "Here, you can create a category",
        tags = {"Goals, show, find-goal, name"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives an entity class, Goal",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "302",
                description = "Successful Goal finding",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Goal> findByNameContaining(@PathVariable String name){
        Goal goalLike = this.goalService.findByNameContaining(name);
        return ResponseEntity.ok(goalLike);
    }

    @PostMapping
    @Operation(
        summary = "This endpoint enables the user to create a Goal",
        description = "Here, you can create your new resolution",
        tags = {"Categories, show"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives an entity class, Goal",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Goal.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Successful Goal creation",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveNewGoal(@RequestBody Goal goal){   //Dejar así un momento antes de añadir la anotación @Valid
        Goal newGoal = this.goalService.save(goal);
        return ResponseEntity.ok("A new goal has been successfully logged in" /*+ newGoal.getId()*/);   //También se puede crear un interceptor para este método, de tal manera que devolvamos un Goal en lugar de un mensaje, y dejar el mensaje en el log
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
        summary = "This endpoint enables the user to remove a Goal of its own",
        description = "Here, you can delete a Goal",
        tags = {"Categories, delete"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives the id of the goal, String typed",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful goal deletion",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteGoal(@RequestBody String id){
        this.goalService.deleteById(id);    //Acá se captura la excepción, en caso tal de que ocurra
        return ResponseEntity.ok("The goal has been successfully deleted");
    }

    @RequestMapping(value = "/update/{id}", method = {PUT, PATCH})
    @Operation(
        summary = "This endpoint enables the user to update a Goal, using its id",
        description = "Here, you can update a current resolution",
        tags = {"Categories, update"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives an entity class, Goal, and its id",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Goal.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful Goal updated",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateGoal(@RequestBody Goal goal, @PathVariable String id){
        goal = this.goalService.save(goal);
        return ResponseEntity.ok("This goal has been successfully updated. See you next time");   //También se puede crear un interceptor para este método, de tal manera que devolvamos un Goal en lugar de un mensaje, y dejar el mensaje en el log
    }
}
