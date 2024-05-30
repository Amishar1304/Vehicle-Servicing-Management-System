package com.bit.VehicleServiceManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


import java.util.List;

@Entity
@Transactional

public class ServiceCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviceCenter_id")
    private Long serviceCenter_id;

    @NotBlank(message ="Service center name is required")
    private String serviceCenterName;
    
    @NotBlank(message ="Service center address is required")
    private String serviceCenterAddress;
    
    @NotNull(message = "Contact number shouldn't be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number entered")
    private String serviceCenterContact;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "serviceCenter")
    @JsonManagedReference
    private List<Customer> customers;

    
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "serviceCenter")
//    @JsonManagedReference
//    private List<Technician> technicians;
    
    @OneToMany(mappedBy = "serviceCenter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ServicePackage> servicePackages;
    
    
    @OneToMany(mappedBy = "serviceCenter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<VehicleEquipments> vehicleEquipments;
    
    
    @OneToMany(mappedBy = "serviceCenter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Labour> labours;

	public ServiceCenter() {
		super();
	}

	public ServiceCenter(Long serviceCenter_id,
			@NotBlank(message = "Service center name is required") String serviceCenterName,
			@NotBlank(message = "Service center address is required") String serviceCenterAddress,
			@NotNull(message = "Contact number shouldn't be empty") @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number entered") String serviceCenterContact,
			List<Customer> customers, /*List<Technician> technicians,*/ List<ServicePackage> servicePackages,
			List<VehicleEquipments> vehicleEquipments, List<Labour> labours) {
		super();
		this.serviceCenter_id = serviceCenter_id;
		this.serviceCenterName = serviceCenterName;
		this.serviceCenterAddress = serviceCenterAddress;
		this.serviceCenterContact = serviceCenterContact;
		this.customers = customers;
		//this.technicians = technicians;
		this.servicePackages = servicePackages;
		this.vehicleEquipments = vehicleEquipments;
		this.labours = labours;
	}

	public Long getServiceCenter_id() {
		return serviceCenter_id;
	}

	public void setServiceCenter_id(Long serviceCenter_id) {
		this.serviceCenter_id = serviceCenter_id;
	}

	public String getServiceCenterName() {
		return serviceCenterName;
	}

	public void setServiceCenterName(String serviceCenterName) {
		this.serviceCenterName = serviceCenterName;
	}

	public String getServiceCenterAddress() {
		return serviceCenterAddress;
	}

	public void setServiceCenterAddress(String serviceCenterAddress) {
		this.serviceCenterAddress = serviceCenterAddress;
	}

	public String getServiceCenterContact() {
		return serviceCenterContact;
	}

	public void setServiceCenterContact(String serviceCenterContact) {
		this.serviceCenterContact = serviceCenterContact;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

//	public List<Technician> getTechnicians() {
//		return technicians;
//	}
//
//	public void setTechnicians(List<Technician> technicians) {
//		this.technicians = technicians;
//	}

	public List<ServicePackage> getServicePackages() {
		return servicePackages;
	}

	public void setServicePackages(List<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}

	public List<VehicleEquipments> getVehicleEquipments() {
		return vehicleEquipments;
	}

	public void setVehicleEquipments(List<VehicleEquipments> vehicleEquipments) {
		this.vehicleEquipments = vehicleEquipments;
	}

	public List<Labour> getLabours() {
		return labours;
	}

	public void setLabours(List<Labour> labours) {
		this.labours = labours;
	}

	
	
}
	

	
	
	
	



	
    
    

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "serviceCenter" )
//    @JsonManagedReference
//    private List<CustomerQuery> customerQueries;
//
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "serviceCenter")
//    @JsonManagedReference
//    private List<ServiceRecord> serviceRecords;

