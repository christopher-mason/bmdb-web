package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Credit;
import com.bmdb.db.CreditRepo;

@CrossOrigin
@RestController
@RequestMapping("/credits")
public class CreditController {

	/*
	 *  A controller will implement 5 CRUD methods:
	 *  1) GET ALL
	 *  2) GET BY ID
	 *  3) POST - Insert/Create
	 *  4) PUT - Update
	 *  5) DELETE - Delete
	 */
	
	@Autowired
	public CreditRepo creditRepo;
	
	// Get all Credits
	@GetMapping("/")
	public List<Credit> getAllCredits() {
		return creditRepo.findAll();
	}
	
	// Get Credit by ID
	@GetMapping("/{id}")
	public Optional<Credit> getCreditById(@PathVariable int id) {
		return creditRepo.findById(id);
	}
	
	// Add Credit
	@PostMapping("/")
	public Credit addCredit(@RequestBody Credit c) {
		c = creditRepo.save(c);
		return c;
	}
	
	// Update Credit
	@PutMapping("/")
	public Credit updateCredit(@RequestBody Credit c) {
		c = creditRepo.save(c);
		return c;
	}
	
	// Delete User
		@DeleteMapping("/{id}")
		public Credit deleteCredit(@PathVariable int id) {
			Optional<Credit> c = creditRepo.findById(id);
			if (c.isPresent()) {
				creditRepo.deleteById(id);
			} else {
				System.out.println("Error - Credit not found with ID: " + id);
			}
			return c.get();
		}
}
