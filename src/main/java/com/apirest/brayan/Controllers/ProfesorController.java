package com.apirest.brayan.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apirest.brayan.Repositories.ProfesoresRepository;
import com.apirest.brayan.Entities.Profesores;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController // indicar que esta clase es un controlador de Spring MVC
@RequestMapping("/profesores") // ruta base para todas las solicitudes relacionadas con profesores
public class ProfesorController {

    @Autowired // inyectar instancia
    private ProfesoresRepository profesoresRepository;

    @GetMapping // conn estos comienzan las peticiones

    public List<Profesores> getAllprofesores() {

        return profesoresRepository.findAll(); // este método devuelve una lista de todos los profesores en la base de
                                               // datos
    }

    @GetMapping("/{id}")
    public Profesores getprofesoresbyId(@PathVariable Long id) {
        return profesoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro al profesor encargado, con el ID:" + id)); // este

    }

    @PostMapping // pasar un body request(peticion) con los datos del profesor a crear

    public Profesores createProfesor(@RequestBody Profesores profesor) {
        return profesoresRepository.save(profesor);
    }

    @PutMapping("/{id}")
    public Profesores updateprofesoresbyId(@PathVariable Long id, @RequestBody Profesores profesorDetails) {
        Profesores profesor = profesoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro al profesor encargado, con el ID:" + id)); // este

        profesor.setNombre(profesorDetails.getNombre());
        profesor.setApellido(profesorDetails.getApellido());
        profesor.setMateria(profesorDetails.getMateria());
        profesor.setEdad(profesorDetails.getEdad());
        return profesoresRepository.save(profesor);

    }

    @DeleteMapping("/{id}")

    public String deleteprofesoresbyId(@PathVariable Long id) {
        Profesores profesor = profesoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro al profesor encargado, con el ID:" + id));

        profesoresRepository.delete(profesor);
        return "El poducto con el ID:" + id + "fue eliminado con exito";
    }
}
