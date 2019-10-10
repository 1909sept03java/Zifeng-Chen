package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.model.Flashcard;
import com.revature.service.FlashcardService;

@Controller
@RequestMapping(value="/flashcard")
public class FlashcardController {
	
	private FlashcardService flashcardService;
	
	@Autowired // setter injection
	public void setFlashcardService(FlashcardService flashcardService) {
		this.flashcardService = flashcardService;
	}
	
	@ResponseBody // tells Spring to skip ViewResolver
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Flashcard>> getAll() {
		return new ResponseEntity<>(this.flashcardService.allFlashcards(), HttpStatus.OK);
	}
	@ResponseBody // tells Spring to skip ViewResolver
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Flashcard> getFlashcardById(@PathVariable int id) {
		Flashcard f = this.flashcardService.getFlashcardById(id);
		if (f==null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(f, HttpStatus.OK);
	}
	@ResponseBody // tells Spring to skip ViewResolver
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> addFlashcard(@RequestBody Flashcard flashcard){
		ResponseEntity<String> resp = null;
		try {
			this.flashcardService.addFlashcard(flashcard);
			resp = new ResponseEntity<>("Done",HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
		}		
		return resp;
		
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public  ResponseEntity<String>  updateFlashcard(@RequestBody Flashcard flashcard) {
		//System.out.println(flashcard);
		ResponseEntity<String> resp = null;
		if(flashcardService.editFlashcard(flashcard))
			return resp = new ResponseEntity<>("Done",HttpStatus.OK);
		else
			return resp = new ResponseEntity<>("No Flashcard found",HttpStatus.BAD_REQUEST);
		//return null;
	//	return new ResponseEntity<>(flashcard, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE)
	public  ResponseEntity<String>  deleteFlashcard(@RequestBody Flashcard flashcard) {
		ResponseEntity<String> resp = null;
		if(flashcardService.deleteFlashcard(flashcard))
			return resp = new ResponseEntity<>("Done",HttpStatus.OK);
		else
			return resp = new ResponseEntity<>("No Flashcard found",HttpStatus.BAD_REQUEST);
		
	}
}
