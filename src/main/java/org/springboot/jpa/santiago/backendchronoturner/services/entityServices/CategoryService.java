package org.springboot.jpa.santiago.backendchronoturner.services.entityServices;

import org.springboot.jpa.santiago.backendchronoturner.entities.Category;
import org.springboot.jpa.santiago.backendchronoturner.services.methodServices.*;

import java.util.List;

public interface CategoryService extends FindAllService<Category>,
                                 SaveService<Category>,
                                 RemoveService<Category, String>{
    //Atributos de CategoryService
    //Constructores de CategoryService
    //Asignadores de atributos de CategoryService (setters)
    //Lectores de atributos de CategoryService (getters)
    //Métodos de CategoryService
}
