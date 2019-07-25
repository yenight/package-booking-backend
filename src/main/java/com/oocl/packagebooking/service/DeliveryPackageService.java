package com.oocl.packagebooking.service;

import com.oocl.packagebooking.model.DeliveryPackage;
import com.oocl.packagebooking.repository.DeliveryPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPackageService {

    @Autowired
    private DeliveryPackageRepository deliveryPackageRepository;

    public List<DeliveryPackage> getPackages() {
        return deliveryPackageRepository.findAll();
    }

    public DeliveryPackage createdPackage(DeliveryPackage deliveryPackage) {
        if (deliveryPackage != null) {
            return deliveryPackageRepository.saveAndFlush(deliveryPackage);
        }
        return null;
    }
}
