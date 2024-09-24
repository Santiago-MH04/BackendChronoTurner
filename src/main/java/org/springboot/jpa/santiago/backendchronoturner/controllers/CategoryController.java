package org.springboot.jpa.santiago.backendchronoturner.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springboot.jpa.santiago.backendchronoturner.dto.authenticationDTO.AuthenticationRequest;
import org.springboot.jpa.santiago.backendchronoturner.entities.Category;
import org.springboot.jpa.santiago.backendchronoturner.services.entityServices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@Tag(name = "Categories", description = "Controller for categories, and its endpoints")
public class CategoryController {
        //Atributos de CategoryController
    @Autowired
    private CategoryService categoryService;

    //Constructores de CategoryController
    //Asignadores de atributos de CategoryController (setters)
    //Lectores de atributos de CategoryController (getters)
        //Métodos de CategoryController
    @GetMapping("/show-all")
    @Operation(
        summary = "This endpoint shows the categories registered in database",
        description = "Categories showing",
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
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = this.categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    @Operation(
        summary = "This endpoint enables the user to create a category",
        description = "Here, you can create a category",
        tags = {"Categories, show"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives an entity class, Category",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Category.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Successful Category creation",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveNewGoal(@RequestBody Category category){   //Dejar así un momento antes de añadir la anotación @Valid
        Category newCategory = this.categoryService.save(category);
        return ResponseEntity.ok("A new category has been successfully logged in" /*+ newCategory.getId()*/);   //También se puede crear un interceptor para este método, de tal manera que devolvamos un Goal en lugar de un mensaje, y dejar el mensaje en el log
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
        summary = "This endpoint enables the user to remove a category of its own",
        description = "Here, you can create a category",
        tags = {"Categories, delete"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The method receives the id of the category, String typed",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Category.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful category deletion",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)
                )
            )
        }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCategory(@RequestBody String id){
        this.categoryService.deleteById(id);
        return ResponseEntity.ok("The category has been successfully deleted");
    }
}
