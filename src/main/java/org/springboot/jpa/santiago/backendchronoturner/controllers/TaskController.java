package org.springboot.jpa.santiago.backendchronoturner.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springboot.jpa.santiago.backendchronoturner.entities.Priority;
import org.springboot.jpa.santiago.backendchronoturner.entities.Task;
import org.springboot.jpa.santiago.backendchronoturner.services.entityServices.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/api/task")
@Tag(name = "Tasks")
public class TaskController {
        //Atributos de TaskController
    @Autowired
    private TaskService taskService;

    //Constructores de TaskController
    //Asignadores de atributos de TaskController (setters)
    //Lectores de atributos de TaskController (getters)
        //Métodos de TaskController
    @GetMapping("/show-all")
    @Operation(
        summary = "This endpoint shows the tasks in database for the user concerned",
        description = "Tasks showing",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Here you go with the tasks list",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Task>> findAll() {
        List<Task> tasks = this.taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/show/{id}")
    @Operation(
        summary = "This endpoint enables the user to find a task, submitting its id",
        description = "Here, you can find a task, using its id like search parameter",
        tags = {"Tasks, show, find-task, id"},
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
                description = "Successful Task finding",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Task> findById(@PathVariable String id) {
        Task task = this.taskService.findById(id).orElseThrow();//Para un manejo de excepciones bien poderoso
        return ResponseEntity.ok(task);
    }

    @PostMapping
    @Operation(
        summary = "This endpoint enables the user to create a Task",
        description = "Here, you can create your new task",
        tags = {"Task, creation"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives an entity class, Task",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Task.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Successful Task creation",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveNewTask(@RequestBody Task task){   //Dejar así un momento antes de añadir la anotación @Valid
        Task newtask = this.taskService.save(task);
        return ResponseEntity.ok("A new task has been successfully logged in" /*+ newTask.getId()*/);   //También se puede crear un interceptor para este método, de tal manera que devolvamos un Goal en lugar de un mensaje, y dejar el mensaje en el log
    }

    @RequestMapping(value = "/update/{id}", method = {PUT, PATCH})
    @Operation(
        summary = "This endpoint enables the user to update a Task, using its id",
        description = "Here, you can update a current Task",
        tags = {"Tasks, update"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives an entity class, Task, and its id",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Task.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful Task update",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable String id){   //Dejar así un momento antes de añadir la anotación @Valid
        task = this.taskService.save(task);
        return ResponseEntity.ok("This task has been successfully updated. See you next time");   //También se puede crear un interceptor para este método, de tal manera que devolvamos un Goal en lugar de un mensaje, y dejar el mensaje en el log
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
        summary = "This endpoint enables the user to remove a Task of its own",
        description = "Here, you can delete a Task",
        tags = {"Tasks, delete"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives the id of the Task, String typed",
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
    public ResponseEntity<?> deleteTask(@RequestBody String id){
        this.taskService.deleteById(id);    //Acá se captura la excepción, en caso tal de que ocurra
        return ResponseEntity.ok("The task has been successfully deleted");
    }
}
