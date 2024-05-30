package com.bit.VehicleServiceManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ServicePackage {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "servicePackage_id")
    private int servicePackage_id;
    private String packageName;
    private String description;
    private double price;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceCenter_id")
    @JsonBackReference
    private ServiceCenter serviceCenter;
    
	public ServicePackage() {
		super();
	}

	public ServicePackage(int servicePackage_id, String packageName, String description, double price,
			ServiceCenter serviceCenter) {
		super();
		this.servicePackage_id = servicePackage_id;
		this.packageName = packageName;
		this.description = description;
		this.price = price;
		this.serviceCenter = serviceCenter;
	}

	public int getServicePackage_id() {
		return servicePackage_id;
	}

	public void setServicePackage_id(int servicePackage_id) {
		this.servicePackage_id = servicePackage_id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ServiceCenter getServiceCenter() {
		return serviceCenter;
	}

	public void setServiceCenter(ServiceCenter serviceCenter) {
		this.serviceCenter = serviceCenter;
	}
	
	

}