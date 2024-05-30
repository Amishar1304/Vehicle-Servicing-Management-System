//package com.bit.VehicleServiceManagementSystem.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bit.VehicleServiceManagementSystem.Repository.ServiceDetailRepo;
//import com.bit.VehicleServiceManagementSystem.Repository.VehicleRepo;
//import com.bit.VehicleServiceManagementSystem.entity.ServiceDetail;
//import com.bit.VehicleServiceManagementSystem.entity.Vehicle;
//
//@RestController
//@RequestMapping("/serviceDetails")
//public class ServiceDetailsController {
//	 @Autowired
//	    private VehicleRepo vehicleRepository;
//	 @Autowired
//	 private ServiceDetailRepo serviceDetailRepo;
//
//	   /* @GetMapping("/details")
////	    public List<Vehicle> getAllServiceDetails() {
////	        return vehicleRepository.findAll();
////	    }
//	    public ResponseEntity<?> getServiceDetail(@PathVariable("id") Long id) {
//	        Optional<ServiceDetail> serviceDetailOptional = serviceDetailRepo.findById(id);
//	        if (serviceDetailOptional.isPresent()) {
//	            ServiceDetail serviceDetail = serviceDetailOptional.get();
//	            String technicianName = serviceDetail.getTechnician().getName();
//	            Vehicle vehicle = serviceDetail.getVehicle();
//
//	            Map<String, Object> response = new HashMap<>();
//	            response.put("technicianName", technicianName);
//	            response.put("vehicleDetails", vehicle);
//
//	            return ResponseEntity.ok(response);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }*/
//	 
//	 @GetMapping("/details/{id}") 
//	    public ResponseEntity<Map<String, Object>> getServiceDetail(@PathVariable("id") Long id) {
//	        Optional<ServiceDetail> serviceDetailOptional = serviceDetailRepo.findById(id);
//	        if (serviceDetailOptional.isPresent()) {
//	            ServiceDetail serviceDetail = serviceDetailOptional.get();
//	            String technicianName = serviceDetail.getTechnician().getName();
//	            Vehicle vehicle = serviceDetail.getVehicle();
//
//	            Map<String, Object> response = new HashMap<>();
//	            response.put("technicianName", technicianName);
//	            response.put("vehicleDetails", vehicle);
//
//	            return ResponseEntity.ok(response);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }
//
//}
