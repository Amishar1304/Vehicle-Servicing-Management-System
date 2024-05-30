package com.bit.VehicleServiceManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "labour_id")
public class Labour {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "labour_id")
	private int labour_id;
	
	private String labourName;
	private boolean oilChange;
	private boolean washing;
	private boolean coloring;
	private boolean repairing;
	private boolean filtersCheck;
	
//	 @ManyToOne(fetch = FetchType.LAZY)
//	 @JoinColumn(name = "serviceCenter_id")
//	 @JsonBackReference
//	 private ServiceCenter serviceCenter;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_center_id")
    @JsonBackReference
    private ServiceCenter serviceCenter;
	 
	  
//	  @ManyToOne(fetch = FetchType.EAGER)
//	  @JoinColumn(name = "technician_id")
//	 // @JsonManagedReference
//	  @JsonBackReference
//	  private Technician technician;
	 

	
	public Labour() {
		super();
	}

	public Labour(int labour_id, String labourName, boolean oilChange, boolean washing, boolean coloring,
			boolean repairing, boolean filtersCheck, ServiceCenter serviceCenter, Technician technician) {
		super();
		this.labour_id = labour_id;
		this.labourName = labourName;
		this.oilChange = oilChange;
		this.washing = washing;
		this.coloring = coloring;
		this.repairing = repairing;
		this.filtersCheck = filtersCheck;
		this.serviceCenter = serviceCenter;
		//this.technician = technician;
	}

	public int getLabour_id() {
		return labour_id;
	}

	public void setLabour_id(int labour_id) {
		this.labour_id = labour_id;
	}

	public String getLabourName() {
		return labourName;
	}

	public void setLabourName(String labourName) {
		this.labourName = labourName;
	}

	public boolean isOilChange() {
		return oilChange;
	}

	public void setOilChange(boolean oilChange) {
		this.oilChange = oilChange;
	}

	public boolean isWashing() {
		return washing;
	}

	public void setWashing(boolean washing) {
		this.washing = washing;
	}

	public boolean isColoring() {
		return coloring;
	}

	public void setColoring(boolean coloring) {
		this.coloring = coloring;
	}

	public boolean isRepairing() {
		return repairing;
	}

	public void setRepairing(boolean repairing) {
		this.repairing = repairing;
	}

	public boolean isFiltersCheck() {
		return filtersCheck;
	}

	public void setFiltersCheck(boolean filtersCheck) {
		this.filtersCheck = filtersCheck;
	}

	public ServiceCenter getServiceCenter() {
		return serviceCenter;
	}

	public void setServiceCenter(ServiceCenter serviceCenter) {
		this.serviceCenter = serviceCenter;
	}

//	public Technician getTechnician() {
//		return technician;
//	}
//
//	public void setTechnician(Technician technician) {
//		this.technician = technician;
//	}

	

	
	
	

}
