package com.bit.VehicleServiceManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
//@Data
//@Setter
//@Getter
//@Table
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviceRecord_id")
    private Long serviceRecord_id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date = new Date(); // Initialize with current date
    
//    @NotNull(message = "Payment status is required")
//    private boolean isPayment;
    
    @NotNull(message = "Service amount is required")
    @Min(value = 0, message = "Service amount must be greater than or equal to zero")
    private Integer serviceAmount;
    
//    private boolean isOilChange;
//    private boolean isWashing;
    
    private String isOilChange;
    private String isWashing;
    
    @NotBlank(message = "Other amenities description is required")
    private String otherAmenities;
    
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    private Vehicle vehicle;
    
  
    
   
    

	public ServiceRecord() {
		super();
	}
	

	public ServiceRecord(Long serviceRecord_id, Date date,
			@NotNull(message = "Service amount is required") @Min(value = 0, message = "Service amount must be greater than or equal to zero") Integer serviceAmount,
			String isOilChange, String isWashing,
			@NotBlank(message = "Other amenities description is required") String otherAmenities, Vehicle vehicle) {
		super();
		this.serviceRecord_id = serviceRecord_id;
		this.date = date;
		this.serviceAmount = serviceAmount;
		this.isOilChange = isOilChange;
		this.isWashing = isWashing;
		this.otherAmenities = otherAmenities;
		this.vehicle = vehicle;
	}

	public Long getServiceRecord_id() {
		return serviceRecord_id;
	}

	public void setServiceRecord_id(Long serviceRecord_id) {
		this.serviceRecord_id = serviceRecord_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getServiceAmount() {
		return serviceAmount;
	}

	public void setServiceAmount(Integer serviceAmount) {
		this.serviceAmount = serviceAmount;
	}

	public String isOilChange() {
		return isOilChange;
	}

	public void setOilChange(String isOilChange) {
		this.isOilChange = isOilChange;
	}

	public String isWashing() {
		return isWashing;
	}

	public void setWashing(String isWashing) {
		this.isWashing = isWashing;
	}

	public String getOtherAmenities() {
		return otherAmenities;
	}

	public void setOtherAmenities(String otherAmenities) {
		this.otherAmenities = otherAmenities;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	

	//getcustomer name

	public String getCustomerName() {
	        if (vehicle != null && vehicle.getCustomer() != null) {
	            return vehicle.getCustomer().getCustomerName();
	        }
	        return null; // Or handle null case as needed
	    }
	   
	   //get technician
	   
//	   public String getTechnicianName() {
//		    if (vehicle != null) {
//		        Technician technician = vehicle.getTechnician();
//		        if (technician != null) {
//		            return technician.getName();
//		        }
//		    }
//		    return null; // Or handle null case as needed
//		}

	

	
	
    

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "serviceCenter_id")
//    @JsonBackReference
//    private ServiceCenter serviceCenter;
}
