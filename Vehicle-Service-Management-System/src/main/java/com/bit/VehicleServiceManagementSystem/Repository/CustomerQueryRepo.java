package com.bit.VehicleServiceManagementSystem.Repository;

import com.bit.VehicleServiceManagementSystem.entity.CustomerQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerQueryRepo extends JpaRepository<CustomerQuery, Long> {
}
