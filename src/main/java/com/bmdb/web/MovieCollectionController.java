package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.MovieCollection;
import com.bmdb.db.MovieCollectionRepo;

@CrossOrigin
@RestController
@RequestMapping("/movie-collections")
public class MovieCollectionController { 
	/*
	 *  A controller will implement 5 CRUD methods:
	 *  1) GET ALL
	 *  2) GET BY ID
	 *  3) POST - Insert/Create
	 *  4) PUT - Update
	 *  5) DELETE - Delete
	 */
	
	@Autowired
	private MovieCollectionRepo movieCollectionRepo; 
	
	// Get all movieCollections
	@GetMapping("/")
	public List<MovieCollection> getAll() {
		return movieCollectionRepo.findAll();
	}
	
	// Get a movieCollection by ID
	@GetMapping("/{id}")
	public Optional<MovieCollection> getById(@PathVariable int id) {
		return movieCollectionRepo.findById(id);
	}
	
	// Add a movieCollection
	@PostMapping("/")
	public MovieCollection addMovieCollection(@RequestBody MovieCollection mc) {
		mc = movieCollectionRepo.save(mc);
		return mc;
	}
	
	// Update a movieCollection
	@PutMapping("/")
	public MovieCollection updateMovieCollection(@RequestBody MovieCollection mc) {
		mc = movieCollectionRepo.save(mc);
		return mc;
	}
	
	// Delete movieCollection
	@DeleteMapping("/{id}")
	public MovieCollection deleteMovieCollection(@PathVariable int id) {
		// Optional type will wrap a movieCollection
		Optional<MovieCollection> mc = movieCollectionRepo.findById(id);
		// isPresent() will return true if a movieCollection was found
		if (mc.isPresent()) {
			movieCollectionRepo.deleteById(id);
		} else {
			System.out.println("Error - MovieCollection not found for id: " + id);
		}
		return mc.get();
	}
}
