package com.bit.VehicleServiceManagementSystem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Data
//@Setter
//@Getter
//@Table
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technician_id")
    private Long technician_id;

    @NotBlank(message="Name is required")
    private String name;
    
    @NotNull (message = "contact number shouldn't be Empty")
    @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ")
    private String contact;
    
    private String shiftStatus;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceCenter_id")
    @JsonBackReference
    private ServiceCenter serviceCenter;
    
//    @OneToMany(mappedBy = "technician", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    //@JsonManagedReference
//    @JsonManagedReference("technician-vehicle")
//    private List<Vehicle> vehicles;
    

    
    public Technician() {
		super();
	}

	public Technician(Long technician_id, @NotBlank(message = "Name is required") String name,
		@NotNull(message = "contact number shouldn't be Empty") @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ") String contact,
		String shiftStatus, ServiceCenter serviceCenter) {  //, List<Vehicle> vehicles
	super();
	this.technician_id = technician_id;
	this.name = name;
	this.contact = contact;
	this.shiftStatus = shiftStatus;
	this.serviceCenter = serviceCenter;
	//this.vehicles = vehicles;
}


	public Long getTechnician_id() {
		return technician_id;
	}

	public void setTechnician_id(Long technician_id) {
		this.technician_id = technician_id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getShiftStatus() {
		return shiftStatus;
	}

	public void setShiftStatus(String shiftStatus) {
		this.shiftStatus = shiftStatus;
	}


	public ServiceCenter getServiceCenter() {
		return serviceCenter;
	}


	public void setServiceCenter(ServiceCenter serviceCenter) {
		this.serviceCenter = serviceCenter;
	}


//	public List<Vehicle> getVehicles() {
//		return vehicles;
//	}
//
//	public void setVehicles(List<Vehicle> vehicles) {
//		this.vehicles = vehicles;
//	}
	
}

