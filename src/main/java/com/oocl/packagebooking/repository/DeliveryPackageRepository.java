package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.model.DeliveryPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface DeliveryPackageRepository extends JpaRepository<DeliveryPackage, Long> {

    List<DeliveryPackage> findByStatusIs(int status);

    @Modifying
    @Transactional
    @Query("update DeliveryPackage dp set dp.status = 2 where dp.waybillNumber = ?1")
    int updatePackageByStatusIsTwo(long waybillNumber);

    @Modifying
    @Transactional
    @Query("update DeliveryPackage dp set dp.bookTime = ?1, dp.status = 1 where dp.waybillNumber = ?2")
    int updatePackageTimeByWayBillNumber(Date date, long waybillNumber);

}
