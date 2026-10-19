package com.krakdev.peliculas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakdev.peliculas.entidades.Pelicula;
import com.krakdev.peliculas.services.ServicioPelicula;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
	
	private final ServicioPelicula servicio;
	
	public PeliculaController(ServicioPelicula servicio) {
		this.servicio = servicio;
	}
	
@PostMapping
public ResponseEntity<?> crear(@RequestBody Pelicula pelicula){
try {
	Pelicula creado = servicio.crear(pelicula);
	return ResponseEntity.status(HttpStatus.CREATED).body(creado);
}catch(Exception e) {
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear pelicula");
}
}

@GetMapping
public ResponseEntity<?> listar(){
	try {
		List<Pelicula> peliculas = servicio.listar();
		
		return ResponseEntity.ok(peliculas);
	}catch(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar peliculas");
	}
}

@GetMapping("/{id}")
public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
	try {
		Pelicula encontrada = servicio.buscarPorId(id);
		
		if(encontrada == null) {
			return ResponseEntity.notFound().build();		
			}
		return ResponseEntity.ok(encontrada);
	}catch(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar pelicula por id");
	}
}

@PutMapping("/{id}")
public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pelicula pelicula) {
    try {
        Pelicula actualizado = servicio.actualizar(id, pelicula);
        
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar pelicula por id");
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<?> eliminar(@PathVariable Long id) {
    try {
    	
    	boolean eliminado = servicio.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Se elimino correctamente");
        
    }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar");
    }
}

@GetMapping("/genero/{genero}")
public ResponseEntity<?> buscarPorGenero(@PathVariable String genero){
	try {
		List<Pelicula> peliculas = servicio.buscarPorGenero(genero);
		if(peliculas.size() <0 ) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(peliculas);
	}catch(Exception e) {
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al consultar pelicula por id");
	}
}


@GetMapping("/disponible/{disponible}")
public ResponseEntity<?> buscarPorDisponible(@PathVariable boolean disponible){
	try {
		List<Pelicula> peliculas = servicio.buscarPorDisponible(disponible);
		if(peliculas.size() <0 ) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(peliculas);
	}catch(Exception e) {
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al consultar pelicula por id");
	}
}



}
