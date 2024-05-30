//package com.bit.VehicleServiceManagementSystem.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//
//@Entity
//public class ServiceDetail {
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//	
//	@ManyToOne
//    @JoinColumn(name = "vehicle_id")
//    @JsonBackReference
//    private Vehicle vehicle;
//
//    @ManyToOne
//    @JoinColumn(name = "technician_id")
//    @JsonBackReference
//    private Technician technician;
//
//	public ServiceDetail() {
//		super();
//	}
//
//	public ServiceDetail(Long id, Vehicle vehicle, Technician technician) {
//		super();
//		this.id = id;
//		this.vehicle = vehicle;
//		this.technician = technician;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Vehicle getVehicle() {
//		return vehicle;
//	}
//
//	public void setVehicle(Vehicle vehicle) {
//		this.vehicle = vehicle;
//	}
//
//	public Technician getTechnician() {
//		return technician;
//	}
//
//	public void setTechnician(Technician technician) {
//		this.technician = technician;
//	}
//	
//	
//    
//    
//
//}
