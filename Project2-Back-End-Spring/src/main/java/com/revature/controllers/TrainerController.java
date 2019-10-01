package com.revature.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.TrainerNotFoundException;
import com.revature.models.Trainer;
import com.revature.services.TrainerService;

@RestController 
@CrossOrigin(maxAge = 3600)
@RequestMapping("/users")
public class TrainerController {

	@Autowired
	private TrainerService trainerService;	
	
	@GetMapping
	public List<Trainer> getAll(){
		return trainerService.findAllTrainers();
	}
//	@GetMapping("/{userName}")
//	public List<Trainer> getAllbyUsername(@RequestParam(value="userName",required=false)String userName){
//		if(userName==null) {
//			throw new TrainerNotFoundException();
//		}
//			return trainerService.findTrainersByuserName(userName);
//	}	
	@GetMapping("/{id}")
	public Trainer getTrainersById(@PathVariable("id")Integer id) {
		Trainer t=trainerService.findTrainerById(id);
		if (t==null) {
			throw new TrainerNotFoundException();
		}
		return t;
	}
//	@GetMapping("/{userId}/pokemons")
//	public List<Trainer> getAllPokemonByuserId(@PathVariable("userId")Integer userid) {
//		return trainerService.findAllPkemonsByTrainerId(userid);
//	} // move to the pokemon controller
	@PostMapping
	public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer){
		trainerService.addTrainer(trainer);
		return new ResponseEntity<Trainer>(trainer, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public Trainer updateTrainer(@PathVariable("id")Integer id, @RequestBody Trainer t) {
		t.setTrainerId(id);
		return trainerService.updateTrainer(t);		
	}
	
	@DeleteMapping("/{userName}")
	public Trainer deleteTrainer(@PathVariable("userName")String username) {
		Trainer trainer =  trainerService.findTrainerByuserName(username);
		if (trainer==null) {
			throw new TrainerNotFoundException();
		}				
		return trainerService.deleteTrainer(trainer);
	}

}
