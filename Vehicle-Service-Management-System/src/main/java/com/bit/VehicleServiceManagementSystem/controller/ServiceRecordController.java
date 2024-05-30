package com.bit.VehicleServiceManagementSystem.controller;

import com.bit.VehicleServiceManagementSystem.Repository.ServiceRecordRepo;
import com.bit.VehicleServiceManagementSystem.Repository.VehicleRepo;
import com.bit.VehicleServiceManagementSystem.entity.ServiceRecord;
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
@RequestMapping("/serviceRecord")

@CrossOrigin(origins = "http://localhost:5173")
public class ServiceRecordController {

    @Autowired
    private ServiceRecordRepo serviceRecordRepo;
    
    @Autowired
    private VehicleRepo vehicleRepo;


    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllVehicles() {
        try {
            List<ServiceRecord> serviceRecords = serviceRecordRepo.findAll();
            if (serviceRecords.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("No service records found");
            } else {
                return ResponseEntity.ok(serviceRecords);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve service records: " + e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> createServiceRecord(@Valid @RequestBody ServiceRecord serviceRecord, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                                        .stream()
                                        .map(error -> error.getDefaultMessage())
                                        .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        ServiceRecord savedRecord = serviceRecordRepo.save(serviceRecord);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }


    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateServiceRecord(@PathVariable Long id, @Valid @RequestBody ServiceRecord serviceRecord,
                                                 BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            if (serviceRecordRepo.existsById(id)) {
                serviceRecord.setServiceRecord_id(id);
                ServiceRecord updatedRecord = serviceRecordRepo.save(serviceRecord);
                return ResponseEntity.ok(updatedRecord);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update service record with id " + id + ": " + e.getMessage());
        }
    }
    
    

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllServiceRecords() {
        try {
            serviceRecordRepo.deleteAll();
            return ResponseEntity.ok("All service records deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete all service records: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteServiceRecordById(@PathVariable Long id) {
        try {
            if (serviceRecordRepo.existsById(id)) {
                serviceRecordRepo.deleteById(id);
                return ResponseEntity.ok("Service record with id " + id + " deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Service record with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete service record with id " + id + ": " + e.getMessage());
        }
    }

   
}
