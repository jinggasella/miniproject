package com.eksad.miniproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.miniproject.domain.Person;
import com.eksad.miniproject.exception.DataNotFoundException;
import com.eksad.miniproject.repository.PersonRepository;

@RestController
//@RequestMapping(value = "api/v1")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/person")
	public List<Person> GetAllPerson(){
		return personRepository.findAll();
	}
	@PostMapping(value = "/savePerson")
	public Person InsertPerson(@Valid @RequestBody Person person) {
		return personRepository.save(person);
	}
	
	@PutMapping(value = "/perosn/{id}")
	public ResponseEntity<Person> UpdatePerson(@PathVariable(value = "id") Long personId, @Valid @RequestBody Person personRequest) 
			throws DataNotFoundException {
		
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new DataNotFoundException("Person not found for this id :: " + personId));
		
		person.setName(personRequest.getName());
		
		final Person updatePerson  = personRepository.save(person);
		
		return ResponseEntity.ok().body(updatePerson);
	}
	
	public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId) throws DataNotFoundException {
		
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new DataNotFoundException("Person not found fot this id :: " + personId));
		
		personRepository.delete(person);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
