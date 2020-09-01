package com.cleevio.eshop.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleevio.eshop.model.Watch;
import com.cleevio.eshop.service.WatchService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/eshop")
public class WatchController {

  @Autowired	
  private WatchService watchService;
  
  
  
  @PostMapping("/watches")
  @ApiOperation(value="create a new Watch", 
  notes="happy response will be HttpStatus value CREATED and created Watch object in response body.",
  response=HttpStatus.class)
  public ResponseEntity<Watch> createWatch(@Valid @RequestBody Watch watch) {
	  
	  if(watch != null) {
		  return new ResponseEntity<Watch>(watchService.createWatch(watch),HttpStatus.CREATED);
	  }
	  
	return new ResponseEntity<Watch>(HttpStatus.BAD_REQUEST);
  }
  

  @GetMapping("/watches")
  @ApiOperation(value="get All Watches which are exist in Database", 
  notes="happy response will be HttpStatus value FOUND and list of all objects.",
  response=HttpStatus.class)
  public ResponseEntity<List<Watch>> getAllWatches(){
	  if(!watchService.getAllWatches().isEmpty()) {
		  return new ResponseEntity<List<Watch>>(watchService.getAllWatches(),HttpStatus.FOUND);
	  }
	  return  new ResponseEntity<List<Watch>>(HttpStatus.NOT_FOUND);
   }
  
  
  @GetMapping("/watches/{id}")
  @ApiOperation(value="get Watch from database by Id", 
  notes="happy response will be HttpStatus value OK and watch object.",
  response=HttpStatus.class)
  public ResponseEntity<Watch> getWatchById(@PathVariable long id){
	  //Optional<Watch> watchInDB = watchService.getWatchByID(id);
	  if(watchService.getWatchByID(id) == null) {
		  return new ResponseEntity<Watch>(HttpStatus.NOT_FOUND); 
		 
	  }
	  return  new ResponseEntity<Watch>(watchService.getWatchByID(id),HttpStatus.FOUND);
	
   }
  
  
  @DeleteMapping("/watches/{id}")
  @ApiOperation(value="delete Watch from databse by its id.", 
  notes="happy response will be HttpStatus value OK .",
  response=HttpStatus.class)
  public HttpStatus deleteWatch(@PathVariable long id) {
	  
	  return watchService.deleteWatch(id);
  }
  
  
  @PutMapping("/watches/{id}")
  @ApiOperation(value="update existing Watch in database by passing its id and new watch object as a params.", 
  notes="happy response will be HttpStatus value OK .",
  response=HttpStatus.class)
  public HttpStatus updateWatch(@PathVariable long id, @Valid @RequestBody Watch watch) {
	  watch.setId(id);
	  return watchService.updateWatch(watch);
  }
  
  
}
