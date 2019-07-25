package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.model.DeliveryPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryPackageRepository extends JpaRepository<DeliveryPackage, Long> {

    List<DeliveryPackage> findByStatusIs(int status);

}
