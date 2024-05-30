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

import com.bit.VehicleServiceManagementSystem.Repository.VehicleEquipmentsRepo;
import com.bit.VehicleServiceManagementSystem.entity.VehicleEquipments;

@RestController
@RequestMapping("/equipments")
public class VehicleEquipmentsController {
	@Autowired
	private VehicleEquipmentsRepo vehicleEquipmentsRepo;
	
	 @GetMapping("/getAll")
	    public ResponseEntity<List<VehicleEquipments>> getAllEquipments() {
	        List<VehicleEquipments> equipmentList = vehicleEquipmentsRepo.findAll();
	        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<VehicleEquipments> getEquipmentById(@PathVariable int id) {
	        Optional<VehicleEquipments> optionalEquipment = vehicleEquipmentsRepo.findById(id);
	        return optionalEquipment.map(equipment -> new ResponseEntity<>(equipment, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PostMapping("/save")
	    public ResponseEntity<VehicleEquipments> addEquipment(@RequestBody VehicleEquipments equipment) {
	        VehicleEquipments savedEquipment = vehicleEquipmentsRepo.save(equipment);
	        return new ResponseEntity<>(savedEquipment, HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<VehicleEquipments> updateEquipment(@PathVariable int id, @RequestBody VehicleEquipments equipment) {
	        if (!vehicleEquipmentsRepo.existsById(id)) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        equipment.setEquipment_id(id);
	        VehicleEquipments updatedEquipment = vehicleEquipmentsRepo.save(equipment);
	        return new ResponseEntity<>(updatedEquipment, HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEquipment(@PathVariable int id) {
	        if (!vehicleEquipmentsRepo.existsById(id)) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        vehicleEquipmentsRepo.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

}
