package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.DeliveryPackage;
import com.oocl.packagebooking.service.DeliveryPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class DeliveryPackageController {

    @Autowired
    private DeliveryPackageService deliveryPackageService;

    @GetMapping("/deliveryPackages")
    public ResponseEntity getPackages(@RequestParam(defaultValue = "-1") int status) {
        if (status < -1 || status > 2)
            return ResponseEntity.badRequest().build();
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

    @PatchMapping("/deliveryPackages")
    public ResponseEntity updatePackage(@RequestBody DeliveryPackage deliveryPackage) {
        int status = deliveryPackageService.updatePackage(deliveryPackage);
        if (status == 1) {
            return ResponseEntity.ok(status);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }



}
