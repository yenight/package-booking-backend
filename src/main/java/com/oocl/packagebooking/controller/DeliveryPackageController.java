package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.DeliveryPackage;
import com.oocl.packagebooking.repository.DeliveryPackageRepository;
import com.oocl.packagebooking.service.DeliveryPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
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

    @PatchMapping("/deliveryPackages")
    public ResponseEntity updatePackage(@RequestBody DeliveryPackage deliveryPackage) {
        int status = -1;
        if (deliveryPackage.getBookTime() == null) {
            status = deliveryPackageService.updatePackageByStatusIsTwo(deliveryPackage.getWaybillNumber());
            return ResponseEntity.ok(status);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(deliveryPackage.getBookTime());
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour >= 9 && hour <= 20) {
                status = deliveryPackageService.updatePackageTimeByWayBillNumber(deliveryPackage.getWaybillNumber(), deliveryPackage.getBookTime());
                return ResponseEntity.ok(status);
            } else {
                return ResponseEntity.badRequest().body("不是营业时间,无法预约取件");
            }
        }
    }



}
