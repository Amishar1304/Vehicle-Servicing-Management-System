package com.bit.VehicleServiceManagementSystem.Repository;

import com.bit.VehicleServiceManagementSystem.entity.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerContact(String customerContact);

	List<Customer> findByCustomerNameContainingIgnoreCase(String searchText);
	

}
