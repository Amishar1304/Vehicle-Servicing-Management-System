package com.bit.VehicleServiceManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bit.VehicleServiceManagementSystem.entity.ServicePackage;

@Repository
public interface ServicePackageRepo extends JpaRepository<ServicePackage, Integer> {

}
