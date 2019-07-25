package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.DeliveryPackage;
import com.oocl.packagebooking.repository.DeliveryPackageRepository;
import com.oocl.packagebooking.service.DeliveryPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeliveryPackageController {

    @Autowired
    private DeliveryPackageService deliveryPackageService;

    @GetMapping("/deliveryPackages")
    public ResponseEntity getPackages() {
        List<DeliveryPackage> deliveryPackages = deliveryPackageService.getPackages();
        return ResponseEntity.ok(deliveryPackages);
    }


}
