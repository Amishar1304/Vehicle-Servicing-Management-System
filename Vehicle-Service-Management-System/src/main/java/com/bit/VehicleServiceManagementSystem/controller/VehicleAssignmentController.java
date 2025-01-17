package com.bit.VehicleServiceManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.VehicleServiceManagementSystem.Repository.ServiceRecordRepo;
import com.bit.VehicleServiceManagementSystem.Repository.VehicleAssignmentRepo;
import com.bit.VehicleServiceManagementSystem.entity.VehicleAssignment;

@RestController
@RequestMapping("/VehicleAssigned")
@CrossOrigin(origins = "http://localhost:5173")
public class VehicleAssignmentController {
	 @Autowired
	    private VehicleAssignmentRepo assignmentRepository;
	 
	 
	 @GetMapping("/getAll")
	    public List<VehicleAssignment> getAllVehicleAssignments() {
	        return assignmentRepository.findAll();
	        
	    }
	
	    @PostMapping("/save")
	    public VehicleAssignment assignVehicleToTechnician(@RequestBody VehicleAssignment assignment) {
	        return assignmentRepository.save(assignment);
	    }

	    @DeleteMapping("/deleteById/{id}")
	    public void deleteVehicleAssignment(@PathVariable Long id) {
	        assignmentRepository.deleteById(id);
	    }

}
