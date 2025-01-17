package com.bit.VehicleServiceManagementSystem.controller;

import com.bit.VehicleServiceManagementSystem.Repository.CustomerRepo;
import com.bit.VehicleServiceManagementSystem.Repository.ServiceRecordRepo;
import com.bit.VehicleServiceManagementSystem.entity.Customer;
import com.bit.VehicleServiceManagementSystem.entity.ServiceRecord;
import com.bit.VehicleServiceManagementSystem.service.PdfGeneratorService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/customers")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepository;
    
    @Autowired
    private ServiceRecordRepo serviceRecordRepo;
  
  
   /* @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }
    

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setCustomerName(customerDetails.getCustomerName());
            existingCustomer.setCustomerContact(customerDetails.getCustomerContact());
            existingCustomer.setServiceCenter(customerDetails.getServiceCenter());
            Customer updatedCustomer = customerRepository.save(existingCustomer);
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllCustomers() {
        customerRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    
    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<Customer> customers = customerRepository.findAll();
            if (customers.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("No customers found");
            } else {
                return ResponseEntity.ok(customers);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve customers: " + e.getMessage());
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isPresent()) {
                return ResponseEntity.ok(customer.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Customer with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve customer with id " + id + ": " + e.getMessage());
        }
    }
   
    
    @PostMapping("/save")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                                        .stream()
                                        .map(ObjectError::getDefaultMessage)
                                        .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }


//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customerDetails, BindingResult result) {
//        if (result.hasErrors()) {
//            List<String> errors = result.getAllErrors()
//                    .stream()
//                    .map(ObjectError::getDefaultMessage)
//                    .collect(Collectors.toList());
//            return ResponseEntity.badRequest().body(errors);
//        }
//
//        Optional<Customer> optionalCustomer = customerRepository.findById(id);
//        if (optionalCustomer.isPresent()) {
//            Customer existingCustomer = optionalCustomer.get();
//            existingCustomer.setCustomerName(customerDetails.getCustomerName());
//            existingCustomer.setCustomerContact(customerDetails.getCustomerContact());
//            existingCustomer.setServiceCenter(customerDetails.getServiceCenter());
//            Customer updatedCustomer = customerRepository.save(existingCustomer);
//            return ResponseEntity.ok(updatedCustomer);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customerDetails, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
            if (optionalCustomer.isPresent()) {
                Customer existingCustomer = optionalCustomer.get();
                // Update customer details with the provided data
                existingCustomer.setCustomerName(customerDetails.getCustomerName());
                existingCustomer.setCustomerContact(customerDetails.getCustomerContact());
                existingCustomer.setServiceCenter(customerDetails.getServiceCenter());
                // Save the updated customer record
                Customer updatedCustomer = customerRepository.save(existingCustomer);
                return ResponseEntity.ok(updatedCustomer);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update customer with id " + id + ": " + e.getMessage());
        }
    }


    
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllCustomers() {
        try {
            customerRepository.deleteAll();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete all customers: " + e.getMessage());
        }
    }

    
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isPresent()) {
                customerRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete customer with id " + id + ": " + e.getMessage());
        }
    }
    
    
   /* @GetMapping("/getByContact/{customerContact}")
    public ResponseEntity<?> getCustomerByContact(@PathVariable String customerContact) {
        try {
            Optional<Customer> customer = customerRepository.findByCustomerContact(customerContact);
            if (customer.isPresent()) {
                return ResponseEntity.ok(customer.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with contact number " + customerContact + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve customer with contact number " + customerContact + ": " + e.getMessage());
        }
    }*/
    
    @GetMapping("/getByContact/{customerContact}")
    public ResponseEntity<?> getCustomerByContact(@PathVariable String customerContact) {
        try {
            Optional<Customer> customer = customerRepository.findByCustomerContact(customerContact);
            if (customer.isPresent()) {
                // Fetch complete service record details for the customer
                List<ServiceRecord> serviceRecords = serviceRecordRepo.findByVehicle_Customer(customer.get());

                // Construct a Map to represent the customer and their service records
                Map<String, Object> customerWithServiceRecords = new HashMap<>();
                customerWithServiceRecords.put("customer", customer.get());
                customerWithServiceRecords.put("serviceRecords", serviceRecords);

                return ResponseEntity.ok(customerWithServiceRecords);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with contact number " + customerContact + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve customer with contact number " + customerContact + ": " + e.getMessage());
        }
    }
    
    
    
    
    
}
