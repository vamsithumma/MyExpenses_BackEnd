package com.vamsi.transactions.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vamsi.transactions.entity.User;
import com.vamsi.transactions.exception.ResourceNotFoundException;
import com.vamsi.transactions.exception.UserExistException;
import com.vamsi.transactions.repository.UserRepository;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
    private UserRepository userRepository;
	
	@PostMapping("/users")
	public User CreateUser(@Valid @RequestBody User user) 
		throws UserExistException, ResourceNotFoundException{
		if(findUserExist(user.getEmail(),user.getUsername()))
			return userRepository.save(user);
		else
			throw new UserExistException("User Exists");
	}
	
	
	public Boolean findUserExist(String email, String username)
			throws ResourceNotFoundException {
    	User user = userRepository.findByEmail(email);
    	User user1= userRepository.findByUsername(username);
    	if(user!=null || user1!=null) {
    		return false;
   		}
		return true;
	}
	
	
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	@GetMapping("/userlogin/{username}&{password}")
   	public ResponseEntity<User> userLogin(@PathVariable(value = "username") String username,@PathVariable(value="password") String password )
   			throws ResourceNotFoundException {
    	System.out.println(username+password);
   		User user = userRepository.getLoginStatus(username, password);
   		if(user==null) {
   			throw new ResourceNotFoundException("No User Found");
   		}
   				//.orElseThrow(() -> new ResourceNotFoundException("User not found for this name :: " + username));
   		return ResponseEntity.ok().body(user);
   	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value= "id") Long userId) 
		throws ResourceNotFoundException {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
			return ResponseEntity.ok(user);
	}
	
	@PutMapping("users/{id}")
	public ResponseEntity<User> UpdateUserById(@PathVariable(value= "id") Long userId, @Valid @RequestBody User userDetails)
		throws ResourceNotFoundException, UserExistException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		if((findUserExist(userDetails.getEmail(),userDetails.getUsername())==false)&&(user.getEmail() == userDetails.getEmail())&&(user.getUsername() == userDetails.getUsername()) ){
			throw new UserExistException("User Exists");
		}
		else {
			user.setName(userDetails.getName());
			user.setEmail(userDetails.getEmail());
			user.setPassword(userDetails.getPassword());
			user.setUsername(userDetails.getUsername());
			user.setRole(userDetails.getRole());
			final User updatedUser = userRepository.save(user);
			return ResponseEntity.ok(updatedUser);
		}
	}
	
	
	@DeleteMapping("/users/delete/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
    	User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
		
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
