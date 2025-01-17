package com.bit.VehicleServiceManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_id")
    private Long vehicle_id;

    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;
    
    @NotBlank(message = "Vehicle registration number is required")
    private String vehicleRegNo;
    
    @NotNull(message = "Policy status is required")
    private boolean hasPolicy;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
    
//    @ManyToOne
//    @JoinColumn(name = "technician_id") 
//    private Technician technician;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vehicle")
    @JsonManagedReference
    private List<ServiceRecord> serviceRecords;

    public Vehicle() {
        // Default constructor
    }

    public Vehicle(Long vehicle_id, @NotBlank(message = "Vehicle type is required") String vehicleType,
                   @NotBlank(message = "Vehicle registration number is required") String vehicleRegNo,
                   @NotNull(message = "Policy status is required") boolean hasPolicy, Customer customer,
                   List<ServiceRecord> serviceRecords) {
        this.vehicle_id = vehicle_id;
        this.vehicleType = vehicleType;
        this.vehicleRegNo = vehicleRegNo;
        this.hasPolicy = hasPolicy;
        this.customer = customer;
        this.serviceRecords = serviceRecords;
    }

    // Getters and setters

    public Long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public void setVehicleRegNo(String vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    public boolean isHasPolicy() {
        return hasPolicy;
    }

    public void setHasPolicy(boolean hasPolicy) {
        this.hasPolicy = hasPolicy;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ServiceRecord> getServiceRecords() {
        return serviceRecords;
    }

    public void setServiceRecords(List<ServiceRecord> serviceRecords) {
        this.serviceRecords = serviceRecords;
    }
    
    
    

//    public Technician getTechnician() {
//		return technician;
//	}
//
//	public void setTechnician(Technician technician) {
//		this.technician = technician;
//	}

	public boolean isValidRegistration() {
        String pattern = "^(BH|RJ|WB)\\d{4}$";
        return vehicleRegNo != null && vehicleRegNo.matches(pattern);
    }

	

	
}
