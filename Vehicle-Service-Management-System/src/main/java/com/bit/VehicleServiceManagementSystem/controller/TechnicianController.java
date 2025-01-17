
package com.bit.VehicleServiceManagementSystem.controller;

import com.bit.VehicleServiceManagementSystem.Repository.TechnicianRepo;
import com.bit.VehicleServiceManagementSystem.entity.ServiceRecord;
import com.bit.VehicleServiceManagementSystem.entity.Technician;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/technician")
@CrossOrigin(origins = "http://localhost:5173")
public class TechnicianController {

    @Autowired
    private TechnicianRepo technicianRepo;

//    @PostMapping("/save")
//    public ResponseEntity<Technician> createVehicle(@RequestBody Technician technician) {
//        Technician technicianRecord = technicianRepo.save(technician);
//        return new ResponseEntity<>(technicianRecord, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<List<Technician>> getAllVehicles() {
//        List<Technician> technicianRecord = technicianRepo.findAll();
//        return new ResponseEntity<>(technicianRecord, HttpStatus.OK);
//    }
    
    
    
    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllVehicles() {
        try {
            List<Technician> technicianRecords = technicianRepo.findAll();
            if (technicianRecords.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("No technician records found");
            } else {
                return ResponseEntity.ok(technicianRecords);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve technician records: " + e.getMessage());
        }
    }
    
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getTechnicianById(@PathVariable Long id) {
        try {
            // Find the technician by ID
            Optional<Technician> technicianOptional = technicianRepo.findById(id);
            
            // Check if technician exists
            if (technicianOptional.isPresent()) {
                // Technician found, return it in the response
                Technician technician = technicianOptional.get();
                return ResponseEntity.ok(technician);
            } else {
                // Technician not found, return a not found response
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Technician with ID " + id + " not found");
            }
        } catch (Exception e) {
            // Handle any unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve technician: " + e.getMessage());
        }
    }

    

    
    @PostMapping("/save")
    public ResponseEntity<?> createTechnician(@Valid @RequestBody Technician technician, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                                        .stream()
                                        .map(error -> error.getDefaultMessage())
                                        .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Technician savedTechnician = technicianRepo.save(technician);
        return new ResponseEntity<>(savedTechnician, HttpStatus.CREATED);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTechnician(@PathVariable Long id, @Valid @RequestBody Technician technicianDetails, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                                        .stream()
                                        .map(error -> error.getDefaultMessage())
                                        .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        
        Technician existingTechnician = technicianRepo.findById(id).get();
        if (existingTechnician != null) {
            existingTechnician.setName(technicianDetails.getName());
            existingTechnician.setContact(technicianDetails.getContact());
          
            try {
                Technician updatedTechnician = technicianRepo.save(existingTechnician);
                return ResponseEntity.ok(updatedTechnician);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body("Failed to update technician with id " + id + ": " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Technician with id " + id + " not found");
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllTechnicians() {
        try {
            technicianRepo.deleteAll();
            return ResponseEntity.ok("All technicians deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to delete all technicians: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteTechnicianById(@PathVariable Long id) {
        try {
            Technician technician = technicianRepo.findById(id).get();
            if (technician != null) {
                technicianRepo.deleteById(id);
                return ResponseEntity.ok("Technician with id " + id + " deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Technician with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to delete technician with id " + id + ": " + e.getMessage());
        }
    }

}

