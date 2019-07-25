package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.DeliveryPackage;
import com.oocl.packagebooking.repository.DeliveryPackageRepository;
import com.oocl.packagebooking.service.DeliveryPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryPackageController {

    @Autowired
    private DeliveryPackageService deliveryPackageService;

    @GetMapping("/deliveryPackages")
    public ResponseEntity getPackages(@RequestParam(defaultValue = "-1") int status) {
        List<DeliveryPackage> deliveryPackages;
        deliveryPackages = status == -1? deliveryPackageService.getPackages() : deliveryPackageService.getPackageByStatus(status);
        return ResponseEntity.ok(deliveryPackages);
    }

    @PostMapping("/deliveryPackages")
    public ResponseEntity createPackage(@RequestBody DeliveryPackage deliveryPackage) {
        DeliveryPackage createdPackage = deliveryPackageService.createdPackage(deliveryPackage);
        if (createdPackage != null) {
            return ResponseEntity.ok(createdPackage);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
