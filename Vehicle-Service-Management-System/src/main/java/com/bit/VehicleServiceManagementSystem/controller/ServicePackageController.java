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

import com.bit.VehicleServiceManagementSystem.Repository.ServicePackageRepo;
import com.bit.VehicleServiceManagementSystem.entity.ServicePackage;

@RestController
@RequestMapping("/packages")
public class ServicePackageController {
	
	@Autowired
	private ServicePackageRepo servicePackageRepo;
	
	 @GetMapping("/getAll")
	    public ResponseEntity<List<ServicePackage>> getAllServicePackages() {
	        List<ServicePackage> servicePackages = servicePackageRepo.findAll();
	        return new ResponseEntity<>(servicePackages, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ServicePackage> getServicePackageById(@PathVariable Integer id) {
	        Optional<ServicePackage> optionalServicePackage = servicePackageRepo.findById(id);
	        return optionalServicePackage.map(servicePackage -> new ResponseEntity<>(servicePackage, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PostMapping("/save")
	    public ResponseEntity<ServicePackage> createServicePackage(@RequestBody ServicePackage servicePackage) {
	        ServicePackage createdServicePackage = servicePackageRepo.save(servicePackage);
	        return new ResponseEntity<>(createdServicePackage, HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ServicePackage> updateServicePackage(@PathVariable Integer id, @RequestBody ServicePackage servicePackage) {
	        if (!servicePackageRepo.existsById(id)) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        servicePackage.setServicePackage_id(id);
	        ServicePackage updatedServicePackage = servicePackageRepo.save(servicePackage);
	        return new ResponseEntity<>(updatedServicePackage, HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteServicePackage(@PathVariable Integer id) {
	        if (!servicePackageRepo.existsById(id)) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        servicePackageRepo.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

}
