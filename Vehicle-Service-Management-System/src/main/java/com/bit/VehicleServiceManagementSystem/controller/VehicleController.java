package com.bit.VehicleServiceManagementSystem.controller;

import com.bit.VehicleServiceManagementSystem.Repository.CustomerRepo;
import com.bit.VehicleServiceManagementSystem.Repository.VehicleRepo;
import com.bit.VehicleServiceManagementSystem.entity.Customer;
import com.bit.VehicleServiceManagementSystem.entity.Vehicle;

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
@RequestMapping("/vehicles")
@CrossOrigin("*")
public class VehicleController {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private CustomerRepo customerRepo;

    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllVehicles() {
        try {
            List<Vehicle> vehicles = vehicleRepo.findAll();
            if (vehicles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("No vehicle records found");
            } else {
                return ResponseEntity.ok(vehicles);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve vehicle records: " + e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> createVehicle(@Valid @RequestBody Vehicle vehicle, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                                        .stream()
                                        .map(error -> error.getDefaultMessage())
                                        .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        
        // Additional validation for vehicle registration number
        if (!vehicle.isValidRegistration()) {
            return ResponseEntity.badRequest().body("Invalid vehicle registration number.");
        }
        
        Vehicle savedVehicle = vehicleRepo.save(vehicle);
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @Valid @RequestBody Vehicle vehicleDetails, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                                        .stream()
                                        .map(error -> error.getDefaultMessage())
                                        .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            Optional<Vehicle> optionalVehicle = vehicleRepo.findById(id);
            if (optionalVehicle.isPresent()) {
                Vehicle existingVehicle = optionalVehicle.get();
             
                existingVehicle.setVehicleType(vehicleDetails.getVehicleType());
                existingVehicle.setVehicleRegNo(vehicleDetails.getVehicleRegNo());
                existingVehicle.setHasPolicy(vehicleDetails.isHasPolicy());
                Vehicle updatedVehicle = vehicleRepo.save(existingVehicle);
                return ResponseEntity.ok(updatedVehicle);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to update vehicle with id " + id + ": " + e.getMessage());
        }
    }


    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllVehicles() {
        try {
            vehicleRepo.deleteAll();
            return ResponseEntity.ok("All vehicles deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to delete all vehicles: " + e.getMessage());
        }
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteVehicleById(@PathVariable Long id) {
        try {
            Optional<Vehicle> vehicle = vehicleRepo.findById(id);
            if (vehicle.isPresent()) {
                vehicleRepo.deleteById(id);
                return ResponseEntity.ok("Vehicle with id " + id + " deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Vehicle with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to delete vehicle with id " + id + ": " + e.getMessage());
        }
    }
    
    

}
