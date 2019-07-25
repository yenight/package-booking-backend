package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.model.DeliveryPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPackageRepository extends JpaRepository<DeliveryPackage, Long> {
}
