package com.krakdev.peliculas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakdev.peliculas.entidades.Pelicula;
import com.krakdev.peliculas.repository.PeliculaRepository;

@Service
public class ServicioPelicula {
	private final PeliculaRepository peliculaRepository;

	public ServicioPelicula(PeliculaRepository peliculaRepository) {
		this.peliculaRepository = peliculaRepository;
	}

	public Pelicula crear(Pelicula pelicula) {
		return peliculaRepository.save(pelicula);
	}

	public List<Pelicula> listar() {
		return peliculaRepository.findAll();
	}
	public Pelicula buscarPorId(Long id){
		Optional<Pelicula> resultado = peliculaRepository.findById(id);
		
		return resultado.orElse(null);
	}
	
	public Pelicula actualizar(Long id, Pelicula pelicula) {
		Pelicula encontrado = buscarPorId(id);
		
		if(encontrado == null) {
			return null;
		}
		encontrado.setNombre(pelicula.getNombre());
        encontrado.setDirector(pelicula.getDirector());
        encontrado.setGenero(pelicula.getGenero());
        encontrado.setDuracion(pelicula.getDuracion());
        encontrado.setDisponible(pelicula.isDisponible());
        encontrado.setCalificacion(pelicula.getCalificacion());
        
        return peliculaRepository.save(encontrado);
	}
	
	public boolean eliminar(Long id) {
		Pelicula encontrado = buscarPorId(id);
        if (encontrado == null) {
            return false;
        }
        peliculaRepository.delete(encontrado);
        return true;
	}

	
	public List<Pelicula> buscarPorGenero(String genero){
		return peliculaRepository.findByGenero(genero);
	}
	
	public List<Pelicula> buscarPorDisponible(boolean disponible){
		return peliculaRepository.findByDisponible(disponible);
	}
	
}
