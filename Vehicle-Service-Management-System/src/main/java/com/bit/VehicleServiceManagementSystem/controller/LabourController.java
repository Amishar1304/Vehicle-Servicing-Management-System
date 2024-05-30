package com.bit.VehicleServiceManagementSystem.controller;

import java.util.List;
import java.util.Optional;

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

import com.bit.VehicleServiceManagementSystem.Repository.LabourRepo;
import com.bit.VehicleServiceManagementSystem.entity.Labour;

@RestController
@RequestMapping("/labour")
public class LabourController {
	@Autowired
	private LabourRepo labourRepo;
	
	 @GetMapping("/getAll")
	    public ResponseEntity<List<Labour>> getAllLabour() {
	        List<Labour> labourList = labourRepo.findAll();
	        return new ResponseEntity<>(labourList, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Labour> getLabourById(@PathVariable int id) {
	        Optional<Labour> optionalLabour = labourRepo.findById(id);
	        return optionalLabour.map(labour -> new ResponseEntity<>(labour, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PostMapping("/save")
	    public ResponseEntity<Labour> addLabour(@RequestBody Labour labour) {
	        Labour savedLabour = labourRepo.save(labour);
	        return new ResponseEntity<>(savedLabour, HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Labour> updateLabour(@PathVariable int id, @RequestBody Labour labour) {
	        if (!labourRepo.existsById(id)) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        labour.setLabour_id(id);
	        Labour updatedLabour = labourRepo.save(labour);
	        return new ResponseEntity<>(updatedLabour, HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteLabour(@PathVariable int id) {
	        if (!labourRepo.existsById(id)) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        labourRepo.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
