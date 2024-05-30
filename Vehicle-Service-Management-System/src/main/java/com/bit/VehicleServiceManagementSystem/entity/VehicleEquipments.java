package com.bit.VehicleServiceManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VehicleEquipments {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "equipment_id")
	private int equipment_id;
	private String equipName;
	
//	 @ManyToOne(fetch = FetchType.LAZY)
//	 @JoinColumn(name = "serviceCenter_id")
//	 @JsonBackReference
//	 private ServiceCenter serviceCenter;
	 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "serviceCenter_id")
	private ServiceCenter serviceCenter;

	
	public VehicleEquipments() {
		super();
	}


	public VehicleEquipments(int equipment_id, String equipName, ServiceCenter serviceCenter) {
		super();
		this.equipment_id = equipment_id;
		this.equipName = equipName;
		this.serviceCenter = serviceCenter;
	}


	public int getEquipment_id() {
		return equipment_id;
	}


	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}


	public String getEquipName() {
		return equipName;
	}


	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}


	public ServiceCenter getServiceCenter() {
		return serviceCenter;
	}


	public void setServiceCenter(ServiceCenter serviceCenter) {
		this.serviceCenter = serviceCenter;
	}

}
