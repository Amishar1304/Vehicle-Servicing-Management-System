package com.bit.VehicleServiceManagementSystem.controller;

import com.bit.VehicleServiceManagementSystem.Repository.ServiceCenterRepo;
import com.bit.VehicleServiceManagementSystem.entity.ServiceCenter;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/serviceCenter")
public class ServiceCenterController {

    @Autowired
    private ServiceCenterRepo serviceCenterRepository;


//    @PostMapping("/save")
//    public ResponseEntity<ServiceCenter> createServiceCenter(@RequestBody ServiceCenter serviceCenter) {
//        ServiceCenter savedServiceCenter = serviceCenterRepository.save(serviceCenter);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedServiceCenter);
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<List<ServiceCenter>> getAllServiceCenters() {
//        List<ServiceCenter> serviceCenters = serviceCenterRepository.findAll();
//        return ResponseEntity.ok(serviceCenters);
//    }
//
//    @GetMapping("/getByID/{id}")
//    public ResponseEntity<ServiceCenter> getServiceCenterById(@PathVariable Long id) {
//        Optional<ServiceCenter> serviceCenter = serviceCenterRepository.findById(id);
//        return serviceCenter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<ServiceCenter> updateServiceCenter(@PathVariable Long id, @RequestBody ServiceCenter serviceCenterDetails) {
//        Optional<ServiceCenter> optionalServiceCenter = serviceCenterRepository.findById(id);
//        if (optionalServiceCenter.isPresent()) {
//            ServiceCenter existingServiceCenter = optionalServiceCenter.get();
//            existingServiceCenter.setServiceCenterName(serviceCenterDetails.getServiceCenterName());
//            existingServiceCenter.setServiceCenterAddress(serviceCenterDetails.getServiceCenterAddress());
//            existingServiceCenter.setServiceCenterContact(serviceCenterDetails.getServiceCenterContact());
//            existingServiceCenter.setCustomers(serviceCenterDetails.getCustomers());
//            ServiceCenter updatedServiceCenter = serviceCenterRepository.save(existingServiceCenter);
//            return ResponseEntity.ok(updatedServiceCenter);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/deleteAll")
//    public ResponseEntity<Void> deleteAllServiceCenters() {
//        serviceCenterRepository.deleteAll();
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/deleteById/{id}")
//    public ResponseEntity<Void> deleteServiceCenterById(@PathVariable Long id) {
//        Optional<ServiceCenter> serviceCenter = serviceCenterRepository.findById(id);
//        if (serviceCenter.isPresent()) {
//            serviceCenterRepository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllServiceCenters() {
        try {
            List<ServiceCenter> serviceCenters = serviceCenterRepository.findAll();
            if (serviceCenters.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No service centers found");
            } else {
                return ResponseEntity.ok(serviceCenters);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve service centers: " + e.getMessage());
        }
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<?> getServiceCenterById(@PathVariable Long id) {
        try {
            Optional<ServiceCenter> serviceCenter = serviceCenterRepository.findById(id);
            if (serviceCenter.isPresent()) {
                return ResponseEntity.ok(serviceCenter.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body("Service center with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body("Failed to retrieve service center with id " + id + ": " + e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> createServiceCenter(@Valid @RequestBody ServiceCenter serviceCenter, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                                        .stream()
                                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                        .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        ServiceCenter savedServiceCenter = serviceCenterRepository.save(serviceCenter);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedServiceCenter);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateServiceCenter(@PathVariable Long id, @Valid @RequestBody ServiceCenter serviceCenterDetails, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                                        .stream()
                                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                        .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Optional<ServiceCenter> optionalServiceCenter = serviceCenterRepository.findById(id);
            if (optionalServiceCenter.isPresent()) {
                ServiceCenter existingServiceCenter = optionalServiceCenter.get();
                existingServiceCenter.setServiceCenterName(serviceCenterDetails.getServiceCenterName());
                existingServiceCenter.setServiceCenterAddress(serviceCenterDetails.getServiceCenterAddress());
                existingServiceCenter.setServiceCenterContact(serviceCenterDetails.getServiceCenterContact());
                existingServiceCenter.setCustomers(serviceCenterDetails.getCustomers());
                ServiceCenter updatedServiceCenter = serviceCenterRepository.save(existingServiceCenter);
                return ResponseEntity.ok(updatedServiceCenter);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service center with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update service center: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllServiceCenters() {
        try {
            serviceCenterRepository.deleteAll();
            return ResponseEntity.ok("All service centers deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete all service centers: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteServiceCenterById(@PathVariable Long id) {
        try {
            Optional<ServiceCenter> serviceCenter = serviceCenterRepository.findById(id);
            if (serviceCenter.isPresent()) {
                serviceCenterRepository.deleteById(id);
                return ResponseEntity.ok("Service center with id " + id + " deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service center with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete service center with id " + id + ": " + e.getMessage());
        }
    }

}
