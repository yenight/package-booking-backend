package com.oocl.packagebooking.service;

import com.oocl.packagebooking.model.DeliveryPackage;
import com.oocl.packagebooking.repository.DeliveryPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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

    public List<DeliveryPackage> getPackageByStatus(int status) {
        return deliveryPackageRepository.findByStatusIs(status);
    }

    public int updatePackage (DeliveryPackage deliveryPackage) {
        int status = -1;
        if (deliveryPackage.getBookTime() == null) {
            status = deliveryPackageRepository.updatePackageByStatusIsTwo(deliveryPackage.getWaybillNumber());
        } else if (deliveryPackage.getWaybillNumber() > 0){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(deliveryPackage.getBookTime());
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour >= 9 && hour <= 20) {
                status = deliveryPackageRepository.updatePackageTimeByWayBillNumber(deliveryPackage.getBookTime(), deliveryPackage.getWaybillNumber());
            }
        }
        return status;
    }
}
