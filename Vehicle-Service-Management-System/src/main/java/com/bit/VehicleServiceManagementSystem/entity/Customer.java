package com.bit.VehicleServiceManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
//@Getter
//@Setter
//@Data
//@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long customer_id;
    
    @NotBlank(message="Name is required") 
    private String customerName;
    @NotNull (message = "contact number shouldn't be Empty")
    @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ")
    private String customerContact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceCenter_id")
    @JsonBackReference
    private ServiceCenter serviceCenter;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "customer")
    @JsonManagedReference
    private List<Vehicle> vehicles;

	public Customer() {
		super();
	}

	public Customer(Long customer_id, @NotBlank(message = "Name is required") String customerName,
			@NotNull(message = "contact number shouldn't be Empty") @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ") String customerContact,
			ServiceCenter serviceCenter, List<Vehicle> vehicles) {
		super();
		this.customer_id = customer_id;
		this.customerName = customerName;
		this.customerContact = customerContact;
		this.serviceCenter = serviceCenter;
		this.vehicles = vehicles;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public ServiceCenter getServiceCenter() {
		return serviceCenter;
	}

	public void setServiceCenter(ServiceCenter serviceCenter) {
		this.serviceCenter = serviceCenter;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", customerName=" + customerName + ", customerContact="
				+ customerContact + ", serviceCenter=" + serviceCenter + ", vehicles=" + vehicles + "]";
	}

	
    

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "customer")
//    @JsonManagedReference
//    private List<CustomerQuery> customerQueries;
}
