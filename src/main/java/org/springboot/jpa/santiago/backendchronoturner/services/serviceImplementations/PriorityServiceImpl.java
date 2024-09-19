package org.springboot.jpa.santiago.backendchronoturner.services.serviceImplementations;

import org.springboot.jpa.santiago.backendchronoturner.entities.Priority;
import org.springboot.jpa.santiago.backendchronoturner.repositories.PriorityRepository;
import org.springboot.jpa.santiago.backendchronoturner.services.entityServices.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
        //Atributos de PriorityServiceImpl
    @Autowired
    private PriorityRepository repoPriority;

    //Constructores de PriorityServiceImpl
    //Asignadores de atributos de PriorityServiceImpl (setters)
    //Lectores de atributos de PriorityServiceImpl (getters)
        //Métodos de PriorityServiceImpl
    @Override
    public List<Priority> findAll() {
        return this.repoPriority.findAll();
    }

    @Override
    public Priority save(Priority priority) {
        return this.repoPriority.save(priority);
    }
}
