package com.learning.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraintvalidation.SupportedValidationTarget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoServices service; 
	
	//GET  /users
	//retrieveAllUsers
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	//GET /user/{id}
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id ) {
		User user = service.findOne(id);
		if(user==null) throw new UserNotFoundExcpetion("id "+ id );
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	//POST
	//CREATED
	//input -  details of user
	//output -  CREATED and Return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saveUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri(); 
		return ResponseEntity.created(location).build();
		
	}
	
	//DELETE /user/{id}
		@DeleteMapping("/users/{id}")
		public void deleteUser(@PathVariable int id ) {
			User user = service.deleteById(id);
			if(user==null) throw new UserNotFoundExcpetion("id "+ id );
			
		}
	

}
