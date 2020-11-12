package com.bmdb.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Actor;
import com.bmdb.db.ActorRepo;

@CrossOrigin
@RestController
@RequestMapping("/actors")
public class ActorController {
	/*
	 *  A controller will implement 5 CRUD methods:
	 *  1) GET ALL
	 *  2) GET BY ID
	 *  3) POST - Insert/Create
	 *  4) PUT - Update
	 *  5) DELETE - Delete
	 */
	
	@Autowired
	private ActorRepo actorRepo; 
	
	// Get all actors
	@GetMapping("/")
	public List<Actor> getAll() {
		return actorRepo.findAll();
	}
	
	// Get a actor by ID
	@GetMapping("/{id}")
	public Optional<Actor> getById(@PathVariable int id) {
		return actorRepo.findById(id);
	}
	
	// Add a actor
	@PostMapping("/")
	public Actor addActor(@RequestBody Actor a) {
		a = actorRepo.save(a);
		return a;
	}
	
	// Update an actor
	@PutMapping("/")
	public Actor updateActor(@RequestBody Actor a) {
		a = actorRepo.save(a);
		return a;
	}
	
	// Delete actor
	@DeleteMapping("/{id}")
	public Actor deleteActor(@PathVariable int id) {
		// Optional type will wrap an actor
		Optional<Actor> a = actorRepo.findById(id);
		// isPresent() will return true if a movie was found
		if (a.isPresent()) {
			actorRepo.deleteById(id);
		} else {
			System.out.println("Error - Actor not found for id: " + id);
		}
		return a.get();
	}
	
	// List all actors by gender
	// using RequestParam to pass gender
	@GetMapping("/find-by-gender")
	public List<Actor> getAll(@RequestParam String gender) {
		return actorRepo.findByGender(gender);
	}
	// List actors by last name
	@GetMapping("/find-by-last-name")
	public List<Actor> getByLastName(@RequestParam String lastName) {
		return actorRepo.findByLastName(lastName);
	}
	
	// List actors by last name starting with letter
	@GetMapping("/find-by-last-name-by-letter")
	public List<Actor> getLastNameByLetter(@RequestParam String letter) {
		return actorRepo.findByLastNameLike(letter+"%");
	}
		
	// List actors born between d1 and d2
	@GetMapping("/find-by-bithdate-between")
	public List<Actor> getActorsByBirthDateBetween(@RequestParam String d1, String d2) {
		LocalDate ld1 = LocalDate.parse(d1);
		LocalDate ld2 = LocalDate.parse(d2);
		return actorRepo.findByBirthDateBetween(ld1, ld2);
	}
}
